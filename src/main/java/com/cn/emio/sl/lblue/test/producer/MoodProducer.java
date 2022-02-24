package com.cn.emio.sl.lblue.test.producer;

import javax.annotation.Resource;
import javax.jms.Destination;

import com.cn.emio.sl.lblue.test.entity.Mood;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
/**
 * @author Silence_Lurker
 */
public class MoodProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(Destination destination, final Mood mood) {
        jmsMessagingTemplate.convertAndSend(destination, mood);
    }
}
