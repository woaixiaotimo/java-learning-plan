package com.helloWord;

import com.lmax.disruptor.EventFactory;

//需要让dissruptor为我们创建事件，我们同时还生命了一个LongEventFactory来实例化Event对象
public class LongEventFactory implements EventFactory {
    public Object newInstance() {
        return new LongEvent();
    }
}
