package com.frame.schedule.controller;

import com.frame.core.components.AjaxResult;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.service.TaskService;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
                entity.setStatus(TaskEntity.Status.WORKING.toString()).setExecNum(0L);
            }
        } catch (ParseException e) {
            throw new ValidationException(e.getMessage());
        }
        return true;
    }
    
    @Override
    public void afterDelete(TaskEntity entity) {
        taskService.deleteTask(entity);
        super.afterDelete(entity);
    }
    
    @Override
    public void afterUpdate(TaskEntity entity,boolean isAdd) {
        if (isAdd){
            taskService.addTask(entity);
        }
    }
    
    @RequestMapping("/pause")
    @ResponseBody
    public Object pause(TaskEntity taskEntity){
        taskService.pauseTask(taskEntity);
        return new AjaxResult();
    }
    @RequestMapping("/resume")
    @ResponseBody
    public Object resume(TaskEntity taskEntity){
        taskService.resumeTask(taskEntity);
        return new AjaxResult();
    }
}
