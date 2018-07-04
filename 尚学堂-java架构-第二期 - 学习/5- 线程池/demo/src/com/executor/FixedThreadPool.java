package com.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

    }
}
