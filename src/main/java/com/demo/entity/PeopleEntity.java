package com.demo.entity;

public class PeopleEntity {
    private String name;

    private Integer age;

    private String sex;

    private String address;

    private String connection;

    public PeopleEntity() {
    }

    public PeopleEntity(String name, Integer age, String sex, String address, String connection) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "PeopleEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", connection='" + connection + '\'' +
                '}';
    }
}
