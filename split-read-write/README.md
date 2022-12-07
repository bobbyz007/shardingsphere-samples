
## 一、项目概述

### 1、技术架构

项目总体技术选型

```
SpringBoot 2.7.6 + shardingsphere 5.2.1 + MySQL 8 主从
```

### 2、项目说明

如果实际项目中Mysql是 **Master-Slave** (主从)部署的，那么数据保存到Master库，Master库数据同步数据到Slave库，数据读取到Slave库，

这样可以减缓数据库的压力。

### 3、数据库脚本
参考工程 src/main/resources/sql 目录

## 二、测试验证

### 1、读数据

手工往slave库插入一条数据。
```shell
http://localhost:8088/list-user
```
我们可以发现读取的数据是slave库的数据。

### 2、写数据
```shell
http://localhost:8088/save-user
```
我们可以发现master库多了一条记录，同时slave库同步也多了一条记录。