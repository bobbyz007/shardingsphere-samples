package com.jbobby.shardingsphere.sdtrw.controller;


import com.google.common.collect.Lists;
import com.jbobby.shardingsphere.sdtrw.entity.User;
import com.jbobby.shardingsphere.sdtrw.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 模拟插入数据
     */
    List<User> userList = Lists.newArrayList();
    /**
     * 初始化插入数据
     */
    @PostConstruct
    private void getData() {
        userList.add(new User(1L,"小小", "女", 3));
        userList.add(new User(2L,"爸爸", "男", 30));
        userList.add(new User(3L,"妈妈", "女", 28));
        userList.add(new User(4L,"爷爷", "男", 64));
        userList.add(new User(5L,"奶奶", "女", 62));
    }

    @PostMapping("save-user")
    public Object saveUser() {
        return userService.insertForeach(userList);
    }

    @GetMapping("list-user")
    public Object listUser() {
        return userService.list();
    }

    /**
     * 通过SQL HINT使得读强制路由到 主库
     */
    @GetMapping("list-user-from-master-forcefully")
    public Object listUserFromMasterForcefully() {
        return userService.listFromMasterForcefully();
    }
}
