package com.frame.schedule.sdk.listener;

import com.frame.schedule.sdk.structure.ScheduleTaskMessage;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/13.
 */
public class DefaultScheduleListener extends AbstractScheduleListener {
    
//    private Gson
    
    private Object executor;
    
    @Override
    public void processTask(ScheduleTaskMessage scheduleTaskMessage) {
    
    }
    
    public DefaultScheduleListener setExecutor(Object executor) {
        this.executor = executor;
        return this;
    }
}
