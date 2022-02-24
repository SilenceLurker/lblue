package com.cn.emio.sl.lblue.test.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import com.cn.emio.sl.lblue.test.entity.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 * @author Silence_Lurker
 */
public interface UserService {
    /**
     * 根据ID查找数据
     * 
     * @param id 目标ID
     * @return 对应ID数据对象
     */
    User findById(String id);

    /**
     * 获取全部对象
     * 
     * @return 全部数据对象集合(List类)
     */
    List<User> findAll();

    /**
     * 保存数据对象进入数据库
     * 
     * @param user 待添加数据
     * @return 保存进入的对象（所以说为什么要返回？）
     */
    User save(User user);

    /**
     * 删除指定Id对象
     * 
     * @param id 目标ID
     */
    void delete(String id);

    /**
     * <p>
     * 分页操作
     * </p>
     * <p>
     * Page:分页查询的结果会封装在该类中，Page接口实现Slice接口，可通过调用getTotalPages和getContent等方法，可以方便获得总页数和查询的记录。
     * </p>
     * 
     * @param pageable 传入一个pageable接口的实现类指定pageNum和PageSize即可。
     * @return
     */
    Page<User> findAll(Pageable pageable);

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

    /**
     * 根据用户名和密码查询
     * 
     * @param name
     * @param password
     * @return
     */
    User findByNameAndPassword(String name, String password);

    /**
     * 异步查询方法
     * 
     * @return
     */
    Future<List<User>> findAsynAll();

    /**
     * 可Retry的按名称和密码查询
     * 
     * @param name     用户名
     * @param password 密码
     * @return 查询到的对象
     */
    User findByNameAndPasswordRetry(String name, String password);
}
