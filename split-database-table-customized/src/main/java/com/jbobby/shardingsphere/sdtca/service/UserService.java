package com.jbobby.shardingsphere.sdtca.service;

import com.jbobby.shardingsphere.sdtca.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User>  list();
    List<User>  listByKey(Map<String, Long> params);
    List<User>  listByRange(Map<String, Map<String, Long>> params);
    List<User>  listByKeyRange(Long userId, Map<String, Long> params);

    String  insertForeach(List<User> userVOList);

    boolean insertSingle(User user);
    boolean insertSingleWithHint(long dbValue, long tableValue, User user);
}