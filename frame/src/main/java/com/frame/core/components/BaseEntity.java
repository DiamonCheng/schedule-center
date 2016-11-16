package com.frame.core.components;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author bin
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 主键ID自动生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    protected Integer version;

    /**
     * 创建时间
     */
    @Column(name = "create_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createDateTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date updateDateTime;

    /**
     * 用于鉴别初始化数据
     */
    @Column(name = "init_id")
    private Long initId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
