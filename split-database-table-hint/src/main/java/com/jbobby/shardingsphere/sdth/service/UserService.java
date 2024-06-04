package com.jbobby.shardingsphere.sdth.service;

import com.jbobby.shardingsphere.sdth.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户信息
     */
    List<User>  list();

    /**
     *   批量 保存用户信息
     * @param userVOList
     */
    String  insertForeach(List<User> userVOList);

    boolean insertSingle(int dbValue, int tableValue, User user);

}