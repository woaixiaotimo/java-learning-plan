package com.generate1;

import com.lmax.disruptor.*;

import java.util.concurrent.*;

public class EventHandlerMainDemo {
    public static void main(String[] args) {

        int BUFF_SIZE = 1024;
        int THREAD_NUMBERS = 4;

        //createSingleProducer创建一个生产者的RingBuffer
        //EventFactory:负责产生数据填充RingBuffer的对象
        //RingBufferSize:指示RingBuffer的大小，他必须是2的指数倍，目的是为了转为&运算提高效率
        //WaitStrategy:RingBuffer的生产在没有可用区块(或者是 消费者/事件处理器 太慢了))的等待策略
        final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFF_SIZE, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        //创建消息处理器
        BatchEventProcessor<Trade> tradeBatchEventProcessor = new BatchEventProcessor<Trade>(ringBuffer, sequenceBarrier, new TradeHandler());

        //这一步的目的是吧消费者的位置信息引入到生产者，如果只有一个消费者的情况下可以忽略
        ringBuffer.addGatingSequences(tradeBatchEventProcessor.getSequence());

        //把消息处理器提交到线程池
        executorService.submit(tradeBatchEventProcessor);

        //如果存在多个消费者那重复执行上面三行代码,把TraheHandler换成其他消费类

        Future<?> future = executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {

                long seq;
                for (int i = 0; i < 10; i++) {
                    //占用一个ringBuffer的可用区块
                    seq = ringBuffer.next();
                    //为区块置入数据
                    ringBuffer.get(seq).setPrice(Math.random() * 9999);
                    //发布这个区块是handler可见
                    ringBuffer.publish(seq);
                }
                return null;
            }
        });


        try {
            future.get();//等待生产结束
            Thread.sleep(1000);//等上一秒，等消息处理完成
            //通知时间
            tradeBatchEventProcessor.halt();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
