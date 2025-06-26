package com.jbobby.shardingsphere.srw.service;

import com.jbobby.shardingsphere.srw.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户信息
     */
    List<User> list(int status);

    /**
     * 单个 保存用户信息
     *
     * @param user
     */
    String saveOne(User user);

}