package com;

public class WaitNotify {


    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();


        Thread t1 = new Thread(() -> {

            synchronized (lock) {
                System.out.println("t1 获得锁定，进入等待状态");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1得到通知");

            }
        });
        Thread t2 = new Thread(() -> {

            synchronized (lock) {

                System.out.println("通知t1");
                lock.notify();
            }
        });
        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
