package com.concurrentUtil.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReentrantReadWriteLock {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入。。。");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入。。。");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    public static void main(String[] args) {
        final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        },"t3");
//        t1.start();
//        t2.start();
        t1.start();
        t3.start();
    }


}
