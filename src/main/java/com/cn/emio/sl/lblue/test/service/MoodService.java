package com.cn.emio.sl.lblue.test.service;

import com.cn.emio.sl.lblue.test.entity.Mood;

/**
 * @author Silence_Lurker
 */
public interface MoodService {
    /**
     * 用于保存说说信息
     * 
     * @param mood 待保存说说信息
     * @return 保存的信息
     */
    Mood save(Mood mood);

    /**
     * 异步保存方法
     * 
     * @param mood 带保存信息
     * @return 标志信息
     */
    String asynMood(Mood mood);
}
