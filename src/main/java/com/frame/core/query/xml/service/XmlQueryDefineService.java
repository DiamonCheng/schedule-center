package com.frame.core.query.xml.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.frame.core.components.BaseEntity;
import com.frame.core.dao.GeneralDao;
import com.frame.core.query.xml.DataFilter;
import com.frame.core.query.xml.DefaultDataFilter;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.PageDefinitionHolder;
import com.frame.core.query.xml.QueryHqlResolver;
import com.frame.core.query.xml.definition.ColumnDefinition;
import com.frame.core.query.xml.definition.Manage;
import com.frame.core.query.xml.definition.ManageField;
import com.frame.core.query.xml.definition.PageDefinition;
import com.frame.core.query.xml.definition.QueryCondition;
import com.frame.core.query.xml.definition.QueryConditionDefine;
import com.frame.core.query.xml.definition.QueryConditions;
import com.frame.core.query.xml.definition.QueryDefinition;
import com.frame.core.utils.ReflectUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class XmlQueryDefineService {
	private final static Logger LOGGER = LoggerFactory.getLogger(XmlQueryDefineService.class);
	public static class DataSetTransferException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		DataSetTransferException(Throwable t){super(t);}
	}
	public static class QueryConditionParseException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		QueryConditionParseException(String message){super(message);}

		public QueryConditionParseException(Throwable e) {super(e);	}
	}
	public static class ManageExecuteException extends RuntimeException{
		private static final long serialVersionUID = 1L;
        ManageExecuteException(Throwable t){super(t);}
	}
	private DataFilter defaultDataFilter=new DefaultDataFilter();
	@Autowired
	GeneralDao dao;
	@Autowired
	public Gson gson;
	@SuppressWarnings("unchecked")
	public Object list(PageDefinition pageDefinition,QueryConditions conditions){
		String selectHql=QueryHqlResolver.generateSelect(pageDefinition.getQueryDefinition(), conditions);
		if (LOGGER.isInfoEnabled())
			LOGGER.info("生成的查询HQL："+selectHql);
		else System.out.println("生成的查询HQL："+selectHql);
		Query query=dao.getSessionFactory().getCurrentSession().createQuery(selectHql); 
		int i=0;
		if (conditions.getConditions()!=null) for (QueryCondition condition : conditions.getConditions()) {
			if (StringUtils.isEmpty(condition.getValue())) continue;
			query.setParameter(i, condition.parsedValue());
			i++;
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setMaxResults(conditions.getPageSize());
		query.setFirstResult(conditions.getPageSize()*(conditions.getPage()-1));
		//分页
		List<Map<String,Object>> listMap=query.list();
		List<String[]> list=new ArrayList<String[]>();
		for (Map<String,Object> map : listMap) {
			String[] stra=new String[pageDefinition.getQueryDefinition().getColumns().size()+1];
			stra[0]=map.get("id").toString();
			int j=0;
			for (Iterator<ColumnDefinition> iterator = pageDefinition.getQueryDefinition().getColumns().iterator(); iterator.hasNext(); j++) {
				ColumnDefinition column = iterator.next();
				if (column.getStaticColumnData()!=null) stra[j]=column.getStaticColumnData();
				else if (column.getFilter()!=null){
					try {
						DataFilter f=(DataFilter) column.getFilter().newInstance();
						stra[j+1]=f.filt(map.get("col_"+j),column);
					} catch (Exception e) {
						throw new DataSetTransferException(e);
					}
				}else stra[j+1]=defaultDataFilter.filt(map.get("col_"+j),column);
			}
			list.add(stra);
		}
		return list;
	}
	public int totalPageCount(PageDefinition pageDefinition,QueryConditions conditions){
		String selectHql=QueryHqlResolver.generateCount(pageDefinition.getQueryDefinition(), conditions.getConditions());
		if (LOGGER.isInfoEnabled())
			LOGGER.info("生成的计数HQL："+selectHql);
		else System.out.println("生成的计数HQL："+selectHql);
		List<Object> params=new ArrayList<Object>();
		if (conditions.getConditions()!=null) for (Iterator<QueryCondition> iterator = conditions.getConditions().iterator(); iterator.hasNext();) {
			QueryCondition condition = iterator.next();
			if (StringUtils.isEmpty(condition.getValue())) continue;
			params.add(condition.parsedValue());
		}
		long count=dao.getUnique(selectHql, params.toArray());
		int pageSize=conditions.getPageSize();
		int totalPageCount=(int) ((count+pageSize-1)/pageSize);
		if (totalPageCount<1) totalPageCount=1;
		return totalPageCount;
	}
	/**
     * 准备查询条件
	 * @param queryConditions 浏览器传送过来结构化之后的参数
	 * @param queryDefinition xml中拉取的设置
	 * @return 查询条件 TODO应为复制之后的。
	 */
	public QueryConditions prepareQueryCondition(QueryConditions queryConditions,QueryDefinition queryDefinition){
		List<QueryConditionDefine> res= null;
		try {
			res = queryDefinition.cloneQueryConditionDefine();
		} catch (CloneNotSupportedException e) {
			throw new QueryConditionParseException(e);
		}
		for(QueryConditionDefine q : res){
			if (queryConditions.getConditions()!=null)//将查询的条件回写至条件栏
			    for (QueryCondition q2:queryConditions.getConditions()){
                    if (q.equalsField(q2)){
                        q.setValue(defaultDataFilter.filt(q2.getValue(),null));
                        break;
				    }
			}
			if (q.getValue()==null) q.setValue(q.getDefaultValue());//默认查询值
			prepareSelectData(q);
		}
		queryConditions.setConditions(new ArrayList<QueryCondition>(res));
		return queryConditions;
	}
	private void prepareSelectData(QueryConditionDefine q){
        if ("SELECT".equals(q.getInputType())||"CHECKBOX".equals(q.getInputType())){
            if (!StringUtils.isEmpty(q.getStaticData())){
                q.setParsedData(gson.fromJson(q.getStaticData(),new TypeToken<List<Map<String,String>>>(){}.getType()));
            }else{
                if (q.getOptionClass()!=null&&BaseEntity.class.isAssignableFrom(q.getOptionClass())){
                    List<?> list= dao.findMap("select "+q.getSelectTextField()+" as text,"+q.getSelectValueField() +" as value from "+q.getOptionClass().getName()+" ");
                    q.setParsedData(list);
                }else throw new QueryConditionParseException("Select option data parse Exception.No static data found nor optionClass found.");
            }
        }
    }

	public void delete(BaseEntity entity){
		dao.getHibernateTemplate().delete(entity);
	}
	@SuppressWarnings("unchecked")
	public <T> T get(Class<? extends BaseEntity> cls, Serializable id){
		return (T)dao.getHibernateTemplate().get(cls,id);
	}
	public Map<String,Object> prepareManage(Long id,Manage manage,Class<? extends BaseEntity> targetClass){
	    Map<String,Object> models=new HashMap<String,Object>();
        BaseEntity target=null;
        List<ManageField> manageFields= null;
        try {
            manageFields = manage.cloneFields();
        } catch (CloneNotSupportedException e) {
            throw new ManageExecuteException(e);
        }
        try {
            if (id == null) {
                target = targetClass.newInstance();
                for (ManageField manageField:manageFields) {
                	manageField.setValue(manageField.getDefaultValue());
                }
            }else{
                target=this.get(targetClass,id);
                for (ManageField manageField:manageFields) {
                    try {
						manageField.setValue(ReflectUtil.getValueByField(target,manageField.getField()));
					} catch (Exception e) {
						LOGGER.warn("字段解析出现异常，值设置为默认空。class："+targetClass.getName()+",field:"+manageField.getField(),e);
					}
                }
            }
            for (ManageField manageField:manageFields) try {
                prepareSelectData(manageField);
                Class<?> fieldClass=ReflectUtil.resolveFieldClass(targetClass,manageField.getField());
                if (BaseEntity.class.isAssignableFrom(fieldClass)){
                    manageField.setIsEntity(true);
                    if (manageField.getValue()!=null&&!BaseEntity.class.isAssignableFrom(manageField.getValue().getClass())){
                    	manageField.setValue(null);
                    }
                    if (manageField.getValue()!=null&&BaseEntity.class.isAssignableFrom(manageField.getValue().getClass()))
                        manageField.setValue(ReflectUtil.getValueByField(manageField.getValue(),manageField.getSelectValueField()));
                }else if(Collection.class.isAssignableFrom(fieldClass)){
                	if (manageField.getValue()!=null&&!Collection.class.isAssignableFrom(manageField.getValue().getClass())){
                    	manageField.setValue(null);
                    }
                	if (BaseEntity.class.isAssignableFrom(manageField.getOptionClass())){
                		manageField.setIsEntity(true);
                	}
                }
            }catch(Exception e){
            	LOGGER.warn("字段解析出现异常，值设置为默认空。class："+targetClass.getName()+",field:"+manageField.getField(),e);
            }
            models.put("entity",target);
            models.put("manageFields",manageFields);
            models.put("manage", manage);
        }catch (Exception e) {
            throw new ManageExecuteException(e);
        }
	    return models;
    }
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> T saveManage(String paramString,GeneralController<T> c){
	    Class<T> targetClass=c.getTargetClass();
		PageDefinitionHolder pageHolder=c.getPageHolder();
		T entity =gson.fromJson(paramString,targetClass);
		T toSave=null;
        List<ManageField> manageFields=pageHolder.getPageDefinition().getManage().getField();
        boolean isAdd=false;
        if (entity.getId()==null){
            toSave=entity;
            isAdd=true;
        }else{
            toSave=get(targetClass,entity.getId());
            for(ManageField manageField:manageFields){
                ReflectUtil.setValueByField(toSave,manageField.getField(), ReflectUtil.getValueByField(entity,manageField.getField()));
            }
        }
        for(ManageField manageField:manageFields){
            Object fieldValue=ReflectUtil.getValueByField(toSave,manageField.getField());
            if (fieldValue==null){
            	
            }else if(fieldValue instanceof BaseEntity){//处理单个实体
                ReflectUtil.setValueByField(
                		toSave,manageField.getField(),
                		get(
                				(Class<? extends BaseEntity>) manageField.getOptionClass(),
                				(Serializable) ReflectUtil.getValueByField(
                						fieldValue,
                						manageField.getSelectValueField()
                						)
                				)
                		);
            }else if(fieldValue instanceof Collection){//处理实体集合的字段
            	try {
					Collection<BaseEntity> newCollectionValue=(Collection<BaseEntity>) fieldValue.getClass().newInstance();
					for(Object oldEntity:(Collection<?>)fieldValue){
						newCollectionValue.add(this.<BaseEntity>get(
                				(Class<? extends BaseEntity>) manageField.getOptionClass(),
                				(Serializable) ReflectUtil.getValueByField(
                						oldEntity,
                						manageField.getSelectValueField()
                						)
                				)
                		);
					}
					ReflectUtil.setValueByField(toSave,manageField.getField(), newCollectionValue);
				} catch (Exception e) {
					throw new ManageExecuteException(e);
				}
            }
        }
        c.beforeUpdate(entity);
        saveOrUpdate(toSave);
        c.afterUpdate(toSave,isAdd);
        return toSave;
    }
    public void saveOrUpdate(BaseEntity entity){
	    if (entity.getId()==null){
	        dao.getHibernateTemplate().save(entity);
        }else{
	        dao.getHibernateTemplate().update(entity);
        }
    }
}
