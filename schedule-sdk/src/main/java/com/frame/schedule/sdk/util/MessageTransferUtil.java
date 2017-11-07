package com.frame.schedule.sdk.util;

import com.frame.schedule.sdk.structure.ScheduleTaskMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public class MessageTransferUtil {
    private static MessageTransferUtil instance=new MessageTransferUtil();
    
    public static MessageTransferUtil getInstance() {
        return instance;
    }
    public byte[] getMessageBody(ScheduleTaskMessage scheduleTaskMessage){
        
        try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream)){
            objectOutputStream.writeObject(scheduleTaskMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public ScheduleTaskMessage getScheduleTaskMessage(byte[] messageBody){
        try (ObjectInputStream objectInputStream= new ObjectInputStream(new ByteArrayInputStream(messageBody))) {
            return (ScheduleTaskMessage) objectInputStream.readObject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

