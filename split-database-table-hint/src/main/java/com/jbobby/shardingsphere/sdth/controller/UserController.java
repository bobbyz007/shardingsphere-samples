package com.jbobby.shardingsphere.sdth.controller;

import com.google.common.collect.Lists;
import com.jbobby.shardingsphere.sdth.entity.User;
import com.jbobby.shardingsphere.sdth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 此方法没有设置hint值，此时会执行全库表路由，即在所有分片表中都会执行此语句。
     */
    @PostMapping("save-user")
    public Object saveUser() {
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            userList.add(new User(0, i + 100, "小小", "女", i + 1));
        }
        return userService.insertForeach(userList);
    }

    @PostMapping("save-single/{dbValue}/{tableValue}")
    public boolean saveSingleUser(@PathVariable("dbValue") int dbValue,
                                  @PathVariable("tableValue") int tableValue) {
        User user = new User(0, 999, "Justin", "male", 100);
        userService.insertSingle(dbValue, tableValue, user);
        return true;
    }

    /**
     * 此方法没有设置hint值，此时会执行全库表路由，即在所有分片表中都会执行此语句然后汇总结果。
     */
    @GetMapping("list-user")
    public Object listUser() {
        return userService.list();
    }

    @GetMapping("list-user-with-sql-hint")
    public Object listUserWithSQLHint() {
        return userService.listWithSQLHint();
    }

    @GetMapping("list-user-with-sql-hint-datasource")
    public Object listUserWithSQLHintDatasource() {
        return userService.listWithSQLHintDatasource();
    }
}
