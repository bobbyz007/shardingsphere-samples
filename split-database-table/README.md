## 一、项目概述
此示例主要包含标准分片算法，主要包括inline和interval，分别对应配置  
src/main/resources/ss-config-inline.yaml  
src/main/resources/ss-config-interval.yaml  
### 1、项目说明

在实际开发中，如果表的数据过大我们需要把一张表拆分成多张表，也可以垂直切分把一个库拆分成多个库，这里就是通过ShardingSphere实现`分库分表`功能。

这里的分库中的每个库都包含相同的表结构。

### 2、数据库设计

`分库` ds一个库分为 **ds0库** 和 **ds1库**。

`分表`  t_user一张表分为**t_user_0表** 和 **t_user_1表**。

## 二、测试验证

### 1、批量插入数据

请求接口

```shell
# 创建分片表
http://127.0.0.1:8088/init-env

localhost:8088/save-user
```

我们可以从商品接口代码中可以看出，它会批量插入5条数据。

我们可以从SQL语句可以看出 **ds_0** 和 **ds_1** 库中都插入了数据。

分库：age % 2

分表：id % 2

ds_0库：
```
t_user_0:
+----+--------+------+------+----------------------------+----------------------------+--------+
| id | name   | sex  | age  | create_time                | update_time                | status |
+----+--------+------+------+----------------------------+----------------------------+--------+
|  2 | 爸爸   | 男   |    2 | 2022-12-07 15:10:53.154000 | 2022-12-07 15:10:53.154000 |      0 |
|  4 | 爷爷   | 男   |    4 | 2022-12-07 15:10:53.154000 | 2022-12-07 15:10:53.154000 |      0 |
+----+--------+------+------+----------------------------+----------------------------+--------+

t_user_1:
+----+--------+------+------+----------------------------+----------------------------+--------+
| id | name   | sex  | age  | create_time                | update_time                | status |
+----+--------+------+------+----------------------------+----------------------------+--------+
|  7 | 爸爸   | 男   |    2 | 2022-12-07 15:11:21.804000 | 2022-12-07 15:11:21.804000 |      0 |
|  9 | 爷爷   | 男   |    4 | 2022-12-07 15:11:21.804000 | 2022-12-07 15:11:21.804000 |      0 |
+----+--------+------+------+----------------------------+----------------------------+--------+
```
ds_1库：
```
t_user_0:
+----+--------+------+------+----------------------------+----------------------------+--------+
| id | name   | sex  | age  | create_time                | update_time                | status |
+----+--------+------+------+----------------------------+----------------------------+--------+
|  6 | 小小   | 女   |    1 | 2022-12-07 15:11:21.804000 | 2022-12-07 15:11:21.804000 |      0 |
|  8 | 妈妈   | 女   |    3 | 2022-12-07 15:11:21.804000 | 2022-12-07 15:11:21.804000 |      0 |
| 10 | 奶奶   | 女   |    5 | 2022-12-07 15:11:21.804000 | 2022-12-07 15:11:21.804000 |      0 |
+----+--------+------+------+----------------------------+----------------------------+--------+

t_user_1:
+----+--------+------+------+----------------------------+----------------------------+--------+
| id | name   | sex  | age  | create_time                | update_time                | status |
+----+--------+------+------+----------------------------+----------------------------+--------+
|  1 | 小小   | 女   |    1 | 2022-12-07 15:10:53.154000 | 2022-12-07 15:10:53.154000 |      0 |
|  3 | 妈妈   | 女   |    3 | 2022-12-07 15:10:53.154000 | 2022-12-07 15:10:53.154000 |      0 |
|  5 | 奶奶   | 女   |    5 | 2022-12-07 15:10:53.154000 | 2022-12-07 15:10:53.154000 |      0 |
+----+--------+------+------+----------------------------+----------------------------+--------+
```



#### 2、获取数据

这里获取列表接口的SQL，这里对SQL做了order排序操作，具体ShardingSphere分表实现order操作的原理可以看上面一篇博客。

```
  http://localhost:8088/list-user
```

我们可以看出虽然已经分库分表，但依然可以将多表数据聚合在一起并可以支持按**age排序**。

`注意` ShardingSphere并不支持`CASE WHEN`、`HAVING`、`UNION (ALL)`，`有限支持子查询`。这个官网有详细说明。