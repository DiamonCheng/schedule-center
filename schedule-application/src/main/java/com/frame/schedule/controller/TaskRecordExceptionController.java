package com.frame.schedule.controller;

import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.schedule.entity.TaskExceptionEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
@RequestMapping("/schedule/task/record/exception")
@PageDefinition("taskRecordExceptionController.xml")
@Controller
public class TaskRecordExceptionController extends GeneralController<TaskExceptionEntity> {
}
