package com.threadCreateDemo;

/**
 * Created by 啊Q on 2018-09-02.
 */
public class ThreadCreate01 extends  Thread {
    public void run() {
        for (int i = 0; i< 10; i++) {
            System.out.println("i:" + i);
        }
    }
}
