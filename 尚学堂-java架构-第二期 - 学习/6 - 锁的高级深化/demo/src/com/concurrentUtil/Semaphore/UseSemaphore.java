package com.concurrentUtil.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UseSemaphore {

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //只能五个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        semaphore.acquire();
                        System.out.println("accessing:" + NO);
                        //模拟实际业务
                        Thread.sleep((long) (Math.random() * 10000));
                        //访问完成后释放
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(run);
        }
        Thread.sleep(10);


    }
}
