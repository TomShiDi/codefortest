package com.demo.stringtest;

/**
 * @Author TomShiDi
 * @Since 2019/9/24
 * @Version 1.0
 */
public class DoMain {

    public static void main(String[] args) {
//        Test test = new Test();
//        test.fun(test.str, test.ch);
//        System.out.println(test.str + " and " + new String(test.ch));
        try {
            int i = 0;
            System.exit(0);
        }finally {
            System.out.println("执行finally");
        }
    }

    static class Test {
        String str = "hello";
        char[] ch = new char[]{'a', 'b', 'c'};

        public void fun(String str,char[] ch) {
            str = "world";
            ch[0] = 'd';
        }
    }

}
