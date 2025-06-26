package com.jbobby.shardingsphere.sdth.mapper;

import com.jbobby.shardingsphere.sdth.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertForeach(List<User> list);
    int insertSingle(User user);

    List<User> selectAll();
    List<User> selectAllWithSQLHint();
    List<User> selectAllWithSQLHintDatasource();
}