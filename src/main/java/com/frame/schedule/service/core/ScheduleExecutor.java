package com.frame.schedule.service.core;

import com.frame.schedule.entity.Schedulable;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/8/21.
 */
public abstract class ScheduleExecutor<T extends Schedulable> implements Job {
   

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object obj=context.getMergedJobDataMap()
                           .get(ScheduleOperator.JOB_MAP_DATA_KEY);
        execute((T) obj);
        
    }

    public abstract void execute(T scheWdulable);

}
