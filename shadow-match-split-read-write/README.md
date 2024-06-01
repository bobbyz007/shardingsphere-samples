
## 一、项目概述

### 1、项目说明

如果实际项目中Mysql是 **Master-Slave** (主从)部署的，那么数据保存到Master库，Master库数据同步数据到Slave库，数据读取到Slave库，

这样可以减缓数据库的压力。

此处演示读写分离和影子库的结合。

### 3、数据库脚本
参考工程 src/main/resources/sql 目录

## 二、测试验证

### 1、写数据

手工往slave库插入一条数据。
```shell
# 插入到masterdb
http://127.0.0.1:8088/save-user/12

# 插入到影子库shadow_masterdb
http://127.0.0.1:8088/save-user/1
```

### 2、读数据
```shell
# 根据影子规则，读取slavedb
http://127.0.0.1:8088/list-user/12

# 根据影子规则，读取shadow_slavedb
http://127.0.0.1:8088/list-user/1

```