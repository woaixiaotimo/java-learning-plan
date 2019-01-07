package test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @Auther: what
 * @Date: 2019/1/3 11:01
 * @Description:
 */
public class ExecutorsTest {

}
class UseReentrantReadWriteLock {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入read。。。");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出read。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入write。。。");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出write。。。");
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
        t1.start();
        t2.start();
//        t1.start();
//        t3.start();
    }


}

