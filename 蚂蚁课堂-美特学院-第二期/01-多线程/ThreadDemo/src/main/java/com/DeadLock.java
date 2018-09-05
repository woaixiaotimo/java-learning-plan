package com;

/**
 * 死锁
 */
public class DeadLock {
    private static Object A = new Object();
    private static Object B = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程1开始执行...");
            synchronized (A) {
                try {
                    System.out.println("线程1拿到A锁");
                    //休眠两秒让线程2有时间拿到B锁
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("线程1拿到B锁");
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("线程2开始执行...");
            synchronized (B) {
                try {
                    System.out.println("线程2拿到B锁");
                    //休眠两秒让线程1有时间拿到A锁
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("线程2拿到A锁");
                }
            }
        }).start();

    }
}

