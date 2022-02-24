package com.cn.emio.sl.lblue.test.quartz;

import java.util.List;

import javax.annotation.Resource;

import com.cn.emio.sl.lblue.test.entity.User;
import com.cn.emio.sl.lblue.test.mail.SendJunkMailService;
import com.cn.emio.sl.lblue.test.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
/**
 * @author SIlence_Lurker
 */
public class SendMailQuartz {
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);

    @Resource
    private SendJunkMailService sendJunkMailService;

    @Resource
    private UserService userService;

    // @Scheduled(cron = "*/5 * * * * *")
    // public void reportCurrentByCron() {
    // List<User> userList = userService.findAll();
    // if (userList == null || userList.size() <= 0) {
    // return;
    // }
    // sendJunkMailService.sendJunkMailService(userList);
    // logger.info("定时器运行了！！！");
    // }
}
