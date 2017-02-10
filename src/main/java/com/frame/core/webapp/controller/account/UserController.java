package com.frame.core.webapp.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.core.entity.UserEntity;

@RequestMapping("/users")
@Controller
@PageDefinition
public class UserController extends GeneralController<UserEntity> {
}
