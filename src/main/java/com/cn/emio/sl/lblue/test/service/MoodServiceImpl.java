package com.cn.emio.sl.lblue.test.service;

import javax.annotation.Resource;
import javax.jms.Destination;

import com.cn.emio.sl.lblue.test.entity.Mood;
import com.cn.emio.sl.lblue.test.producer.MoodProducer;
import com.cn.emio.sl.lblue.test.repository.MoodRepository;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

@Service
/**
 * @author Silence_Lurker
 */
public class MoodServiceImpl implements MoodService {

    @Resource
    private MoodRepository moodRepository;

    @Override
    public Mood save(Mood mood) {
        return moodRepository.save(mood);
    }

    // 队列
    private static Destination destination = new ActiveMQQueue("sl.queue.asyn.save");

    @Resource
    private MoodProducer moodProducer;

    @Override
    public String asynMood(Mood mood) {
        // 向目标队列推送消息，消息内容为说说实体。

        moodProducer.sendMessage(destination, mood);
        return "success";
    }

}
