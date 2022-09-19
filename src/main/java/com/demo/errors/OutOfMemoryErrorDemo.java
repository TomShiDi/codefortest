package com.demo.errors;



import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * OOM异常
 */
public class OutOfMemoryErrorDemo {
    public static void main(String[] args) {

    }

    /**
     * 堆内存溢出
     */
    public static void oomJavaHeapSpace() {
        // -Xmx10M
        byte[] byteArr = new byte[20 * 1024 * 1024];
    }

    /**
     * GC回收时间过长时会抛出的异常，过长的定义是：超过98%得到时间用来做GC但仅回收了不到2%的堆内存。
     * 如果不抛出该异常，GC清理的那一点内存会立刻被占用，迫使再次GC，这样会形成恶性循环，使得CPU使用率一直100%，而GC却没有任何成果。
     */
    public static void oomGcOverhead() {
        List<String> list = new ArrayList<>();
        try {
            // -Xmx10M
            for (int i = 0; i < 100000; i++) {
                list.add(new String("Hello World " + i).intern());
            }
        } catch (Throwable throwable) {
            System.out.println("list中的元素个数：" + list.size());
            throwable.printStackTrace();
        }
    }

    /**
     * 直接引用JVM外的物理内存发生了溢出
     * NIO中经常使用ByteBuffer写入或读取数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/O模式。
     * 它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据。
     * ByteBuffer.allocate(capability)用于分配JVM堆内存，属于GC管辖范畴，与Native堆交互需要拷贝所以速度相对较慢。
     * ByteBuffer.allocateDirect(capability) 分配OS本地内存，不属于GC管辖范围，与Native堆交互不需要拷贝所以速度较快。
     * 如果不断分配本地内存，堆内存却很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象就不会被回收，这是堆内存很充足，但是本地内存被耗尽了，再次尝试分配本地内存时就会出现OOM。
     */
    public static void oomDirectBufferMemory() {
        List<ByteBuffer> byteBufferList = new ArrayList<>();
//        System.out.printf("最大直接内存大小：%d\n", VM.maxDirectMemory() / 1024 / 1024);
        try {
            // -Xmx10M -XX:MaxDirectMemorySize=5M
            for (int i = 0; i < 10 * 1024; i++) {
                byteBufferList.add(ByteBuffer.allocateDirect(1024));
            }
        } catch (Throwable throwable) {
            System.out.println("list中的ByteBuffer元素个数：" + byteBufferList.size());
            throwable.printStackTrace();
        }
    }
}
