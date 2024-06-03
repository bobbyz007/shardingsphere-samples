package com.jbobby.shardingsphere.sta.controller;


import com.jbobby.shardingsphere.sta.entity.User;
import com.jbobby.shardingsphere.sta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autotable")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("init-env")
    public boolean cleanEnvironment() {
        userService.initEnvironment();
        return true;
    }

    /**
     * @Description: 批量保存用户
     */
    @PostMapping("save-user")
    public Object saveUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(0L,"小小", "女", 1));
        userList.add(new User(0L,"爸爸", "男", 2));
        userList.add(new User(0L,"妈妈", "女", 3));
        userList.add(new User(0L,"爷爷", "男", 4));
        userList.add(new User(0L,"奶奶", "女", 5));

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
