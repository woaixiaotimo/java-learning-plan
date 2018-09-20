package ConcurrentUtilPackageDemo;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch示例
 *
 * 说明：
 * CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。
 * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。计数器初始值为线程的数量。
 * 当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成了任务，
 * 然后在CountDownLatch上等待的线程就可以恢复执行任务。
 *
 * 原理：使用CAS算法和线程空转完成等待
 *
 * */
public class CountDownLatch_Demo {

    final static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static Runnable buildRunable() {
        return () -> {
            System.out.println("线程名称：" + Thread.currentThread().getName());
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 结束");
        };
    }

    public static void main(String[] args) {
        new Thread(buildRunable(), "1").start();
        new Thread(buildRunable(), "2").start();
        new Thread(buildRunable(), "3").start();
    }


}
