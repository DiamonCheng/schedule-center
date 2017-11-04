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
        Schedulable schedulable =
                (Schedulable) context.getMergedJobDataMap()
                    .get(ScheduleOperator.JOB_MAP_DATA_KEY);
        execute(schedulable);
    }

    public abstract void execute(Schedulable scheWdulable);

}
