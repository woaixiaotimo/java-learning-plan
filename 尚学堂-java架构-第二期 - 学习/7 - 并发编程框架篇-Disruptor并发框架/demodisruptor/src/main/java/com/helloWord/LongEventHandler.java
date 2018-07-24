package com.helloWord;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    public Long afterTime;

    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
//        if (longEvent.getValue() == 1048576L) {
//            afterTime = System.currentTimeMillis();
//            System.out.println("thisTime = " +(afterTime - LongEventMain.beforeTime));
//        }

        System.out.println(longEvent.getValue());
    }
}
