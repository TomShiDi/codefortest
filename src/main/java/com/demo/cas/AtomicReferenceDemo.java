package com.demo.cas;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        DemoData zhangsanData = new DemoData("张三", 1);
        DemoData lisiData = new DemoData("李四", 2);
        AtomicReference<DemoData> demoDataAtomicReference1 = new AtomicReference<>(zhangsanData);
//        AtomicReference<DemoData> demoDataAtomicReference2 = new AtomicReference<>(new DemoData("李四", 2));
        zhangsanData.setName("王五");
        System.out.printf("交换结果：%s，此时原本张三的数据已经修改为：%s\n", demoDataAtomicReference1.compareAndSet(zhangsanData, lisiData), zhangsanData.toString());
    }

    static class DemoData {
        private String name;

        private int age;

        public DemoData(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "DemoData{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
