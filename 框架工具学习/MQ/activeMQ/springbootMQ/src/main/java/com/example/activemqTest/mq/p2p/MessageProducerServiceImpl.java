package com.example.activemqTest.mq.p2p;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;


@Service
public class MessageProducerServiceImpl implements IMessageProducerService {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource
    private Queue queue;

    @Override
    public void sendMessage(String msg) {
        System.err.println("【*** 发送消息 ***】" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

}
