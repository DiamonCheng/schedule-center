package com.frame.schedule.service.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/9.
 */
@Service
public class TaskInstanceIdHolder {
    @Value("${schedule.instance.id}")
    private String instanceId;
    @Value("${server.port}")
    private String serverPort;

    
    @PostConstruct
    public void init(){
        if (StringUtils.isEmpty(instanceId)){
            try {
                instanceId = InetAddress.getLocalHost().getHostName()+":"+serverPort;
            } catch (UnknownHostException e) {
                throw new IllegalStateException("Can not find localhost to generate the instance id, please set the instance id ('schedule.instance.id') command line argument manually.",e);
            }
        }
    }
    
    public String getInstanceId() {
        return instanceId;
    }
}
