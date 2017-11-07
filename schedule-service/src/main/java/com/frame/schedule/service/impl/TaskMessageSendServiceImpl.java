package com.frame.schedule.service.impl;

import com.frame.schedule.entity.TaskRecordEntity;
import com.frame.schedule.sdk.structure.ScheduleTaskMessage;
import com.frame.schedule.sdk.util.MessageTransferUtil;
import com.frame.schedule.service.TaskMessageSendService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
@Service
public class TaskMessageSendServiceImpl implements TaskMessageSendService {
    
    
    @Resource(name="taskMessageProducer")
    private DefaultMQProducer producer;
    
    @Override
    public void sendTaskMessage(TaskRecordEntity taskRecordEntity){
        ScheduleTaskMessage scheduleTaskMessage=
                new ScheduleTaskMessage()
                        .setPlanStartTime(taskRecordEntity.getPlanStartDate())
                        .setTaskParam(taskRecordEntity.getTaskExecuteParam())
                        .setTaskRecordId(taskRecordEntity.getId())
                        .setTaskTopic(taskRecordEntity.getTask().getTaskTopic())
                        .setTaskTag(taskRecordEntity.getTask().getTaskTag())
                        .setTaskCode(taskRecordEntity.getTask().getTaskCode());
        Message msg=new Message(
                scheduleTaskMessage.getTaskTopic(),
               scheduleTaskMessage.getTaskTag(),
               "TASK_ID:"+scheduleTaskMessage.getTaskRecordId(),
               MessageTransferUtil.getInstance().getMessageBody(scheduleTaskMessage));
    
        try {
            producer.send(msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
