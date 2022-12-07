
## 一、项目概述

#### 1、项目说明

在实际开发中，如果表的数据过大，我们可能需要把一张表拆分成多张表，这里就是通过ShardingSphere实现分表功能，但不分库。

#### 2、数据库设计

这里有个member库，里面的`t_user`表由一张拆分成3张，分别是`t_user_0`、`t_user_1`、`t_user_2`。

注意： 分表必须事先手工创建号，shardingsphere 不会自动创建分表。

## 二、测试验证

#### 1、批量插入数据
```
localhost:8088/save-user
```
我们可以从商品接口代码中可以看出，它会批量插入5条数据。我们先看控制台输出SQL语句

我们可以从SQL语句可以看出 **t_user_1** 和 **t_user_2** 表插入了`两条数据`，而 **t_user_0** 表中插入`一条数据`。

#### 2、获取数据

我们来获取列表的SQL，这里对SQL做了order排序操作，具体ShardingSphere分表实现order操作的原理可以看上面一篇博客。

```
  http://localhost:8088/list-user
```
我们可以看出虽然已经分表，但依然可以将多表数据聚合在一起并可以排序。

`注意` ShardingSphere并不支持`CASE WHEN`、`HAVING`、`UNION (ALL)`，`有限支持子查询`。这个官网有详细说明。