package com.demo.proxy;

import com.demo.MethodLog;

public class UserServiceImpl implements UserService{

    @Override
    public void add(@MethodLog String name) {
        System.out.println("UserService接口add方法被调用 inputParam= " + name);
    }
}
