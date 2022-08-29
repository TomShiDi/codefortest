package com.demo.test;


import com.demo.MethodLog;
import com.demo.proxy.ServiceInvocationHandler;
import com.demo.proxy.UserService;
import com.demo.proxy.UserServiceImpl;
import org.junit.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceImplTest {

    @Test
    public void add() {
        UserService userService = new UserServiceImpl();
        ServiceInvocationHandler serviceInvocationHandler = new ServiceInvocationHandler(userService);
//        UserService com.demo.proxy = (UserService) serviceInvocationHandler.getProxy();
        UserService proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), serviceInvocationHandler);
        proxy.add("TomShiDi");

        Class<? extends UserService> cl = userService.getClass();
        try {
            Method method = cl.getMethod("add", String.class);
            Annotation[][] annotationss = method.getParameterAnnotations();
            for (Annotation[] annotations : annotationss) {
                for (Annotation annotation : annotations) {
                    System.out.println(": " + annotation.toString());
                }
            }
            Annotation annotation = method.getAnnotation(MethodLog.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}