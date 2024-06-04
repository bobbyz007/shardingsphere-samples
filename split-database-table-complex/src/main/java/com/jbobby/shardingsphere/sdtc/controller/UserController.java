package com.jbobby.shardingsphere.sdtc.controller;


import com.google.common.collect.Lists;
import com.jbobby.shardingsphere.sdtc.entity.User;
import com.jbobby.shardingsphere.sdtc.service.UserService;
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
        // ds_0 分库
        userList.add(new User(2L, 0, "小小", "女", 1));
        userList.add(new User(5L, 1, "爸爸", "男", 2));
        userList.add(new User(8L, 2, "妈妈", "女", 3));
        userList.add(new User(11L, 3, "爷爷", "男", 4));
        userList.add(new User(14L, 4, "爷爷", "男", 5));

        // ds_1
        userList.add(new User(4L, 5, "奶奶", "女", 6));
        userList.add(new User(7L, 6, "奶奶", "女", 7));
        userList.add(new User(10L, 7, "奶奶", "女", 8));
        userList.add(new User(13L, 8, "奶奶", "女", 9));
        userList.add(new User(16L, 9, "奶奶", "女", 10));

    }
    /**
     * @Description: 批量保存用户
     */
    @PostMapping("save-user")
    public Object saveUser() {
        return userService.insertForeach(userList);
    }
    /**
     * @Description: 获取用户列表
     */
    @GetMapping("list-user")
    public Object listUser() {
        return userService.list();
    }


}
