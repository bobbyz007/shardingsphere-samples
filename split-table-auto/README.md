## 一、项目概述

### 1、项目说明

为了帮助用户更好的使用分片功能，降低配置复杂度和提升使用体验，Apache ShardingSphere 5.0.0 版本推出了一种新的分片配置方式：AutoTable。

顾名思义，AutoTable 类型的数据表，交由 ShardingSphere 自动管理分片，用户只需要指定分片数量和使用的数据源，无需再关心表的具体分布。


首先执行sql/user.sql创建数据库，不需要创建数据库表，shardingsphere会自动创建。
```shell
# 自动在数据库中分片，创建对应的数据库表
http://127.0.0.1:8088/autotable/init-env

# 根据自动分片规则， 自动分配到对应表格中
http://127.0.0.1:8088/autotable/save-user

# 获取所有数据
http://127.0.0.1:8088/autotable/list-user
```