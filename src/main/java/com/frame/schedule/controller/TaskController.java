package com.frame.schedule.controller;

import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.service.TaskService;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ValidationException;
import java.text.ParseException;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
@Controller
@PageDefinition("taskController.xml")
@RequestMapping("/schedule/task")
public class TaskController extends GeneralController<TaskEntity>{
    @Autowired
    private TaskService taskService;
    @Override
    public boolean beforeUpdate(TaskEntity entity) {
        try {
            CronExpression.validateExpression(entity.getCronExpression());
            if (entity.getId()!=null) {
                taskService.updateCron(entity);
            }else{
                entity.setStatus(TaskEntity.Status.WORKING.toString());
            }
        } catch (ParseException e) {
            throw new ValidationException(e.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean beforeDelete(TaskEntity entity) {
        taskService.deleteSchedule(entity);
        return true;
    }
    
    @Override
    public void afterUpdate(TaskEntity entity,boolean isAdd) {
        if (isAdd){
            taskService.addSchedule(entity);
        }
    }
}
