package ConcurrentUtilPackageDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphore_Demo {
    final static int COUNT = 20;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //只能五个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < COUNT; i++) {
            final int NO = i;
            Runnable run = () -> {
                try {
                    //获取许可
                    semaphore.acquire();
                    System.out.println("线程:" + NO + " 获得许可");
                    //模拟实际业务
                    Thread.sleep(1000 * (new Random().nextInt(5)));
                    //访问完成后释放
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            };
            executorService.execute(run);
        }

        //等待任务线程运行结束
        countDownLatch.await();
        System.out.println("所有线程运行结束");
        executorService.shutdown();
    }
}
