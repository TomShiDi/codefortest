package com.demo.constructor;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Constructor;

public class ConstructClass {

    private Class<?> creature;

    public Object getClassInstance(String className) {
        ClassLoader classLoader = getClassLoader();
        Object object = null;
        try {
            Class<?> creature = classLoader.loadClass(className);
            this.creature = creature;
            object = creature.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }



    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ConstructClass.class.getClassLoader();
        }
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }

        return classLoader;
    }


    public static Object getInstanceByConstructor(String className) {
        ClassLoader classLoader = getClassLoader();
        @Nullable Class<?> creature = null;
        Constructor<?> constructor = null;
        try {
            creature = classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            constructor = creature.getConstructor(String.class, Class.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return constructor;
    }
}
