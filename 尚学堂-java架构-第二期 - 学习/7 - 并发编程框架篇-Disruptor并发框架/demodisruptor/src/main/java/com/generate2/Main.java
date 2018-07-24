package com.generate2;

import com.generate1.Trade;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.oracle.jrockit.jfr.Producer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        int bufferSize = 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, bufferSize, executorService, ProducerType.SINGLE.SINGLE, new BusySpinWaitStrategy());


//        //菱形操作
//        //使用disruptor创建消费者c1，c2
//        EventHandlerGroup<Trade> handlerGroup = disruptor.handleEventsWith(new Handler1(), new Handler2());
//        //声明C1，C2之后执行jms消息发送操作，也就是走到流程c3
//        handlerGroup.handleEventsWith(new Handler3());

//        //六边形操作
//        Handler1 handler1 = new Handler1();
//        Handler2 handler2 = new Handler2();
//        Handler3 handler3 = new Handler3();
//        Handler4 handler4 = new Handler4();
//        Handler5 handler5 = new Handler5();
//        disruptor.handleEventsWith(handler1, handler2);
//        disruptor.after(handler1).handleEventsWith(handler4);
//        disruptor.after(handler2).handleEventsWith(handler5);
//        disruptor.after(handler4, handler5).handleEventsWith(handler3);

        //顺序操作
        disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler2()).handleEventsWith(new Handler3());


        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);
        executorService.submit(new TradePublisher(latch, disruptor));
        latch.await();
        Thread.sleep(1000);
        disruptor.shutdown();
        executorService.shutdown();
        System.out.println("总耗时：" + (System.currentTimeMillis() - beginTime));

    }
}
