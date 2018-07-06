package com;


import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Collections;
import java.util.concurrent.*;

public class Test1 {

    static volatile int count = 100;
    final static SynchronousQueue queue = new SynchronousQueue();

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread() {

            @Override
            public void run() {
                try {
                    while (Test1.count > 0) {
//                        queue.take();
                        System.out.println("get = " + queue.take().toString());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get end" );
            }

        };

        Thread t2 = new Thread() {

            @Override
            public void run() {
                while (Test1.count > 0) {
                    if (queue.offer(Test1.count)) {
                        System.out.println("set = " + Test1.count);
                        Test1.count--;
                    }

                }
                System.out.println("set end");
            }
        };
        t1.start();
        Thread.sleep(20);
        t2.start();

    }
}
