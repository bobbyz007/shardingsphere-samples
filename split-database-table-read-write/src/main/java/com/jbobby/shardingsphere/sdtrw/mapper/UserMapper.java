package com.jbobby.shardingsphere.sdtrw.mapper;

import com.jbobby.shardingsphere.sdtrw.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertForeach(List<User> list);

    List<User> selectAll();
    List<User> selectAllFromMasterForcefully();
}