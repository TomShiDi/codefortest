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
     * 虽然stream.forEach是单独拿出来调用，但是实际上它仍然属于b.stream().filter(s -> !a.contains(s))这一条流上的操作。
     * 毕竟stream是同一个，这很好理解。Stream中的各种操作分为两大类：中间操作（filter、map等）和终端操作（collect、forEach等）。
     * 在终端操作调用前，中间操作都不会进行计算，相当于：1+1，只是列出了这个式子，并没有真的计算，知道加上了=号。
     * 对于这里的场景，具体来说就是：
     * 虽然stream.forEach(PersonMain::func)这一行只有一个forEach操作，但实际上每次forEach仍然会去执行之前的filter。
     * stream对象并不是一个最终态，它只是表示一个中间态，在没有调用类似collect前都不会实际进行计算。
     * 可以看到stream.forEach执行时，之前的filter中打印操作也被执行了
     * 所以，当a里面加入第一个3之后，第二个3再次执行forEach前会执行前面的filter，自然的此时a中是【1,2,3】，filter是false，导致第二次的3无法继续执行forEach，后续的4执行filter是true，也就成功加入到了a中。最终结果就是原本应该是执行【3,3,4】，却因为第一个3加入到a中后，导致第二个3执行filter返回false无法执行后续的forEach，也就无法add到a中了。
     */
    private static List<String> a = new ArrayList<>();

    public static void main(String[] args) {
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
        // 猜猜最后打印出的a的内容
        // 是[1, 2, 3, 4]
        System.out.println(a);
    }

    public static void func(String s) {
//        System.out.println(s);
        a.add(s);
    }
}
