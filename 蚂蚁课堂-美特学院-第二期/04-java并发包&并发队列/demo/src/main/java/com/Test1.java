package com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Test1 {
    static volatile int[] a = new int[10];

}

class ThreadLocalTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            return "ads";
        });
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(futureTask);
        System.out.println("executor = " + futureTask.get());
    }
}
