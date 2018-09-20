package com;


import java.util.concurrent.*;

public class Test1 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(500);
            return "ads";
        });
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(futureTask);
        //主线程继续啊
        System.out.println("马上进入等待获取结果" );
        System.out.println("executor = " + futureTask.get());
        executor.shutdown();
    }
}

