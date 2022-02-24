package com.cn.emio.sl.lblue.test.repository;

import java.util.Collection;
import java.util.List;

import com.cn.emio.sl.lblue.test.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 对接User数据池的接口
 * 
 * @author Silence_Lurker
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 通过名字查询，参数为name
     * <p>
     * 等价于：select u from user u where u.name = ?1
     * </p>
     * 
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 通过名字like查询，参数为Name
     * <p>
     * 等价于：select u from user u where u.name = >1
     * </p>
     * 
     * @param name
     * @return
     */
    List<User> findByNameLike(String name);

    /**
     * 通过主键ID集合查询
     * <p>
     * 等价于：select u from user u where id in (?,?,?)
     * </p>
     * 
     * @param ids
     * @return
     */
    List<User> findByIdIn(Collection<String> ids);
}
