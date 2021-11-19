package com.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author TomShiDi
 * @description
 * @date 2021/11/19 20:56
 **/
public class StreamMain {
    /**
     * 虽然stream.forEach是单独拿出来调用，但是实际上它仍然属于b.stream().filter(s -> !a.contains(s))这一条流上的操作。毕竟stream是同一个，这很好理解。而实际上流的操作是分层执行的，对于这里的场景，具体来说就是：
     * 虽然stream.forEach(PersonMain::func)这一行只有一个forEach操作，但实际上每次forEach仍然会去执行之前的filter。stream并不是一个最终态，它只是表示一个中间态，在没有调用类似collect前都不会实际进行计算。
     * 所以，当a里面加入第一个3之后，第二个3再次执行forEach前会执行前面的filter，自然的此时a中是【1,2,3】，filter是false，导致第二次的3无法继续执行forEach，后续的4执行filter是true，也就成功加入到了a中。最终结果就是原本应该是执行【3,3,4】，却因为第一个3加入到a中后，导致第二个3执行filter返回false无法执行后续的forEach，也就无法add到a中了。
     */
    private static List<String> a = new ArrayList<>();

    public static void main(String[] args) {
        List<String> c = new ArrayList<>();
        a.add("1");
        a.add("2");
        List<String> b = new ArrayList<>();
        b.add("1");
        b.add("2");
        b.add("3");
        b.add("3");
        b.add("4");
        Stream<String> stream = b.stream().filter(s -> {
            System.out.println("Stream流过滤器执行：" + s);
            return !a.contains(s);
        });
        stream.forEach(StreamMain::func);
        System.out.println(c);
    }

    public static void func(String s) {
        System.out.println(s);
        a.add(s);
    }
}
