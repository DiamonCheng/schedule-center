package com.frame.schedule.sdk;

import com.frame.schedule.sdk.listener.AbstractScheduleListener;
import com.frame.schedule.sdk.listener.DefaultMessageListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public class TaskMqProcessor {
    private String startTaskTopic;
    private String taskFinishTopic;
    private String consumerGroup;
    private String producerGroup;
    private String nameServAddr;
    
    private DefaultMQProducer producer;
    private DefaultMQPushConsumer consumer;
    
    private Map<String,AbstractScheduleListener> tagListenerMap;
    
    private DefaultMessageListener defaultMessageListener;
    
    public TaskMqProcessor(){
        initProperties();
    }
    
    @PostConstruct
    public void startup(){
        initProducer();
        initConsumer();
    }
    
    @PreDestroy
    public void shutdown(){
        producer.shutdown();
        consumer.shutdown();
    }
    
    private void initConsumer(){
        if (consumer==null){
            consumer=new DefaultMQPushConsumer(consumerGroup);
            consumer.setNamesrvAddr(nameServAddr);
            assert tagListenerMap != null:"Please setup property tagListenerMap";
            StringBuilder sb=new StringBuilder();
            for (String tag:tagListenerMap.keySet()){
                sb.append(tag).append("||");
            }
            String tags=sb.substring(0,sb.length()-2);
            Map<String,String> subscription=new HashMap<>();
            subscription.put(startTaskTopic,tags);
            consumer.setSubscription(subscription);
            if (defaultMessageListener==null){
                defaultMessageListener=new DefaultMessageListener();
                defaultMessageListener.setTagListenerMap(tagListenerMap);
            }
            consumer.setMessageListener(defaultMessageListener);
            for (AbstractScheduleListener listener:tagListenerMap.values()){
                listener.setTaskFinishTopic(taskFinishTopic);
                listener.setProducer(producer);
            }
        }
        try {
            consumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    private void initProducer(){
        if (producer==null){
            producer=new DefaultMQProducer(producerGroup);
            producer.setNamesrvAddr(nameServAddr);
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
    
 
    private void initProperties() throws RuntimeException {
        Properties properties=new Properties();
        
        try(InputStream inputStream=this.getClass().getResourceAsStream("/schedule.properties");
            InputStreamReader reader=new InputStreamReader(inputStream,"UTF-8")
        ){
            properties.load(reader);
            this.consumerGroup=properties.getProperty("mq.consumer.group");
            this.producerGroup=properties.getProperty("mq.producer.group");
            this.startTaskTopic=properties.getProperty("start.task.topic");
            this.taskFinishTopic=properties.getProperty("task.finish.topic");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    public TaskMqProcessor setStartTaskTopic(String startTaskTopic) {
        this.startTaskTopic = startTaskTopic;
        return this;
    }
    
    public TaskMqProcessor setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
        return this;
    }
    
    public TaskMqProcessor setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
        return this;
    }
    
    public TaskMqProcessor setNameServAddr(String nameServAddr) {
        this.nameServAddr = nameServAddr;
        return this;
    }
    
    public TaskMqProcessor setProducer(DefaultMQProducer producer) {
        this.producer = producer;
        return this;
    }
    
    public TaskMqProcessor setConsumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
        return this;
    }
    
    public TaskMqProcessor setTagListenerMap(Map<String, AbstractScheduleListener> tagListenerMap) {
        this.tagListenerMap = tagListenerMap;
        return this;
    }
}
