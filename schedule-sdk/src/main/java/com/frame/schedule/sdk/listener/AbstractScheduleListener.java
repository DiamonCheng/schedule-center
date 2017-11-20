package com.frame.schedule.sdk.listener;

import com.frame.schedule.sdk.structure.ScheduleTaskMessage;
import com.frame.schedule.sdk.util.MessageTransferUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public abstract class AbstractScheduleListener{
    private final Logger logger = LoggerFactory.getLogger(this.getClass() );
    
    private ExecutorService executorService = Executors.newCachedThreadPool();
    
    private DefaultMQProducer producer;
    
    private String taskFinishTopic;
    
    public void consumeMessage(MessageExt message){
        try {
            byte[] body=message.getBody();
            final ScheduleTaskMessage scheduleTaskMessage= MessageTransferUtil.getInstance().getScheduleTaskMessage(body);
            logger.info("Schedule task message received,"+scheduleTaskMessage);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        processTask(scheduleTaskMessage.clone());
                        scheduleTaskMessage.setSuccess(true);
                    } catch (Throwable e) {
                        logger.error("A task execute failed. "+ scheduleTaskMessage,e);
                        scheduleTaskMessage.setSuccess(false);
                    } finally {
                        sendMessage(scheduleTaskMessage);
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private void sendMessage(ScheduleTaskMessage scheduleTaskMessage){
        RuntimeException ex=new RuntimeException("A task finish and send message failed! "+scheduleTaskMessage);
        for (int i=0;i<5;i++){
            try {
                producer.send(new Message(
                     taskFinishTopic,
                     scheduleTaskMessage.getTaskTag(),
                     "TASK_ID:"+scheduleTaskMessage.getTaskRecordId()+"-RE:"+scheduleTaskMessage.isSuccess(),
                     MessageTransferUtil.getInstance().getMessageBody(scheduleTaskMessage)));
                return;
            } catch (Exception e) {
                ex.addSuppressed(e);
            }
        }
        throw ex;
    }
    
    public abstract void processTask(ScheduleTaskMessage scheduleTaskMessage) throws Throwable;
    
    
    public AbstractScheduleListener setProducer(DefaultMQProducer producer) {
        this.producer = producer;
        return this;
    }
    
    public AbstractScheduleListener setTaskFinishTopic(String taskFinishTopic) {
        this.taskFinishTopic = taskFinishTopic;
        return this;
    }
}
