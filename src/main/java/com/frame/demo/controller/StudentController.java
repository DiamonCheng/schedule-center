package com.frame.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.demo.entity.Student;

@Controller
@RequestMapping("/student")
@PageDefinition("studentController.xml")
public class StudentController extends GeneralController<Student> {
	
}
