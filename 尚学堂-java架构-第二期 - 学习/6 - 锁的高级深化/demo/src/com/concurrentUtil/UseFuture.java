package com.concurrentUtil;

import java.util.concurrent.*;

public class UseFuture implements Callable<String> {

    String str;

    public UseFuture(String str) {
        this.str = str;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String queryStr = "query";
        FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //submit和execute的区别
        //1、submit可以传入实现callable接口的实现对象
        //2、submit方法有返回值
        Future f = executor.submit(future);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //get方法内会异步调用内部返回结果，但会阻塞主线程
        System.out.println("数据：" + future.get());
        executor.shutdown();
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        String result = this.str + " 处理完成";
        return result;
    }
}
