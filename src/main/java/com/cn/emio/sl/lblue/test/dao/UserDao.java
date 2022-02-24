package com.cn.emio.sl.lblue.test.dao;

import com.cn.emio.sl.lblue.test.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
/**
 * User类Dao
 * 
 * @author Silence_Lurker
 */
public interface UserDao {
    /**
     * 通过用户名和密码查询用户
     * 
     * @param name
     * @param password
     * @return
     */
    User findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
