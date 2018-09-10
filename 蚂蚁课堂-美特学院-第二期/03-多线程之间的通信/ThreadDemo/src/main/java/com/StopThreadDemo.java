package com;

import java.util.Vector;

public class StopThreadDemo {

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("线程空转" );
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("收到信号线程停止" );
                    return;
                }
            }
        });

        t1.start();
        Thread.sleep(2000);

        t1.interrupt();

    }
}
