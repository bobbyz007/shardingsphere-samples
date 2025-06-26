package com.jbobby.shardingsphere.sdtca.mapper;

import com.jbobby.shardingsphere.sdtca.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int insertForeach(List<User> list);
    int insertSingle(User user);


    List<User> selectAll();

    List<User> selectByKey(Map<String, Long> params);
    List<User> selectByRange(Map<String, Map<String, Long>> params);
    List<User> selectByKeyRange(@Param("userId") Long userId, @Param("addrId") Map<String, Long> params);
}