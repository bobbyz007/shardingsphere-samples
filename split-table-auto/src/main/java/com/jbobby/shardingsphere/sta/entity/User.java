package com.jbobby.shardingsphere.sta.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    /**
     * 主键
     */
    private Long id;

    private long addrId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 是否删除 1删除 0未删除
     */
    private Integer status;

    public User(Long id, long addrId, String name, String sex, Integer age) {
        this.id = id;
        this.addrId = addrId;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}