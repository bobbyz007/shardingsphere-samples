package com.jbobby.shardingsphere.sta.controller;


import com.jbobby.shardingsphere.sta.entity.User;
import com.jbobby.shardingsphere.sta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("save-user/{numOfUsers}")
    public Object saveUser(@PathVariable("numOfUsers") int numOfUsers) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < numOfUsers; i++) {
            userList.add(new User(0L, i + 1, "Justin", "male", 100));
        }

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
