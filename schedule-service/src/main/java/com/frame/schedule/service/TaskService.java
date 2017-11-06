package com.frame.schedule.service;

import com.frame.core.dao.GeneralDao;
import com.frame.core.utils.ReflectUtil;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.entity.TaskRecordEntity;
import com.frame.schedule.service.core.ScheduleOperator;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
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
public class TaskService {
    @Resource(name="staticScheduleOperator")
    private ScheduleOperator scheduleOperator;
    
    @Autowired
    private GeneralDao generalDao;
    
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    
    @PostConstruct
    public void startup() throws SchedulerException {
        List<?> list=generalDao.getHibernateTemplate().<TaskEntity>find("from TaskEntity");
        for (Object object:list) if (object instanceof TaskEntity){
            TaskEntity taskEntity= (TaskEntity) object;
            scheduleOperator.addSchedule(ReflectUtil.cloneObject(taskEntity));
        }
    }
    
    public void updateCron(TaskEntity taskEntity) {
        try {
            scheduleOperator.updateScheduleCron(ReflectUtil.cloneObject(taskEntity));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addTask(TaskEntity entity){
        try {
            scheduleOperator.addSchedule(ReflectUtil.cloneObject(entity));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deleteTask(TaskEntity taskEntity){
        try {
            scheduleOperator.deleteSchedule(taskEntity);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void pauseTask(TaskEntity taskEntity){
        taskEntity=generalDao.getHibernateTemplate().get(TaskEntity.class,taskEntity.getId());
        taskEntity.setStatus(TaskEntity.Status.PAUSED.toString());
        try {
            scheduleOperator.pauseSchedule(taskEntity);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    public void resumeTask(TaskEntity taskEntity){
        taskEntity=generalDao.getHibernateTemplate().get(TaskEntity.class,taskEntity.getId());
        taskEntity.setStatus(TaskEntity.Status.WORKING.toString());
        try {
            scheduleOperator.resumeSchedule(taskEntity);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    void executeTask(TaskEntity taskEntity, JobExecutionContext jobExecutionContext){
        generalDao.getSessionFactory().getCurrentSession().refresh(taskEntity);
        long execNum=taskEntity.getExecNum()+1;
        taskEntity.setExecNum(execNum);
        generalDao.getHibernateTemplate().update(taskEntity);
        generalDao.getHibernateTemplate().save(
                new TaskRecordEntity()
                        .setPlanStartDate(jobExecutionContext.getScheduledFireTime())
                        .setTaskExecuteCode(taskEntity.getTaskCode()+":"+execNum)
                        .setTask(taskEntity)
                        .setStatus(TaskRecordEntity.Status.SCHEDULED.toString())
                        .setStartDateTime(new Date())
        );
        generalDao.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
    }
}
