package com.jbobby.shardingsphere.srw.service.impl;

import com.jbobby.shardingsphere.srw.entity.User;
import com.jbobby.shardingsphere.srw.mapper.UserMapper;
import com.jbobby.shardingsphere.srw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public String saveOne(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(1);
        userMapper.insert(user);
        return "保存成功";
    }
}
