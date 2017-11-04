package com.frame.schedule.entity;

import com.frame.core.components.BaseEntity;

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
    public enum Status{
        PAUSED,WORKING;
    }
    
    @NotNull
    private String taskCode;
    @NotNull
    private String taskName;
    @NotNull
    private String taskCron;
    
    private Long execNum=0L;
    
    private String status;
    
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
                       ", id=" + id +
                       ", version=" + version +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}