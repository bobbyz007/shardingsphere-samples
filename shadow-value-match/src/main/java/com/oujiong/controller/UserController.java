package com.oujiong.controller;


import com.google.common.collect.Lists;
import com.oujiong.entity.User;
import com.oujiong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @Description: 接口测试
 *
 * @author xub
 * @date 2019/8/24 下午6:31
 */
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
        // age 分库
        userList.add(new User(0,0, "test0", "111"));
        userList.add(new User(0,1, "test0", "111"));
        userList.add(new User(0,0, "test0", "111"));
        userList.add(new User(0,1, "test0", "111"));
        userList.add(new User(0,0, "test0", "111"));
    }
    /**
     * @Description: 批量保存用户
     */
    @PostMapping("save-user")
    public Object saveUser() {
        return userService.insertForeach(userList);
    }

    @PostMapping("save-single-user")
    public Object saveSingleUser() {
        return userService.insert(userList.get(0));
    }

    @DeleteMapping(value = "delete/{type}/{value}")
    public boolean delete(@PathVariable Integer type, @PathVariable Integer value) {
        return userService.delete(type, value);
    }
    /**
     * @Description: 获取用户列表
     */
    @GetMapping("list-user")
    public Object listUser() {
        return userService.list();
    }

    @GetMapping("list-user/{userType}")
    public Object listUser(@PathVariable int userType) {
        return userService.listByShadow(userType);
    }

    @PostMapping("truncate-table-shadow")
    public Object truncateTableShadow() {
        return userService.truncateTableShadow();
    }
}
