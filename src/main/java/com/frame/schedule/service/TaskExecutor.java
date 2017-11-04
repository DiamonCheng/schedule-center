package com.frame.schedule.service;

import com.frame.schedule.entity.Schedulable;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.service.core.ScheduleExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
public class TaskExecutor extends ScheduleExecutor<TaskEntity> {
    private static final Logger LOGGER= LoggerFactory.getLogger(TaskExecutor.class);
    @Override
    public void execute(Schedulable schedulable) {
        LOGGER.warn("A task executed,"+schedulable);
    }
}
