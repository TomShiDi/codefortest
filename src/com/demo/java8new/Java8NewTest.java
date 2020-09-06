package com.demo.java8new;

import java.util.Arrays;
import java.util.List;

public class Java8NewTest {

    private List<String> dataList = Arrays.asList("Tom", "ToKa", "YoXiNo");

    public void callInitialize() {
        //ContextInitialize为声明的函数式接口
        //等同于ContextInitialize contextInitialize = (item) -> System.out.println("initialize: " + item);
        ContextInitialize contextInitialize = this::initialize;

         //等同于dataList.forEach(item -> contextInitialize.doIt(item));
        dataList.forEach(contextInitialize::doIt);
    }

    private void initialize(String param) {
        System.out.println("initialize: " + param);
    }
}
