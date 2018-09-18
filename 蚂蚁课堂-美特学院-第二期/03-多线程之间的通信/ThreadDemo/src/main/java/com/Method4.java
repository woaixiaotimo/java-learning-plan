package com;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger+线程空转，完成生产者消费者
 */
public class Method4 {
    public static void main(String args[]) throws InterruptedException {
        MethodFive five = new MethodFive();
        Helper.instance.run(five.newThreadOne());
        Helper.instance.run(five.newThreadTwo());
        Helper.instance.shutdown();
    }
}

class MethodFive {
    private AtomicInteger threadToGo = new AtomicInteger(1);

    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i = i + 2) {
                    //线程空转
                    while (threadToGo.get() == 2) {
                    }
                    Helper.print(arr[i], arr[i + 1]);
                    threadToGo.set(2);
                }
            }
        };
    }

    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    //线程空转
                    while (threadToGo.get() == 1) {
                    }
                    Helper.print(arr[i]);
                    threadToGo.set(1);
                }
            }
        };
    }

}

