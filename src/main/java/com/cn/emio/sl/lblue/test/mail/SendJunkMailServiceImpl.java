package com.cn.emio.sl.lblue.test.mail;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import com.cn.emio.sl.lblue.test.entity.User;
import com.cn.emio.sl.lblue.test.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Silence_Lurker
 */
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    @Autowired
    JavaMailSender mailSender;

    @Resource
    private UserService userService;

    @Value("${spring.mail.username}")
    private String from;

    public static final Logger logger = LogManager.getLogger(SendJunkMailServiceImpl.class);

    @Override
    public boolean sendJunkMailService(List<User> user) {
        try {
            if (user == null || user.size() <= 0) {
                return false;
            }
            for (User u : user) {
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                // 邮件发送方：
                message.setFrom(from);
                // 邮件主题：
                message.setSubject("主题_测试");
                // 邮件接收方
                message.setTo("");
                // 邮件内容
                message.setText(u.getName() + ",进行测试");
                // 发送邮件
                this.mailSender.send(mimeMessage);
            }
        } catch (Exception e) {
            logger.error("sendJunkMail error and user=%s", user, e);
            return false;
        }
        return true;
    }

}
