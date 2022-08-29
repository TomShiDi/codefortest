package com.demo.mybatisTest.entity;


import java.util.Date;

/**
 * @Author TomShiDi
 * @Since 2019/6/2
 * @Version 1.0
 */
public class ViolationRecordEntity {

    private Integer recordId;

    private Integer studentId;

    private String eventDescription;

    private String punishment;

    private String createTime;


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
