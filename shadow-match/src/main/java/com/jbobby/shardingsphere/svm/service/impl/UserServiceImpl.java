package com.jbobby.shardingsphere.svm.service.impl;

import com.jbobby.shardingsphere.svm.entity.User;
import com.jbobby.shardingsphere.svm.mapper.UserMapper;
import com.jbobby.shardingsphere.svm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private AtomicInteger counter = new AtomicInteger(0);
    @Override
    public List<User> list() {
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
            user.setUserName("test" + counter.get());
            user.setUserType(counter.getAndIncrement() % 2);
        }
        //批量插入数据
        userMapper.insertForeach(userList);
        return "保存成功";
    }

    @Override
    public String insert(User user) {
        user.setUserName("test" + counter.get());
        user.setUserType(counter.getAndIncrement() % 2);
        userMapper.insert(user);
        return "insert user success";
    }

    @Override
    public boolean delete(String type, int value) {
        if (type.equalsIgnoreCase("byid")) {
            userMapper.deleteById(value);
        } else if (type.equalsIgnoreCase("byusertype")) {
            userMapper.deleteByUserType(value);
        }
        return true;
    }

    @Override
    public String truncateTableShadow() {
        userMapper.truncateTableShadow();
        return "truncate table shadow success";
    }
}
