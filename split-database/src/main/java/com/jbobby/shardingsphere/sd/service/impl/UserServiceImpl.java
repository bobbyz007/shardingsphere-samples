package com.jbobby.shardingsphere.sd.service.impl;

import com.jbobby.shardingsphere.sd.entity.User;
import com.jbobby.shardingsphere.sd.mapper.UserMapper;
import com.jbobby.shardingsphere.sd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private AtomicLong counter = new AtomicLong(0);
    @Override
    public  List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public String insertForeach(List<User> userList) {
        for (User user : userList) {
            // id分表
            user.setId(counter.incrementAndGet());

            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(0);
        }
        //批量插入数据
        userMapper.insertForeach(userList);
        return "保存成功";
    }
}
