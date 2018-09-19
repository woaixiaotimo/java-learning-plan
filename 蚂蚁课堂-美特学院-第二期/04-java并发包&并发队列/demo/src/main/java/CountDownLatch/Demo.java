package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Demo {

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
