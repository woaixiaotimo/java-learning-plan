package com.executor;


import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class FixedThreadPoolTest {

    static class Temp extends Thread {

        public void run() {
            System.out.println("run");
        }
    }

    static class a implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }

    public static void main(String[] args) {
//        Temp command = new Temp();
//        ScheduledExecutorService pool = Executors.newFixedThreadPool(1);
//        ScheduledFuture<?> scheduledFuture = pool.schedule(command,5,TimeUnit.SECONDS);


    }
}
