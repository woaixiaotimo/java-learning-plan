package com.generate1;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;
/**
 * 事件处理器
 * */
public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {


    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    public void onEvent(Trade event) throws Exception {
        event.setId(UUID.randomUUID().toString());
        System.out.println("event = " + event.getId());
    }

}
