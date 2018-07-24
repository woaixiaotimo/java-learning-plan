package com.helloWord;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件，每调用一次就发布一次事件
     * 它的参数会通过事件来传递给消费者
     */
    public void onData(ByteBuffer byteBuffer) {
        //可以把RingBuffer看做一个事件队列，next就是得到下一个事件槽索引
        long sequence = ringBuffer.next();

        try {
            //用上面的索引取出一个空的事件用于填充(获取该序号对应的)事件对象
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            //发布事件s
            //注意，最后的ringBuffer.publish方法必须包含在finally中以确保得到调用，如果请求的sequence未被提交
            ringBuffer.publish(sequence);
        }

    }
}
