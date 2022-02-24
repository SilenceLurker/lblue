package com.cn.emio.sl.lblue.test.mail;

import java.util.List;

import com.cn.emio.sl.lblue.test.entity.User;

/**
 * @author Silence_Lurker
 */
public interface SendJunkMailService {
    /**
     * 发送垃圾邮件
     * 
     * @param user 收件人列表
     * @return
     */
    boolean sendJunkMailService(List<User> user);
}
