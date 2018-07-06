package com.producerConsumer;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {

    private  BlockingDeque<Data> queue;

    private static Random r = new Random();

    public Consumer(BlockingDeque<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //获取数据
                Data data = this.queue.take();
                //进行数据处理
                Thread.sleep(r.nextInt(1000));
                System.out.println("当前消费线程：" + Thread.currentThread().getName() + ",消费成功，消费数据id为：" + data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
