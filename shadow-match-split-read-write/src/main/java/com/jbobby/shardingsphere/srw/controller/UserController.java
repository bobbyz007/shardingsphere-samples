package com.jbobby.shardingsphere.srw.controller;


import com.jbobby.shardingsphere.srw.entity.User;
import com.jbobby.shardingsphere.srw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 保存用户
     */
    @PostMapping("save-user/{status}")
    public Object saveUser(@PathVariable("status") int status) {
        return userService.saveOne(new User("小小", "女", status));
    }

    /**
     * @Description: 获取用户列表
     */
    @GetMapping("list-user/{status}")
    public Object listUser(@PathVariable("status") int status) {
        return userService.list(status);
    }
}
