package com.jbobby.shardingsphere.sdth.mapper;

import com.jbobby.shardingsphere.sdth.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 批量插入
     *
     * @param list 插入集合
     * @return 插入数量
     */
    int insertForeach(List<User> list);

    int insertSingle(User user);

    /**
     * 获取所有用户
     */
    List<User> selectAll();

}