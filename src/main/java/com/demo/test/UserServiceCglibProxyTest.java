package com.demo.test;

import com.demo.proxy.UserService;
import com.demo.proxy.UserServiceCglibProxy;
import com.demo.proxy.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;


public class UserServiceCglibProxyTest {

    @Test
    public void doTest() {
        UserServiceCglibProxy userServiceCglibProxy = new UserServiceCglibProxy(UserServiceImpl.class);
        UserService userService = (UserService) userServiceCglibProxy.getInstance();
        userService.add("TomShiDi");
//        System.out.println("Class: " + UserServiceImpl.class.toGenericString());

    }
}
