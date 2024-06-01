package com.jbobby.shardingsphere.svm.mapper;


import com.jbobby.shardingsphere.svm.entity.User;
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
    int insert(User user);


    void deleteById(int userId);

    void deleteByUserType(int userType);

    List<User> selectAllByShadow(int userType);

    /**
     * 获取所有用户
     */
    List<User> selectAll();

    void truncateTableShadow();

}