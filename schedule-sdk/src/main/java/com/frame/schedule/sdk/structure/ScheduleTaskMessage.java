package com.frame.schedule.sdk.structure;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/7.
 */
public class ScheduleTaskMessage implements Serializable,Cloneable{
    private Long taskRecordId;
    private String taskTopic;
    private String taskTag;
    private String taskCode;
    private String taskParam;
    private Date planStartTime;
    
    private boolean isSuccess;
    private String errorContent;
    
    public String getTaskTopic() {
        return taskTopic;
    }
    
    public ScheduleTaskMessage setTaskTopic(String taskTopic) {
        this.taskTopic = taskTopic;
        return this;
    }
    
    public String getTaskTag() {
        return taskTag;
    }
    
    public ScheduleTaskMessage setTaskTag(String taskTag) {
        this.taskTag = taskTag;
        return this;
    }
    
    public String getTaskCode() {
        return taskCode;
    }
    
    public ScheduleTaskMessage setTaskCode(String taskCode) {
        this.taskCode = taskCode;
        return this;
    }
    
    public String getTaskParam() {
        return taskParam;
    }
    
    public ScheduleTaskMessage setTaskParam(String taskParam) {
        this.taskParam = taskParam;
        return this;
    }
    
    public Date getPlanStartTime() {
        return planStartTime;
    }
    
    public ScheduleTaskMessage setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
        return this;
    }
    
    public boolean isSuccess() {
        return isSuccess;
    }
    
    public ScheduleTaskMessage setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }
    
    public String getErrorContent() {
        return errorContent;
    }
    
    public ScheduleTaskMessage setErrorContent(String errorContent) {
        this.errorContent = errorContent;
        return this;
    }
    
    @Override
    public ScheduleTaskMessage clone(){
        try {
            return (ScheduleTaskMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Long getTaskRecordId() {
        return taskRecordId;
    }
    
    public ScheduleTaskMessage setTaskRecordId(Long taskRecordId) {
        this.taskRecordId = taskRecordId;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "ScheduleTaskMessage{" +
                       "taskRecordId=" + taskRecordId +
                       ", taskTopic='" + taskTopic + '\'' +
                       ", taskTag='" + taskTag + '\'' +
                       ", taskCode='" + taskCode + '\'' +
                       ", taskParam='" + taskParam + '\'' +
                       ", planStartTime='" + planStartTime + '\'' +
                       ", isSuccess=" + isSuccess +
                       ", errorContent='" + errorContent + '\'' +
                       '}';
    }
}
