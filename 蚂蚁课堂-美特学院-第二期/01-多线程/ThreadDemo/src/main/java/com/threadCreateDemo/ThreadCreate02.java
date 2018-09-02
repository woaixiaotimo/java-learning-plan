package com.threadCreateDemo;

/**
 * Created by 啊Q on 2018-09-02.
 */
public class ThreadCreate02 implements  Runnable {
    public void run() {
        for (int i = 0; i< 10; i++) {
            System.out.println("i:" + i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i< 10; i++) {
                    System.out.println("i:" + i);
                }
            }
        });

    }
}
