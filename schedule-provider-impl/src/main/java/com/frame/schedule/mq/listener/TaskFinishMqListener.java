package com.frame.schedule.mq.listener;

import com.frame.schedule.sdk.structure.ScheduleTaskMessage;
import com.frame.schedule.sdk.util.MessageTransferUtil;
import com.frame.schedule.service.impl.TaskRecordService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public class TaskFinishMqListener implements MessageListenerConcurrently {
    @Autowired
    private TaskRecordService taskRecordService;
    
    @Value("${task.finish.topic}")
    private String taskFinishTopic;
    
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt message:msgs){
            assert message.getTopic().equals(taskFinishTopic):"Message with topic:"+message.getTopic()+" sent here but can not be consumed.";
            ScheduleTaskMessage scheduleTaskMessage=MessageTransferUtil.getInstance().getScheduleTaskMessage(message.getBody());
            taskRecordService.taskFinish(scheduleTaskMessage);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
