package com.demo.proxy;

import net.sf.cglib.proxy.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UserServiceCglibProxy implements MethodInterceptor {

    private Enhancer enhancer;

    private Map<Field, Annotation[]> superClassAnnotationsMap = new HashMap<>();

    public UserServiceCglibProxy(Class<?> cl) {
        this.enhancer = new Enhancer();
        enhancer.setSuperclass(cl);
        enhancer.setCallbacks(new Callback[]{this, NoOp.INSTANCE, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new MyCallBackFilter(cl));
        getSuperClassFieldAnnotation(cl);
    }

    public Object getInstance() {
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + "方法调用前");
        Object result = methodProxy.invokeSuper(target, args);
        System.out.println(method.getName() + "方法调用后");
        return result;
    }

    private void getSuperClassFieldAnnotation(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length > 0) {
                superClassAnnotationsMap.put(field, annotations);
            }
        }
    }
}
