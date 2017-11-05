package com.frame.schedule.service.core;

import com.frame.schedule.entity.Schedulable;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleOperator {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleOperator.class);
	
	public static final String JOB_MAP_DATA_KEY=ScheduleOperator.class+".JOB_MAP_DATA_KEY";
	
	private Class<? extends ScheduleExecutor> scheduleExecutorClass ;
	
	private Scheduler scheduler;
	
	/** 
     * 添加任务 
     *  
     * @param schedule Schedulable
     * @throws SchedulerException 
     */  
    public void addSchedule(Schedulable schedule) throws SchedulerException {
        if (schedule == null) {  
            return;  
        }  
  
        logger.debug("addTask:{}",schedule);
        TriggerKey triggerKey = TriggerKey.triggerKey(schedule.getKey(), schedule.getGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
  
        // 不存在，创建一个  
        if (null == trigger) {  

            JobDetail jobDetail = JobBuilder.newJob(scheduleExecutorClass).withIdentity(schedule.getKey(), schedule.getGroup()).build();
  
            jobDetail.getJobDataMap().put(JOB_MAP_DATA_KEY, schedule);  
  
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getCronExpression());
  
            trigger = TriggerBuilder.newTrigger().withIdentity(schedule.getKey(), schedule.getGroup()).withSchedule(scheduleBuilder).build();  
            scheduler.scheduleJob(jobDetail, trigger);  
        } else {
            // Trigger已存在，那么更新相应的定时设置  
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getCronExpression());
  
            // 按新的cronExpression表达式重新构建trigger  
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();  
  
            // 按新的trigger重新设置job执行  
            scheduler.rescheduleJob(triggerKey, trigger); 
        }
        if(schedule.isPaused()){
            JobKey jobKey = JobKey.jobKey(schedule.getKey(), schedule.getGroup());
            scheduler.pauseJob(jobKey);
        }
     
    }
    
    /** 
     * 删除一个job 
     *  
     */
    public void deleteSchedule(Schedulable schedule) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(schedule.getKey(), schedule.getGroup());
        scheduler.deleteJob(jobKey);
        logger.debug("deleteTask:{}",schedule);
    }  
  
    /** 
     * 立即执行job 
     *  
     */
    public void invokeScheduleAsync(Schedulable schedule) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(schedule.getKey(), schedule.getGroup());
        scheduler.triggerJob(jobKey);
        logger.debug("invokeScheduleAsync:{}",schedule);
    }

    public void invokeShceduleSync(Schedulable schedule){
        logger.debug("invokeScheduleAsync:{}",schedule);
        try {
            scheduleExecutorClass.newInstance().execute(schedule,null);
        } catch (Exception e) {
          throw new RuntimeException("",e);
        }
    }
    /** 
     * 更新job时间表达式 
     *  
     */
    public void updateScheduleCron(Schedulable schedule) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(schedule.getKey(), schedule.getGroup());
        scheduler.deleteJob(jobKey);
        
        JobDetail jobDetail = JobBuilder.newJob(scheduleExecutorClass).withIdentity(schedule.getKey(), schedule.getGroup()).build();
        jobDetail.getJobDataMap().put(JOB_MAP_DATA_KEY, schedule);
        TriggerKey triggerKey = TriggerKey.triggerKey(schedule.getKey(), schedule.getGroup());
  
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
  
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getCronExpression());
        if(trigger==null){
        	trigger = TriggerBuilder.newTrigger().withIdentity(schedule.getKey(), schedule.getGroup()).withSchedule(scheduleBuilder).build();
        }else{
        	trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();  
        }
        scheduler.scheduleJob(jobDetail, trigger);
        logger.debug("updateScheduleCron:{}",schedule);
    }
    
    /** 
     * 暂停一个job 
     *  
     */
    public void pauseSchedule(Schedulable schedule) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(schedule.getKey(), schedule.getGroup());
        scheduler.pauseJob(jobKey);
        logger.debug("pauseSchedule:{}",schedule);
    }  
  
    /** 
     * 恢复一个job 
     *  
     */
    public void resumeSchedule(Schedulable schedule) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(schedule.getKey(), schedule.getGroup());
        scheduler.resumeJob(jobKey);
        logger.debug("resumeSchedule:{}",schedule);
        
    }
    
    public ScheduleOperator setScheduleExecutorClass(Class<? extends ScheduleExecutor> scheduleExecutorClass) {
        this.scheduleExecutorClass = scheduleExecutorClass;
        return this;
    }
    
    public ScheduleOperator setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
        return this;
    }
}
