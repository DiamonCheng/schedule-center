package com.frame.schedule.service;

import com.frame.schedule.entity.TaskEntity;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import javax.annotation.PostConstruct;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public interface TaskService {
    @PostConstruct
    void startup() throws SchedulerException;
    
    void updateCron(TaskEntity taskEntity);
    
    void addTask(TaskEntity entity);
    
    void deleteTask(TaskEntity taskEntity);
    
    void pauseTask(TaskEntity taskEntity);
    
    void resumeTask(TaskEntity taskEntity);
    
    void executeTask(TaskEntity taskEntity, JobExecutionContext jobExecutionContext);
}
