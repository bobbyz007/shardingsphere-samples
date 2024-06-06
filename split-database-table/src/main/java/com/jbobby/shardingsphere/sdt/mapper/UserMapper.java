package com.jbobby.shardingsphere.sdt.mapper;

import com.jbobby.shardingsphere.sdt.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void createTableIfNotExists();

    int insertForeach(List<User> list);

    List<User> selectAll();
}