package com.frame.schedule.entity;

import com.frame.core.components.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
@Table(name="sc_task",
        uniqueConstraints = @UniqueConstraint(columnNames = "taskCode"))
@Entity
public class TaskEntity extends BaseEntity implements Schedulable {
    public String getTaskType() {
        return taskType;
    }
    
    public TaskEntity setTaskType(String taskType) {
        this.taskType = taskType;
        return this;
    }
    
    public enum Status{
        PAUSED,WORKING
    }
    public enum TaskType{
        STATIC,DYNAMIC
    }
    
    @NotNull
    private String taskCode;
    @NotNull
    private String taskName;
    @NotNull
    private String taskCron;
    
    private Long execNum;
    
    private String status;
    
    @Type(type="text")
    private String paramTemplate;//TODO 预留字段，用于渲染参数
    
    @NotNull
    private String taskTopic;
    
    @NotNull
    private String taskTag;
    
    private String taskType=TaskType.STATIC.toString();
    
    public String getTaskCode() {
        return taskCode;
    }
    
    public TaskEntity setTaskCode(String taskCode) {
        this.taskCode = taskCode;
        return this;
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    public TaskEntity setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }
    
    public String getTaskCron() {
        return taskCron;
    }
    
    public TaskEntity setTaskCron(String taskCron) {
        this.taskCron = taskCron;
        return this;
    }
    
    public Long getExecNum() {
        return execNum;
    }
    
    public TaskEntity setExecNum(Long execNum) {
        this.execNum = execNum;
        return this;
    }
    
    public String getStatus() {
        return status;
    }
    
    public TaskEntity setStatus(String status) {
        this.status = status;
        return this;
    }
    
    @Override
    @Transient
    public String getCronExpression() {
        return taskCron;
    }
    
    @Override
    @Transient
    public String getKey() {
        return id.toString();
    }
    
    @Override
    @Transient
    public String getGroup() {
        return "STATIC";
    }
    
    @Override
    public boolean isPaused() {
        return Status.PAUSED.toString().equals(status);
    }
    
    public String getParamTemplate() {
        return paramTemplate;
    }
    
    public TaskEntity setParamTemplate(String paramTemplate) {
        this.paramTemplate = paramTemplate;
        return this;
    }
    
    public String getTaskTopic() {
        return taskTopic;
    }
    
    public TaskEntity setTaskTopic(String taskTopic) {
        this.taskTopic = taskTopic;
        return this;
    }
    
    public String getTaskTag() {
        return taskTag;
    }
    
    public TaskEntity setTaskTag(String taskTag) {
        this.taskTag = taskTag;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "TaskEntity{" +
                       "taskCode='" + taskCode + '\'' +
                       ", taskName='" + taskName + '\'' +
                       ", taskCron='" + taskCron + '\'' +
                       ", execNum=" + execNum +
                       ", status='" + status + '\'' +
                       ", paramTemplate='" + paramTemplate + '\'' +
                       ", taskTopic='" + taskTopic + '\'' +
                       ", taskTag='" + taskTag + '\'' +
                       ", taskType='" + taskType + '\'' +
                       ", id=" + id +
                       ", version=" + version +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}
