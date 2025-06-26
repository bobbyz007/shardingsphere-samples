package com.jbobby.shardingsphere.sdt.service;

import com.jbobby.shardingsphere.sdt.entity.User;

import java.util.List;

public interface UserService {
    void initEnvironment();

    List<User>  list();

    String  insertForeach(List<User> userVOList);
}