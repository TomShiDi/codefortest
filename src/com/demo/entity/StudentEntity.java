package com.demo.entity;

public class StudentEntity extends PeopleEntity {
    private String id;

    private String enterDate;

    private String institution;

    private String systemNumber;

    private String major;

    private String type;

    public StudentEntity() {
    }

    public StudentEntity(String id, String enterDate, String institution,
                         String systemNumber, String major, String type,
                         String name,String age,String sex,String address,String connection) {
        super(name, Integer.parseInt(age), sex, address, connection);
        this.id = id;
        this.enterDate = enterDate;
        this.institution = institution;
        this.systemNumber = systemNumber;
        this.major = major;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                super.toString() +
                "id='" + id + '\'' +
                ", enterDate='" + enterDate + '\'' +
                ", institution='" + institution + '\'' +
                ", systemNumber='" + systemNumber + '\'' +
                ", major='" + major + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
