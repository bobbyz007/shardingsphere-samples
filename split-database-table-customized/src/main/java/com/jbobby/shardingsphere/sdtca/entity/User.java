package com.jbobby.shardingsphere.sdtca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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