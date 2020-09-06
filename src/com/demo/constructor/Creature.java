package com.demo.constructor;

public class Creature {

    private String name;

    private Class<?> catogory;

    public Creature() {
    }

    public Creature(String name, Class<?> catogory) {
        this.name = name;
        this.catogory = catogory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getCatogory() {
        return catogory;
    }

    public void setCatogory(Class<?> catogory) {
        this.catogory = catogory;
    }
}
