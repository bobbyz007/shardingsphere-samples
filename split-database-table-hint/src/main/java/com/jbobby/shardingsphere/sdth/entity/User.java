package com.jbobby.shardingsphere.sdth.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 主键
     */
    private long id;

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
    private Date createTime = new Date();

    /**
     *
     */
    private Date updateTime = new Date();

    /**
     * 是否删除 1删除 0未删除
     */
    private int status;

    public User(long id ,long addrId, String name, String sex, int age) {
        this.id = id;
        this.addrId = addrId;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}