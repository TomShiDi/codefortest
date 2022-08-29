package com.demo.annotation;


import com.demo.MethodLog;

@MethodLog(preExeMessage = "before testClass method execute",afterExeMessage = "after testClass method execute")
public class TestClass {

    private Class<?> containClass = null;

    private String name = "";

    private int age = 0;

    public TestClass() {
        if (this.getClass().isAnnotationPresent(MethodLog.class)) {
            MethodLog p = this.getClass().getAnnotation(MethodLog.class);
            this.name = p.value();
        }
    }

    public String toString(){
        if (!name.isEmpty()) {
            return "name: " + name + "\n" + "age: " + age;
        }
        return "null";
    }

    public Class<?> getContainClass() {
        return containClass;
    }

    public void setContainClass(Class<?> containClass) {
        this.containClass = containClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
