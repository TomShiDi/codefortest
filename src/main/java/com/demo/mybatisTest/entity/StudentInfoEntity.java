package com.demo.mybatisTest.entity;

import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/1
 * @Version 1.0
 */
public class StudentInfoEntity {
    private Integer id;

    private String studentName;

    private String sex;

    private Integer age;

    private List<ViolationRecordEntity> violationRecord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<ViolationRecordEntity> getViolationRecord() {
        return violationRecord;
    }

    public void setViolationRecord(List<ViolationRecordEntity> violationRecord) {
        this.violationRecord = violationRecord;
    }

    @Override
    public String toString() {
        return "StudentInfoEntity{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
