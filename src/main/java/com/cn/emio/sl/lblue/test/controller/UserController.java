package com.cn.emio.sl.lblue.test.controller;

import java.util.List;

import javax.annotation.Resource;

import com.cn.emio.sl.lblue.test.entity.User;
import com.cn.emio.sl.lblue.test.error.BusinessException;
import com.cn.emio.sl.lblue.test.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
/**
 * @author Silence_Lurker
 */
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<User> user = userService.findAll();
        model.addAttribute("users", user);
        throw new BusinessException("业务异常");
    }

    @RequestMapping("/test")
    public String test(Model model) {
        // 查询全部用户
        List<User> user = userService.findAll();
        model.addAttribute("users", user);

        return "user";
    }

    @RequestMapping("/findByNameAndPasswordRetry")
    public String findByNameAndPasswordRetry(Model model) {
        User user = userService.findByNameAndPasswordRetry("Name", "Pass");
        model.addAttribute("users", user);
        return "success";
    }

}