package com.jbobby.shardingsphere.sdtca.controller;


import com.google.common.collect.Lists;
import com.jbobby.shardingsphere.sdtca.entity.User;
import com.jbobby.shardingsphere.sdtca.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        userList.add(new User(1, 100, "小小", "女", 1));
        userList.add(new User(2, 101, "爸爸", "男", 2));
        userList.add(new User(3, 102, "妈妈", "女", 3));
        userList.add(new User(4, 103, "爷爷", "男", 4));
        userList.add(new User(5, 104, "爷爷", "男", 5));
    }
    /**
     * 此方法没有设置hint值，此时会执行全库表路由，即在所有分片表中都会执行此语句。
     */
    @PostMapping("save-user")
    public Object saveUser() {
        return userService.insertForeach(userList);
    }

    /**
     {
         "id": 10,
         "addrId": 23,
         "name": "Justin",
         "age": 100,
         "sex": "male",
         "status": 0,
         "createTime": "2024-06-05 16:03:39",
         "updateTime": "2024-06-05 16:03:39"
     }
     */
    @PostMapping("save-single")
    public boolean saveSingleUser(@RequestBody User user) {
        userService.insertSingle(user);
        return true;
    }

    @GetMapping("list-user")
    public Object listUser() {
        return userService.list();
    }

    /**
     {
         "user_id": 1,
         "addr_id": 100
     }
     */
    @PostMapping("list-user-by-key")
    public Object listUserByKey(@RequestBody Map<String, Long> params) {
        return userService.listByKey(params);
    }

    /**
     {
         "user_id": {
         "lower":1,
         "upper": 1000
         },
         "addr_id": {
         "lower":1,
         "upper": 9000
         }
     }
     */
    @PostMapping("list-user-by-range")
    public Object listUserByRange(@RequestBody Map<String, Map<String, Long>> params) {
        return userService.listByRange(params);
    }

    /**
     {
         "lower":1,
         "upper": 9000
     }
     */
    @PostMapping("list-user-by-key-range/{userId}")
    public Object listUserByKeyRange(@PathVariable("userId") Long userId, @RequestBody Map<String, Long> params) {
        return userService.listByKeyRange(userId, params);
    }
}
