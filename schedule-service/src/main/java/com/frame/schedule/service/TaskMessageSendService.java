package com.frame.schedule.service;

import com.frame.schedule.entity.TaskRecordEntity;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public interface TaskMessageSendService {
    void sendTaskMessage(TaskRecordEntity taskRecordEntity);
}
