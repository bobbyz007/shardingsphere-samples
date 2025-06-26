package com.jbobby.shardingsphere.svm.service;

import com.jbobby.shardingsphere.svm.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户信息
     */
    List<User>  list();

    List<User> listByShadow(int userType);

    /**
     *   批量 保存用户信息
     * @param userVOList
     */
    String  insertForeach(List<User> userVOList);

    String  insert(User user);

    boolean delete(String type, int value);

    String truncateTableShadow();
}