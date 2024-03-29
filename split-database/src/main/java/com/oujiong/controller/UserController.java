package com.oujiong.controller;


import com.google.common.collect.Lists;
import com.oujiong.entity.User;
import com.oujiong.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        userList.add(new User(0L,"小小", "女", 1));
        userList.add(new User(0L,"爸爸", "男", 2));
        userList.add(new User(0L,"妈妈", "女", 3));
        userList.add(new User(0L,"爷爷", "男", 4));
        userList.add(new User(0L,"奶奶", "女", 5));
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
