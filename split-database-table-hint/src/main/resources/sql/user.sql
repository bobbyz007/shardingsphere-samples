drop database ds_0;
drop database ds_1;
create database ds_0;
create database ds_1;

use ds_0;
-- 创建SQL HINT 数据源透传测试表
CREATE TABLE `t_user_hint` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_2` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

use ds_1;
CREATE TABLE `t_user_0` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_2` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addr_id` bigint NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
