package com.cn.emio.sl.lblue.test.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * @author Silence_Lurker
 */
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidStatViewServle() {
        // ServletRegistrationBean提供类的进行注册
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        // 添加初始化参数：initParams
        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");

        // IP黑名单（存在共同使，deny优先于allow）
        // servletRegistrationBean.addInitParameter("deny", "192.168.*.*");
        // 设置登录的账号和密码信息
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
