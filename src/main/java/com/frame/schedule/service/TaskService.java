package com.frame.schedule.service;

import com.frame.core.dao.GeneralDao;
import com.frame.core.utils.ReflectUtil;
import com.frame.schedule.entity.TaskEntity;
import com.frame.schedule.service.core.ScheduleOperator;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
    
    public void addSchedule(TaskEntity entity){
        try {
            scheduleOperator.addSchedule(ReflectUtil.cloneObject(entity));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deleteSchedule(TaskEntity taskEntity){
        try {
            scheduleOperator.deleteSchedule(taskEntity);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    
}
