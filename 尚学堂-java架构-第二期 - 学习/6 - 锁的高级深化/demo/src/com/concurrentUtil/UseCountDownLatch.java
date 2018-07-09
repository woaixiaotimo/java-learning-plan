package com.concurrentUtil;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {


    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入线程t1" + "等待其他线程完成。。。");
                    countDownLatch.await();
                    System.out.println("t1线程继续执行。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2线程初始化操作。。。");

                    Thread.sleep(3000);
                    System.out.println("t2线程初始化完成，通知t1线程继续。。。");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3线程初始化操作。。。");

                    Thread.sleep(3000);
                    System.out.println("t3线程初始化完成，通知t1线程继续。。。");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

    }

}
