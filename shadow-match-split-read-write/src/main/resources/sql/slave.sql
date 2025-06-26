drop database slavedb;
create database slavedb;
use slavedb;
CREATE TABLE `t_user` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 除了自动同步自主库的记录，再额外插入一条记录。
INSERT INTO `t_user` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (20000,'妈妈','男',27,now(),now(),0);

drop database shadow_slavedb;
create database shadow_slavedb;
use shadow_slavedb;
CREATE TABLE `t_user` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 除了自动同步自主库的记录，再额外插入一条不同于slavedb的记录。
INSERT INTO `t_user` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (20000,'shadow-00','female',18,now(),now(),1);
INSERT INTO `t_user` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (20001,'shadow-01','female',19,now(),now(),1);
INSERT INTO `t_user` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (20002,'shadow-02','female',20,now(),now(),1);
INSERT INTO `t_user` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (20003,'shadow-03','female',21,now(),now(),1);