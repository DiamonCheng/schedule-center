package com.frame.core.query.xml.definition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement()
public class PageDefinition {

	private QueryDefinition queryDefinition;
	private Manage manage;
	private Delete delete;
//	public static void main(String[] args) throws JAXBException {
//		JAXBContext context=JAXBContext.newInstance(Definition.class);
//		Definition d=new Definition();
//		d.setQueryDefinition(new QueryDefinition());
//		d.getQueryDefinition().setMappedClass(new ArrayList<MappedClassEntry>());
//		d.getQueryDefinition().getMappedClass().add(new MappedClassEntry(MenuEntity.class, "m1"));
//		d.getQueryDefinition().getMappedClass().add(new MappedClassEntry(MenuEntity.class, "m2"));
//		MappedClassEntry e=new MappedClassEntry(MenuEntity.class, "m3");
//		e.setJoin(new ArrayList<JoinEntry>());
//		e.getJoin().add(new JoinEntry("parent", "p"));
//		d.getQueryDefinition().getMappedClass().add(e);
//		Marshaller marshaller = context.createMarshaller(); 
//		 marshaller.marshal(d, System.out);  
//	}
	public static void main(String[] args) throws JAXBException {
		JAXBContext context=JAXBContext.newInstance(PageDefinition.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();  
//		PageDefinition d= (PageDefinition) unmarshaller.unmarshal(PageDefinition.class.getResource("../example.xml"));
//		System.out.println(d);
	}
	/*public void beanToXML() {  
        Classroom classroom = new Classroom(1, "软件工程", 4);  
        Student student = new Student(101, "张三", 22, classroom);  
  
        try {  
            JAXBContext context = JAXBContext.newInstance(Student.class);  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.marshal(student, System.out);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
  
    } */
	public QueryDefinition getQueryDefinition() {
		return queryDefinition;
	}
	public void setQueryDefinition(QueryDefinition queryDefinition) {
		this.queryDefinition = queryDefinition;
	}
	public Delete getDelete() {
		return delete;
	}

	public void setDelete(Delete delete) {
		this.delete = delete;
	}

	public Manage getManage() {
		return manage;
	}

	public void setManage(Manage manage) {
		this.manage = manage;
	}
}
