package com.frame.schedule.service.impl;

import com.frame.core.dao.GeneralDao;
import com.frame.schedule.entity.TaskRecordEntity;
import com.frame.schedule.sdk.structure.ScheduleTaskMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
@Service
public class TaskRecordService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private GeneralDao generalDao;
    
    public void taskFinish(ScheduleTaskMessage scheduleTaskMessage){
        logger.info("A task receipt accepted."+scheduleTaskMessage);
        TaskRecordEntity taskRecordEntity=generalDao.getHibernateTemplate().get(TaskRecordEntity.class,scheduleTaskMessage.getTaskRecordId());
        assert taskRecordEntity!=null:"Con not find task record entity with id:"+scheduleTaskMessage.getTaskRecordId();
        taskRecordEntity.setFinishDateTime(new Date());
        if (scheduleTaskMessage.isSuccess()){
            taskRecordEntity.setStatus(TaskRecordEntity.Status.SUCCESS.toString());
        }else{
            taskRecordEntity.setStatus(TaskRecordEntity.Status.WAIT_RETRY.toString());
            //TODO process Error content
            logger.error("A task process failed with record: "+taskRecordEntity+"Error content:"+scheduleTaskMessage.getErrorContent());
        }
        generalDao.getHibernateTemplate().update(taskRecordEntity);
    }
}
