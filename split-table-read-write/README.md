## 一、项目概述
### 1、项目说明

在实际开发中，如果表的数据过大，我们可能需要把一张表拆分成多张表，这里就是通过ShardingSphere实现分表 + 主从库的读写分离功能，但不分库。

### 2、数据库设计

`分表`  t_user单表拆分为t_user_0 和 t_user_1。

`读写分离` 数据写入master库 ,数据读取 slave库 。

## 二、测试验证

### 1、批量插入数据

请求接口

```
localhost:8088/save-user
```

我们可以从商品接口代码中可以看出，它会批量插入5条数据。我们先看控制台输出SQL语句
我们可以从SQL语句可以看出	**master数据源** 中 **t_user_0** 表插入了`一条数据`，**t_user_1** 表中插入`两条数据`，**t_user_2** 表中插入`两条数据`。

完成分表插入数据。

### 2、获取数据

我们来获取列表接口的SQL。

```mysql
  http://localhost:8088/list-user
```
为了验证读在从库上执行， 此处额外在从库上插入一条记录，以和主库区分，插入语句参考src/main/resources/sql目录

`结论` 从接口返回的结果可以很明显的看出，数据存储在master主库,而数据库的读取在slave从库。

`注意` ShardingSphere并不支持`CASE WHEN`、`HAVING`、`UNION (ALL)`，`有限支持子查询`。这个官网有详细说明。