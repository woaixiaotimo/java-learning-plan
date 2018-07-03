package com.producerConsumer;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    //共享内存区
    private BlockingDeque<Data> queue = new LinkedBlockingDeque<Data>();

    //指示线程状态
    private volatile boolean isRuning = true;

    //id生成器
    private static AtomicInteger count = new AtomicInteger();

    //随机对象
    private static Random r = new Random();

    public Producer(BlockingDeque<Data> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while (isRuning) {
            try {
                //随机休眠
                Thread.sleep(r.nextInt(1000));
                //获取数据进行累计
                int id = count.incrementAndGet();

                Data data = new Data(Integer.toString(id), "数据" + id);
                System.out.println("当前生产线程：" + Thread.currentThread().getName() + ",获取了数据，id为：" + id + "，装载到内存缓冲区中。。。");
                if (!this.queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("提交缓冲区数据失败。。。");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop() {
        this.isRuning = false;
    }
}
