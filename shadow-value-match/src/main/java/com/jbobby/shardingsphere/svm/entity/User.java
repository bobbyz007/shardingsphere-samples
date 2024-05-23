package com.jbobby.shardingsphere.svm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * user表
 */
@Data
@AllArgsConstructor
public class User {
    private int userId;

    private int userType;

    private String userName;

    private String pwd;
}