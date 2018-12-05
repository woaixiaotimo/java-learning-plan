package com.ll.springbootmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
@EnableJms
public class JMSProducer {

    private final static Logger logger = LoggerFactory.getLogger(JMSProducer.class);


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    public void sendMessage(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);

        logger.info("发送消息：{}",msg);

    }
}

