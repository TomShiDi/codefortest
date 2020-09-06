package com.demo.proxy;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MyCallBackFilter implements CallbackFilter {

    private Class<?> superClass;


    public MyCallBackFilter(Class<?> superClass) {
        this.superClass = superClass;
//        getSuperClassAnnotation(superClass);
    }

    @Override
    public int accept(Method method) {
        Method superClassMethod;
        Parameter[] parameters = null;
        Method[] methods = superClass.getDeclaredMethods();
        try {
            for (Method method1 : methods) {
                if (method1.getName().equals(method.getName())) {
                    superClassMethod = superClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    System.out.println(method.getName());
                    parameters = superClassMethod.getParameters();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (parameters != null && parameters.length > 0) {
            for (Parameter parameter : parameters) {
                Annotation[] annotations;
                if ((annotations = parameter.getAnnotations()).length > 0) {
                    for (Annotation annotation : annotations) {
                        System.out.println(parameter.getName() + ":" + annotation.annotationType().getName());
                    }
                }else {
                    System.out.println(parameter.getName());
                }
            }
            return 1;
        }
        return 0;
    }

}
