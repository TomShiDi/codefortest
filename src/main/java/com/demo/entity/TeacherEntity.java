package com.demo.entity;

public class TeacherEntity extends PeopleEntity{
    private String id;

    private String type;

    private String systemNumber;

    private String enterDate;

    private String institution;

    public TeacherEntity() {
    }

    public TeacherEntity(String name, String age, String sex, String address, String connection, String id, String type, String systemNumber, String enterDate, String institution) {
        super(name, Integer.parseInt(age), sex, address, connection);
        this.id = id;
        this.type = type;
        this.systemNumber = systemNumber;
        this.enterDate = enterDate;
        this.institution = institution;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
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

    @Override
    public String toString() {
        return "TeacherEntity{" +
                super.toString() +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", systemNumber='" + systemNumber + '\'' +
                ", enterDate='" + enterDate + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }
}
