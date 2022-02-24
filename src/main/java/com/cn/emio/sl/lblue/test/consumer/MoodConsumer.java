package com.cn.emio.sl.lblue.test.consumer;

import javax.annotation.Resource;

import com.cn.emio.sl.lblue.test.entity.Mood;
import com.cn.emio.sl.lblue.test.service.MoodService;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
/**
 * @author Silence_Lurker
 */
public class MoodConsumer {

    @JmsListener(destination = "sl.queue")
    public void receiveQueue(String text) {
        System.out.println("用户发表说说" + text + "成功");
    }

    @Resource
    private MoodService moodService;

    @JmsListener(destination = "sl.queue.asyn.save")
    public void receiveQueue(Mood mood) {
        moodService.save(mood);
    }
}
