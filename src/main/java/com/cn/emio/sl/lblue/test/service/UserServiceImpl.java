package com.cn.emio.sl.lblue.test.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import com.cn.emio.sl.lblue.test.dao.UserDao;
import com.cn.emio.sl.lblue.test.entity.User;
import com.cn.emio.sl.lblue.test.error.BusinessException;
import com.cn.emio.sl.lblue.test.repository.UserRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional()
@Service
/**
 * @author Silence_Lurker
 */
public class UserServiceImpl implements UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        try {
            System.out.println("开始执行任务");
            long start = System.currentTimeMillis();
            List<User> userList = userRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("任务完成，耗时：" + (end - start) + "ms");
            return userList;
        } catch (Exception e) {
            logger.error("method [findAll] error!", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public User save(User user) {
        // return userRepository.save(user);

        User saveUser = userRepository.save(user);
        // 下面这段代码必定出错，error恒为空
        // String error = null;
        // error.split("/");
        return saveUser;
    }

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);

        logger.info("userId:" + id + "用户已被删除");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    @Override
    public List<User> findByIdIn(Collection<String> ids) {
        return userRepository.findByIdIn(ids);
    }

    @Resource
    private UserDao userDao;

    @Override
    public User findByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name, password);
    }

    @Override
    @Async
    public Future<List<User>> findAsynAll() {
        try {
            System.out.println("开始任务");
            long start = System.currentTimeMillis();
            List<User> userList = userRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("任务结束，耗时：" + (end - start) + "ms");
            return new AsyncResult<List<User>>(userList);
        } catch (Exception e) {
            logger.error("method [findAll] error", e);
            return new AsyncResult<List<User>>(null);
        }
    }

    @Override
    @Retryable(value = { BusinessException.class }, maxAttempts = 5, backoff = @Backoff(delay = 5000, multiplier = 2))
    public User findByNameAndPasswordRetry(String name, String password) {
        System.out.println("[findByNameAndPasswordRetry] 方法失败了");
        throw new BusinessException();
    }

}