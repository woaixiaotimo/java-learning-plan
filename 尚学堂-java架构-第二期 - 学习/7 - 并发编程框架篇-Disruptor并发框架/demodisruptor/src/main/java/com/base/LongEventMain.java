package com.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongEventMain {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //创建工厂
        LongEventFactory factory = new LongEventFactory();
        //创建bufferSize ，也就是RingBuffer大小必须是2的n次方
    }
}
