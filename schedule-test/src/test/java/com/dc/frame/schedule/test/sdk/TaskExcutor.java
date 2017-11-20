package com.dc.frame.schedule.test.sdk;

import com.frame.core.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/20.
 */
public class TaskExcutor {
    private final static Logger LOGGER= LoggerFactory.getLogger(TaskExcutor.class);
    public void execute1(UserEntity entity){
        LOGGER.info("TaskExecutor.execute1 invoked, param:"+entity);
    }
}
