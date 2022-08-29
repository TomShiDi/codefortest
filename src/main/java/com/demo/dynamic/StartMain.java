package com.demo.dynamic;

/**
 * @author TomShiDi
 * @description
 * @date 2022/3/13 18:14
 **/
public class StartMain {

    public static void main(String[] args) {
        A c = new C();
        c.print();
    }

    static class A {
        public void print() {
            System.out.println("A");
        }
    }

    static class C extends A {
        @Override
        public void print() {
            System.out.println("C");
        }
    }

}
