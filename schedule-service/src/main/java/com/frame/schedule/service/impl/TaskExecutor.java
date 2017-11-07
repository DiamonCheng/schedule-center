package com.frame.schedule.service.impl;

import com.frame.core.utils.SpringWebContextUtil;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.service.TaskService;
import com.frame.schedule.service.core.ScheduleExecutor;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
public class TaskExecutor extends ScheduleExecutor<TaskEntity> {
    private static final Logger LOGGER= LoggerFactory.getLogger(TaskExecutor.class);
    @Override
    public void execute(TaskEntity schedulable,JobExecutionContext context) {
        try {
            SpringWebContextUtil.getBean(TaskService.class).executeTask(schedulable,context);
            LOGGER.warn("A task executed,"+schedulable);
        } catch (Exception e) {
            LOGGER.error("A task executed failed.",e);
        }
    }
}
