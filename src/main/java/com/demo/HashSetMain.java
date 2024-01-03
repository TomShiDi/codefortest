package com.demo;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author TomShiDi
 * @date 2021/11/19 22:15
 **/
public class HashSetMain {
    public static void main(String[] args) {
        // 创建HashSet对象
        HashSet<Person> hs = new HashSet<>();
        // 将Person对象存入集合
        hs.add(new Person("lisa", 21));
        hs.add(new Person("lisi", 32));
        hs.add(new Person("lisi", 33));
        hs.add(new Person("leilei", 31));
        hs.add(new Person("lusi", 25));
        hs.add(new Person("lusi", 25));
        // 遍历集合中的元素
        for (Person p : hs) {
            System.out.println(p);
        }
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // 重写hashCode方法，返回name属性的哈希值
        @Override
        public int hashCode() {
            return name.hashCode();
        }

        // 重写equals方法
        // 对于该用例，有三种处理方案

        /**
         * 常用方案一：基础的String比较，
         * 先判断对象是否都为Person类型，都为Person类型时，比较两个Person对象的name属性是否相同。
         * @param obj
         * @return true表示相等；false表示不是相等
         */
//        @Override
//        public boolean equals(Object obj) {
//            if (!(obj instanceof Person)) {
//                return false;
//            }
//            return this.name.equals(((Person) obj).name);
//        }

        /**
         * 方案二：这个方案有点绕，而且因为没有做类型判定，很容易出问题，不建议使用。
         * 第一层：obj是Person对象，那么obj.equals(name)又会走到下面这个equals重写方法中。
         * 第二层：由上一层得出，这一层是obj入参实际是String类型，也就是上面的name。到这里就相当于name.equals(this.name)了，
         *           所以这种equals也能实现要求。
         * @param obj 比较对象
         * @return true表示相等；false表示不是相等
         */
        @Override
        public boolean equals(Object obj) {
            return obj.equals(name);
        }

        /**
         * 方案三：这个方案得益于jvm的字符串常量池缓存机制。
         * main方法中加入到HashSet中的Person对象的name都是字符串常量
         * 也就意味着new Person("lisi", 32)与new Person("lisi", 33)这两个对象里的name字段实际上是指向的同一个内存地址
         * 那么使用 == 自然也就会返回true
         * @param obj
         * @return true表示相等；false表示不是相等
         */
//        @Override
//        public boolean equals(Object obj) {
//            if (!(obj instanceof Person)) {
//                return false;
//            }
//            return this.name == ((Person) obj).name;
//        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
