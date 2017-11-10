package com.frame.core.webapp.controller.menu;

import com.frame.core.entity.MenuEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Controller
@RequestMapping("/menu")
@PageDefinition("pageDefinition.xml")
public class MenuController extends GeneralController <MenuEntity>{
	@Override
	public boolean beforeDelete(MenuEntity entity) {
		System.out.println("beforeDelete MenuEntity id："+entity.getId());
		return true;
	}
	
	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(com.frame.core.query.xml.definition.PageDefinition.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		com.frame.core.query.xml.definition.PageDefinition page= (com.frame.core.query.xml.definition.PageDefinition) unmarshaller.unmarshal(MenuController.class.getResourceAsStream("pageDefinition.xml"));
		System.out.println(page);
	}
}
