package com.concurrentUtil.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReentrantLock {


    private Lock lock = new ReentrantLock();
    //使用Condition配合Lock完成等待通知模型
    private Condition condition = lock.newCondition();

    public void method1() {

        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入method1..");
            condition.await();
            Thread.sleep(1000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出method1..");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {

        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入method2..");
            Thread.sleep(2000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出method2..");
            Thread.sleep(1000);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final UseReentrantLock use = new UseReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                use.method1();
//                use.method2();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                use.method2();
            }
        }, "t2");

        t1.start();
        Thread.sleep(200);
        t2.start();
    }

}
