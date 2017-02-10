package com.frame.core.query.xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.frame.core.components.BaseEntity;
import com.frame.core.query.xml.definition.JoinEntry;
import com.frame.core.query.xml.definition.MappedClassEntry;
import com.frame.core.query.xml.definition.QueryConditionDefine;
import com.frame.core.query.xml.service.XmlQueryDefineService;
import com.frame.core.utils.ReflectUtil;
import com.frame.webapp.controller.menu.MenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.frame.core.query.xml.definition.PageDefinition;
import org.springframework.util.StringUtils;

/**
 * 用于承载页面定义的配置类
 */
public class PageDefinitionHolder {
	private static final Logger LOGGER=LoggerFactory.getLogger(PageDefinitionHolder.class);
	public static class PageDefinitionLoadException extends RuntimeException{
		private static final long serialVersionUID = 4989620748698016255L;
		public PageDefinitionLoadException(Throwable t){super(t);}
		public PageDefinitionLoadException(String m){super(m);}
	}
	private PageDefinition page;//页面配置对象
	private String fileName;//页面配置xml的文件名
	private Class<?> loader;//用于加载配置的类--使用Resource加载。一般为XXXController
	private long lastModified=0L;//修改时间，用于确定是否需要重新加载
    private Unmarshaller unmarshaller;//JAXB xml反序列化工具
	public PageDefinitionHolder(String fileName, Class<?> loader){
		this.fileName=fileName;
		this.loader=loader;
		try {
            JAXBContext context = JAXBContext.newInstance(PageDefinition.class);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new PageDefinitionLoadException(e);
		}
	}

    /**
     * 调用此方法刷新配置
     * @param target GeneralController this 的引用，用于在加载xml之后初始化一些回调之类的。
     */
	public void refresh(GeneralController target){
		try {
			boolean isNeedLoad=false;
			Resource resource= new ClassPathResource(fileName, loader);
			LOGGER.info("Refreshing pageDefinition: "+fileName+",use class "+loader);
			if (page==null){
				isNeedLoad=true;
			}else if ("file".equals(resource.getURL().getProtocol())){
				long lastModified = resource.getFile().lastModified();
				if (this.lastModified<lastModified) {
					this.lastModified=lastModified;
					LOGGER.info("pageDefinition out of date reload pageDefinition: "+fileName+",use class "+loader);
					isNeedLoad=true;
				}
			}
			if (isNeedLoad){
				page = (PageDefinition) unmarshaller.unmarshal(resource.getInputStream());
				initAfterLoad(target);
			}
		} catch (JAXBException | IOException | NoSuchMethodException e) {
			throw new PageDefinitionLoadException(e);
		}
    }

    /**
     * 初始化
     */
	private void initAfterLoad(GeneralController c) throws NoSuchMethodException {
	    if (page.getQueryDefinition().getQueryConditionDefines()!=null)
	        for (QueryConditionDefine q:page.getQueryDefinition().getQueryConditionDefines()) {
                Class<?> type = null;
                for (MappedClassEntry mappedClass : page.getQueryDefinition().getMappedClass()) {
                    if (StringUtils.isEmpty(mappedClass.getAlias()) && StringUtils.isEmpty(q.getAlias()) || mappedClass.getAlias() != null && mappedClass.getAlias().equals(q.getAlias())) {
                        type = ReflectUtil.resolveFieldClass(mappedClass.getMappedClass(), q.getField());
                        break;
                    }
                    if (mappedClass.getJoin() != null) for (JoinEntry joinEntry : mappedClass.getJoin()) {
                        if (StringUtils.isEmpty(joinEntry.getAs()) && StringUtils.isEmpty(q.getAlias()) || joinEntry.getAs() != null && joinEntry.getAs().equals(q.getAlias())) {
                            Class<?> optionClass = ReflectUtil.resolveFieldClass(mappedClass.getMappedClass(), joinEntry.getField());
                            if (q.getOptionClass() == null) q.setOptionClass(optionClass);
                            type = ReflectUtil.resolveFieldClass(optionClass, q.getField());
                            break;
                        }
                    }
                    if (type != null) break;
                }
                if (type != null) q.setType(type);
                else
                    throw new PageDefinitionLoadException("No such field found in queryDedination! alias:" + q.getAlias() + " field:" + q.getField());
            }
		if (page.getDelete()!=null&&page.getDelete().getBeforeDelete()!=null){
			String beforeDeleteMethodName=page.getDelete().getBeforeDelete();
			Method beforeDeleteMethod=null;
			for(Method method:c.getClass().getDeclaredMethods()){
				if (method.getName().equals(beforeDeleteMethodName)){
					beforeDeleteMethod=method;
					break;
				}
			}
			if (beforeDeleteMethod==null) throw new NoSuchMethodException(c.getClass().getName()+"."+beforeDeleteMethodName);
			else page.getDelete().setBeforeDeleteMethod(beforeDeleteMethod);
			Class<?>[] paramTypes=beforeDeleteMethod.getParameterTypes();
			for (int i=0;i<paramTypes.length;i++){
				if(paramTypes[i].isAssignableFrom(c.getTargetClass())){
					page.getDelete().setInjectIndex(i);
					break;
				}
			}
		}
		if (page.getManage()!=null&&page.getManage().getBeforeManage()!=null){
            String beforeManageMethodName=page.getManage().getBeforeManage();
            Method beforeManageMethod=null;
            for(Method method:c.getClass().getDeclaredMethods()){
                if (method.getName().equals(beforeManageMethodName)){
                    beforeManageMethod=method;
                    break;
                }
            }
            if (beforeManageMethod==null) throw new NoSuchMethodException(c.getClass().getName()+"."+beforeManageMethodName);
            else page.getManage().setBeforeManageMethod(beforeManageMethod);
        }
	}
	/**
	 * 如果不是文件永远不过期
	 * @return
	 */
	public boolean isOutOfDate(){
		if (this.lastModified==0L) return false;
		URL url= loader.getResource(fileName);
		long lastModified = new File(url.getFile()).lastModified();
		return this.lastModified<lastModified;
	}
	public PageDefinition getPageDefinition(){
		return page;
	}
	public static void main(String[] args) throws  Exception{
		Resource resource= new ClassPathResource("pageDefinition.xml", MenuController.class);
		JAXBContext context = JAXBContext.newInstance(PageDefinition.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		PageDefinition page = (PageDefinition) unmarshaller.unmarshal(resource.getInputStream());
		System.out.println();
	}
}
