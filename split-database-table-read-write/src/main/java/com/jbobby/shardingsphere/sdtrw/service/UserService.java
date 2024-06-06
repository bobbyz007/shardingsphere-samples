package com.jbobby.shardingsphere.sdtrw.service;

import com.jbobby.shardingsphere.sdtrw.entity.User;

import java.util.List;

public interface UserService {
    List<User> list();
    List<User> listFromMasterForcefully();

    String  insertForeach(List<User> userVOList);
}