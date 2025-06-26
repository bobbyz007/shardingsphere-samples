package com.jbobby.shardingsphere.sdth.service;

import com.jbobby.shardingsphere.sdth.entity.User;

import java.util.List;

public interface UserService {
    List<User>  list();
    List<User>  listWithSQLHint();
    List<User>  listWithSQLHintDatasource();

    String  insertForeach(List<User> userVOList);
    boolean insertSingle(int dbValue, int tableValue, User user);

}