package com.frame.schedule.service.impl;

import com.frame.core.dao.GeneralDao;
import com.frame.core.utils.ReflectUtil;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.entity.TaskRecordEntity;
import com.frame.schedule.service.TaskMessageSendService;
import com.frame.schedule.service.TaskService;
import com.frame.schedule.service.core.ScheduleOperator;
import org.hibernate.HibernateException;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
@Service
public class TaskServiceImpl implements TaskService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="staticScheduleOperator")
    private ScheduleOperator scheduleOperator;
    
    @Autowired
    private GeneralDao generalDao;
    
    @Autowired
    private TaskMessageSendService taskMessageSendService;
    
    @Override
    @PostConstruct
    public void startup() throws SchedulerException {
        List<?> list=generalDao.getHibernateTemplate().<TaskEntity>find("from com.frame.schedule.entity.TaskEntity");
        for (Object object:list) {
            if (object instanceof TaskEntity){
                TaskEntity taskEntity= (TaskEntity) object;
                scheduleOperator.addSchedule(ReflectUtil.cloneObject(taskEntity));
            }
        }
    }
    
    @Override
    public void updateCron(TaskEntity taskEntity) {
        try {
            scheduleOperator.updateScheduleCron(ReflectUtil.cloneObject(taskEntity));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void addTask(TaskEntity entity){
        try {
            scheduleOperator.addSchedule(ReflectUtil.cloneObject(entity));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void deleteTask(TaskEntity taskEntity){
        try {
            scheduleOperator.deleteSchedule(taskEntity);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void pauseTask(TaskEntity taskEntity){
        taskEntity=generalDao.getHibernateTemplate().get(TaskEntity.class,taskEntity.getId());
        taskEntity.setStatus(TaskEntity.Status.PAUSED.toString());
    }
    @Override
    public void resumeTask(TaskEntity taskEntity){
        taskEntity=generalDao.getHibernateTemplate().get(TaskEntity.class,taskEntity.getId());
        taskEntity.setStatus(TaskEntity.Status.WORKING.toString());
    }
    @Override
    public void executeTask(TaskEntity taskEntity, JobExecutionContext jobExecutionContext){
        try {
            generalDao.getSessionFactory().getCurrentSession().refresh(taskEntity);
        } catch (HibernateException e) {
            logger.warn("Cannot find a task information, may be it has been deleted. TaskCode:"+taskEntity.getTaskCode());
            return;
        }
        if (!TaskEntity.Status.WORKING.toString().equals(taskEntity.getStatus())){
            logger.info("Task:"+taskEntity.getTaskCode()+" not in a working status, skip this execution.");
            return;
        }
        long execNum=taskEntity.getExecNum()+1;
        taskEntity.setExecNum(execNum);
        generalDao.getHibernateTemplate().update(taskEntity);
        TaskRecordEntity taskRecordEntity=new TaskRecordEntity()
                                                  .setPlanStartDate(jobExecutionContext.getScheduledFireTime())
                                                  .setTaskExecuteCode(taskEntity.getTaskCode()+":"+execNum)
                                                  .setTask(taskEntity)
                                                  .setTaskExecuteParam(taskEntity.getParamTemplate())
                                                  .setStatus(TaskRecordEntity.Status.EXECUTEING.toString())
                                                  .setStartDateTime(new Date());
        generalDao.getHibernateTemplate().save(taskRecordEntity);
        //刷新数据库以判断taskRecordEntity是否已经加锁，若抛出唯一键异常则为其他实例已经执行此任务，该任务会被抛弃，这个逻辑是导致死锁的很大的原因
        try {
            generalDao.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
        } catch (HibernateException e) {
            logger.warn("Obtain task lock failed. taskLockCode:"+taskRecordEntity.getTaskExecuteCode());
        }
        try {
            taskMessageSendService.sendTaskMessage(taskRecordEntity);
        } catch (Exception e) {
            logger.error("A task execute failed with its start message not send.",e);
            taskRecordEntity.setStatus(TaskRecordEntity.Status.WAIT_RETRY.toString());
        }
    }
}
