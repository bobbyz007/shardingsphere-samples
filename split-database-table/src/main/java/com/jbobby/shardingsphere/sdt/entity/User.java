package com.jbobby.shardingsphere.sdt.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 主键
     */
    private Long id;

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
    private Date createTime = new Date();

    /**
     *
     */
    private Date updateTime = new Date();

    /**
     * 是否删除 1删除 0未删除
     */
    private Integer status = 0;

    public User(Long id,String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}