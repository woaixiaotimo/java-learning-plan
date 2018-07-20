package com.base;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongEventMain {

    public static Long beforeTime= System.currentTimeMillis();

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //创建工厂
        LongEventFactory factory = new LongEventFactory();
        //创建bufferSize ，也就是RingBuffer大小必须是2的n次方
        int ringBufferSize = 1024 * 1024;

//        //BlockingWaitStrategy 是最低效的策略，但其对cpu的消耗最小并且在各种不同的部署环境中能提供更加一致的性能表现
//        WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
//        //SleepingWaitStrategy 的性能表现和BlockingWaitStrategy差不多，对cpu的消耗也类似，但其对生产者小城的影响最小，适合异步日志类似的场景
//        WaitStrategy SLEEP_WAIT = new SleepingWaitStrategy();
//        //YieldingWaitStrategy 的性能是最好的，适合用于低时延的系统，在要求极高性能且事件处理线程数小于cpu逻辑核心数的场景中，推荐使用此策略：例如cpu开启超线程
//        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
        //创建disruptor
        //factory  用于创建工厂对象用于创建LongEvent对象，LongEvent是实际的消费数据
        //ringBufferSize 缓冲区大小
        //executor 线程池进行Disruptor内部的数据的接收处理调度
        //ProducerType.SINGLE/MULTI,表示指定生产者和消费者的是一个还是多个
        //new YieldingWaitStrategy() 是一种策略
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        //连接消费事件的方法
        disruptor.handleEventsWith(new LongEventHandler());
        //启动
        disruptor.start();

        //Disruptor的事件发布是一个两阶段提交的过程
        //发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        LongEventProducer producer = new LongEventProducer(ringBuffer);
        LongEventProducerWithTranslator producer =new LongEventProducerWithTranslator(ringBuffer);


        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 1; i <= 1048576L; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }
        disruptor.shutdown();
        executor.shutdown();

    }
}
