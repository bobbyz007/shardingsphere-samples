package com.jbobby.shardingsphere.sta.controller;


import com.jbobby.shardingsphere.sta.entity.User;
import com.jbobby.shardingsphere.sta.service.UserService;
import org.apache.commons.lang3.time.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @PostMapping("save-user/{numOfUsers}")
    public Object saveUser(@PathVariable("numOfUsers") int numOfUsers) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < numOfUsers; i++) {
            userList.add(new User(0L, i + 1, "Justin", "male", 100));
        }

        return userService.insertForeach(userList);
    }

    @PostMapping("save-user/auto-interval")
    public Object saveUserWithAutoInterval() {
        List<User> userList = new ArrayList<>();
        LocalDateTime[] dateTimes = new LocalDateTime[]{
                LocalDateTime.of(2022, 3, 12, 16, 16, 16),

                LocalDateTime.of(2023, 3, 12, 16, 16, 16),
                LocalDateTime.of(2023, 4, 12, 16, 16, 16),

                LocalDateTime.of(2024, 9, 12, 16, 16, 16),
                LocalDateTime.of(2024, 10, 12, 16, 16, 16),
                LocalDateTime.of(2024, 11, 12, 16, 16, 16),

                LocalDateTime.of(2025, 3, 12, 16, 16, 16),
                LocalDateTime.of(2025, 4, 12, 16, 16, 16),

                LocalDateTime.of(2026, 1, 5, 16, 16, 16),
                LocalDateTime.of(2026, 3, 12, 16, 16, 16),
                LocalDateTime.of(2027, 3, 12, 16, 16, 16),
                LocalDateTime.of(2028, 3, 12, 16, 16, 16),
                LocalDateTime.of(2029, 3, 12, 16, 16, 16),
        };

        for (int i = 0; i < dateTimes.length; i++) {
            User user = new User(0L, i + 1, "Justin", "male", 100);
            user.setCreateTime(Date.from(dateTimes[i].atZone(ZoneId.systemDefault()).toInstant()));
            userList.add(user);
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
