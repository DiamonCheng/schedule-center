package com.frame.schedule.entity;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
public interface Schedulable {
    String getCronExpression();
    String getKey();
    String getGroup();
    boolean isPaused();
}
