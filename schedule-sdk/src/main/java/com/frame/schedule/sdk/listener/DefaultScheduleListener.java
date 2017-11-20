package com.frame.schedule.sdk.listener;

import com.frame.schedule.sdk.structure.ScheduleTaskMessage;
import com.google.gson.Gson;

import java.lang.reflect.Method;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/13.
 */
public class DefaultScheduleListener extends AbstractScheduleListener {
    
    private Gson gson=new Gson();
    
    private Object executor;
    
    private Method invokeMethod;
    
    public DefaultScheduleListener(Object executor,String invokeMethodName){
        this.executor=executor;
        findMethod(invokeMethodName);
    }
    
    @Override
    public void processTask(ScheduleTaskMessage scheduleTaskMessage) throws Throwable{
        Class[] parameterTypes=invokeMethod.getParameterTypes();
        Object[] args=new Object[parameterTypes.length];
        if (scheduleTaskMessage.getTaskParam()!=null && !"".equals(scheduleTaskMessage.getTaskParam())){
            if (parameterTypes.length>0) {
                Object arg1=gson.fromJson(scheduleTaskMessage.getTaskParam(),parameterTypes[0]);
                args[0]=arg1;
            }
        }
        invokeMethod.invoke(executor,args);
    }
    
    public DefaultScheduleListener setGson(Gson gson) {
        this.gson = gson;
        return this;
    }
    
    private void findMethod(String invokeMethodName){
        Class<?> cls=executor.getClass();
        while(!Object.class.equals(cls)){
            Method[] methods=cls.getDeclaredMethods();
            for (Method method:methods){
                if (method.getName().equals(invokeMethodName)){
                    invokeMethod=method;
                    return;
                }
            }
            cls=cls.getSuperclass();
        }
        
    }
}
