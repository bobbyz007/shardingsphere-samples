package com.jbobby.shardingsphere.sdtca.service.impl;

import com.jbobby.shardingsphere.sdtca.entity.User;
import com.jbobby.shardingsphere.sdtca.mapper.UserMapper;
import com.jbobby.shardingsphere.sdtca.service.UserService;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public List<User> listByKey(Map<String, Long> params) {
        return userMapper.selectByKey(params);
    }

    @Override
    public List<User> listByRange(Map<String, Map<String, Long>> params) {
        return userMapper.selectByRange(params);
    }

    @Override
    public List<User> listByKeyRange(Long userId, Map<String, Long> params) {
        return userMapper.selectByKeyRange(userId, params);
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

    @Override
    public boolean insertSingle(User user) {
        userMapper.insertSingle(user);
        return true;
    }

    @Override
    public boolean insertSingleWithHint(long dbValue, long tableValue, User user) {
        // ThreadLocal变量
        HintManager hintManager = HintManager.getInstance();
        hintManager.clearShardingValues();
        // 设置逻辑表 t_user 的分库值
        hintManager.addDatabaseShardingValue("t_user", dbValue);
        // 设置逻辑表 t_user 的分表值
        hintManager.addTableShardingValue("t_user", tableValue);

        userMapper.insertSingle(user);

        hintManager.close();
        return true;
    }
}
