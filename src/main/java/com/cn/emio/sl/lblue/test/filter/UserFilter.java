package com.cn.emio.sl.lblue.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 过滤器类
 * 
 * @author Silence_Lurker
 * @date 2022/1/11
 */
@WebFilter(filterName = "userFilter", urlPatterns = { "/*" })
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        System.out.println("------------------>>> doFilter");
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
