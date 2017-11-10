package com.frame.schedule.entity;

import com.frame.core.components.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/9.
 */
@Entity
@Table(name = "sc_task_exception")
public class TaskExceptionEntity extends BaseEntity {
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name="null"))
    private TaskRecordEntity taskRecordEntity;
    
    /**
     * 表示这一条执行记录的第几次尝试，1 代表初试，以后没重试一次递增一。
     */
    private Long recordExecuteCount=0L;
    
    @Column
    @Type(type="text")
    private String content;
    
    public TaskRecordEntity getTaskRecordEntity() {
        return taskRecordEntity;
    }
    
    public TaskExceptionEntity setTaskRecordEntity(TaskRecordEntity taskRecordEntity) {
        this.taskRecordEntity = taskRecordEntity;
        return this;
    }
    
    public long getRecordExecuteCount() {
        return recordExecuteCount;
    }
    
    public TaskExceptionEntity setRecordExecuteCount(long recordExecuteCount) {
        this.recordExecuteCount = recordExecuteCount;
        return this;
    }
    
    public String getContent() {
        return content;
    }
    
    public TaskExceptionEntity setContent(String content) {
        this.content = content;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "TaskExceptionEntity{" +
                       "taskRecordEntity=" + taskRecordEntity +
                       ", recordExecuteCount=" + recordExecuteCount +
                       ", content='" + content + '\'' +
                       ", id=" + id +
                       ", version=" + version +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}
