package com.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消费者
 */
public class Consumer implements WorkHandler<Order> {
    private String consumerId;

    private final static AtomicInteger count = new AtomicInteger(0);

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    public void onEvent(Order order) throws Exception {
        System.out.println("当前消费者: " + this.consumerId + "，消费信息：" + order.getId() + " 总数：" + this.getCount());
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
