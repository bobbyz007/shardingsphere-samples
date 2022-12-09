package com.oujiong.service.impl;

import com.oujiong.entity.User;
import com.oujiong.mapper.UserMapper;
import com.oujiong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author xub
 * @Description: 用户实现类
 * @date 2019/8/8 上午9:13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private AtomicInteger counter = new AtomicInteger(0);
    @Override
    public  List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public List<User> listByShadow(int userType) {
        return userMapper.selectAllByShadow(userType);
    }

    @Override
    public String insertForeach(List<User> userList) {
        for (User user : userList) {
            // id分表
            user.setUserId(counter.incrementAndGet());
        }
        //批量插入数据
        userMapper.insertForeach(userList);
        return "保存成功";
    }

    @Override
    public String insert(User user) {
        user.setUserId(counter.incrementAndGet());
        user.setUserType(counter.intValue() % 2);
        userMapper.insert(user);
        return "insert user success";
    }

    @Override
    public boolean delete(int type, int value) {
        if (type == 0) {
            userMapper.deleteById(value);
        } else if (type == 1) {
            userMapper.deleteByUserType(value);
        }
        return true;
    }
}
