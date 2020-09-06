package com.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceInvocationHandler implements InvocationHandler {

    private Object target = null;


    public ServiceInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Proxy代理执行目标方法前");
        Object result = method.invoke(target,args);
        System.out.println("Proxy执行目标方法后");
        //经过代理后的method对象去掉了annotation属性
//        try {
//            Annotation[][] annotationSs = method.getParameterAnnotations();
//            for (Annotation[] annotations : annotationSs) {
//                for (Annotation annotation : annotations) {
//                    System.out.println("method params : " + annotation.toString());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.target.getClass().getInterfaces(), this);
    }
}
