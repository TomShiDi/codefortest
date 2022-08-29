package com.demo.staticinner;

/**
 * @author TomShiDi
 * @description
 * @date 2022/3/2 20:54
 **/
public class InnerClassMain {
    public static void main(String[] args) {
        Inner inner = new InnerClassMain().new Inner();
    }

    public class Inner {

    }
}
