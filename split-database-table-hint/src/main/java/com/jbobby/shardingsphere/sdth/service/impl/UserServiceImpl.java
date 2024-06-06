package com.jbobby.shardingsphere.sdth.service.impl;

import com.jbobby.shardingsphere.sdth.entity.User;
import com.jbobby.shardingsphere.sdth.mapper.UserMapper;
import com.jbobby.shardingsphere.sdth.service.UserService;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public List<User> listWithSQLHint() {
        return userMapper.selectAllWithSQLHint();
    }

    @Override
    public List<User> listWithSQLHintDatasource() {
        return userMapper.selectAllWithSQLHintDatasource();
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
    public boolean insertSingle(int dbValue, int tableValue, User user) {
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
