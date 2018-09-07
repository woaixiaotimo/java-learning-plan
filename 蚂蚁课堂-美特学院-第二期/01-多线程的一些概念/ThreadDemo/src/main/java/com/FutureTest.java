package com;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
        Future<String> future = executor.submit(() -> {
            System.out.println("running task");
            Thread.sleep(10000);
            return "return task";
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情
        System.out.println("do something else");
        try {
            //等待 future 的执行结果，执行完毕之后打印出来
            System.out.println(future.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } finally {
            executor.shutdown();
        }

    }
}
