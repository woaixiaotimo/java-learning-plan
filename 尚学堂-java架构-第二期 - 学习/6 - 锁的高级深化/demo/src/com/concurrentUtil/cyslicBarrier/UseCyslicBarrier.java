package com.concurrentUtil.cyslicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCyslicBarrier {

    static class Runner implements Runnable {
        CyclicBarrier barrier;
        String name;
        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random().nextInt(5)));
                System.out.println(name + " 准备OK");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " go");

        }
    }
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Thread(new Runner(barrier, "1")));
        executorService.submit(new Thread(new Runner(barrier, "2")));
        executorService.submit(new Thread(new Runner(barrier, "3")));

        executorService.shutdown();
    }

}
