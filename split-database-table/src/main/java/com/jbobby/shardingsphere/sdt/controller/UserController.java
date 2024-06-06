package com.jbobby.shardingsphere.sdt.controller;


import com.google.common.collect.Lists;
import com.jbobby.shardingsphere.sdt.entity.User;
import com.jbobby.shardingsphere.sdt.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("init-env")
    public boolean cleanEnvironment() {
        userService.initEnvironment();
        return true;
    }

    @PostMapping("save-user")
    public Object saveUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 保证根据age和id分库分表可以分布到各个数据分片中
            User user = new User(0L, "小小", "女", i + 1);
            User user2 = new User(0L, "小小", "女", i + 1);

            userList.add(user);
            userList.add(user2);
        }
        return userService.insertForeach(userList);
    }

    @PostMapping("save-user/interval")
    public Object saveUserWithInterval() {
        List<User> userList = new ArrayList<>();
        LocalDateTime[] dateTimes = new LocalDateTime[]{
                LocalDateTime.of(2024, 1, 12, 16, 16, 16),
                LocalDateTime.of(2024, 2, 12, 16, 16, 16),
                LocalDateTime.of(2024, 3, 12, 16, 16, 16),
                LocalDateTime.of(2024, 4, 12, 16, 16, 16),
                LocalDateTime.of(2024, 5, 12, 16, 16, 16),
                LocalDateTime.of(2024, 6, 5, 16, 16, 16),

                // 超过shardingsphere配置的分片范围则会报错
                // LocalDateTime.of(2024, 7, 5, 16, 16, 16),
        };

        for (int i = 0; i < dateTimes.length; i++) {
            User user = new User(0L, "Justin", "male", 100 + i);
            user.setCreateTime(Date.from(dateTimes[i].atZone(ZoneId.systemDefault()).toInstant()));
            userList.add(user);
        }
        return userService.insertForeach(userList);
    }

    @GetMapping("list-user")
    public Object listUser() {
        return userService.list();
    }


}
