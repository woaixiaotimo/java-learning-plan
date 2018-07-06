package com.materWork;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Master {

    ReentrantLock reentrantLock = new ReentrantLock();

    //1承装任务的集合
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();

    //2使用HashMap去承载Work对象
    private Map<String, Thread> works = new HashMap<String, Thread>();

    //3使用一个容器承载每一个worker，并非执行任务的结果集
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();


    //4构造方法
    public Master(Worker worker, int workerCount) {

        worker.setWorkerQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for (int i = 0; i < workerCount; i++) {
            //
            works.put("子节点" + Integer.toString(i), new Thread(worker));
        }
    }

    //5提交方法
    public void submit(Task task) {
        this.workQueue.add(task);
    }

    //6执行方法使所有的worker工作
    public void execute() {
        for (Map.Entry<String, Thread> me : works.entrySet()) {
            me.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> me : works.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public long getResult() {
        long count = 0L;
//        reentrantLock.lock();
//        reentrantLock.wait();
        for (Map.Entry<String, Object> me : resultMap.entrySet()) {
            count += Long.parseLong(me.getValue().toString());
        }
//        reentrantLock.unlock();
        return count;
    }
}
