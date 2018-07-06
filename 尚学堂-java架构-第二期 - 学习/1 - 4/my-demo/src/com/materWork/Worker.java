package com.materWork;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
    private ConcurrentLinkedQueue<Task> workerQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    @Override
    public void run() {
        while (true) {
            Task input = this.workerQueue.poll();
            if (input == null)
                break;
            Object output = handle(input);

            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    private Object handle(Task input) {
        Object output = null;
        try {
            //表示处理耗时
            Thread.sleep(500);
            output = input.getPrice();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }


    public void setWorkerQueue(ConcurrentLinkedQueue<Task> workerQueue) {
        this.workerQueue = workerQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
