package com.jbobby.shardingsphere.sta.service.impl;

import com.jbobby.shardingsphere.sta.entity.User;
import com.jbobby.shardingsphere.sta.mapper.UserMapper;
import com.jbobby.shardingsphere.sta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void initEnvironment() {
        userMapper.createTableIfNotExists();
    }

    @Override
    public  List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public String insertForeach(List<User> userList) {
        for (User user : userList) {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(0);
        }
        //批量插入数据
        userMapper.insertForeach(userList);
        return "保存成功";
    }
}
