package com.frame.schedule.sdk.listener;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public class DefaultMessageListener implements MessageListenerConcurrently{
    private Map<String,AbstractScheduleListener> tagListenerMap;
    
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for(MessageExt message:msgs){
            String tag=message.getTags();
            final AbstractScheduleListener listener=tagListenerMap.get(tag);
            assert listener!=null:"Con not found listener by tag:"+tag;
            listener.consumeMessage(message);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
    
    public DefaultMessageListener setTagListenerMap(Map<String, AbstractScheduleListener> tagListenerMap) {
        this.tagListenerMap = tagListenerMap;
        return this;
    }
}
