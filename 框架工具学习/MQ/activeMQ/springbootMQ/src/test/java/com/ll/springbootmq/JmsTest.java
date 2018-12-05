package com.ll.springbootmq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Queue;
import javax.jms.Topic;

public class JmsTest extends BaseTest{
    @Autowired
    private JMSProducer jmsProducer;
    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;

    @Test
    public void testJms() {
        for (int i=0;i<10;i++) {
            jmsProducer.sendMessage(queue,"queue,world!" + i);
            jmsProducer.sendMessage(topic, "topic,world!" + i);
        }
    }
}
