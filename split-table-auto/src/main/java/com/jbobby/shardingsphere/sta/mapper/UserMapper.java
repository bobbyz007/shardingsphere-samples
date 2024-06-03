package com.jbobby.shardingsphere.sta.mapper;


import com.jbobby.shardingsphere.sta.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void createTableIfNotExists();

    /**
     * 批量插入
     *
     * @param list 插入集合
     * @return 插入数量
     */
    int insertForeach(List<User> list);

    /**
     * 获取所有用户
     */
    List<User> selectAll();

}