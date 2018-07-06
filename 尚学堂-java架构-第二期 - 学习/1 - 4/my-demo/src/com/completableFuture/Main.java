package com.completableFuture;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建内部有5个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        long start = System.currentTimeMillis();
        //将任务放入CompletableFuture中并使用executor线程池
        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "hello";
            }
        }, executor);


        //将前面的任务异步处理的结果交给另外一个异步事件处理线程来处理。
        //有多个方法并且有的方法可以指定线程池
        System.out.println(resultCompletableFuture.thenAccept(new Consumer<String>(){
            @Override
            public void accept(String t) {
                System.out.println(t);
                System.out.println(Thread.currentThread().getName());
            }
        }));



//        System.out.println(resultCompletableFuture.get());
//
//        boolean result = resultCompletableFuture.complete("23");
//        System.out.println("result = " + result);
//
//        System.out.println("executor = " + (System.currentTimeMillis() - start));
//        executor.shutdown();
    }

}
