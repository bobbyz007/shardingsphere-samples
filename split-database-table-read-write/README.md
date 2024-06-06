## 一、项目概述
### 1、项目说明

在实际开发中，如果数据库压力大我们可以通过  **分库分表**  的基础上进行 **读写分离**，来减缓数据库压力。  

为了简化demo中配置数据库的主从同步， 此处在一个MySQL实例中创建多个数据库模拟主从同步，同样可达到验证目的。

### 2、数据库设计

`分库` master单库分库分为 master-0库 和 master-1库。

`分表`  t_user单表分为t_user_0 和 t_user_1 表。

`读写分离` 数据写入master-0库 和 master-1库，数据读取 slave-0库 和 slave-1库。

## 二、测试验证

### 1、批量插入数据

请求接口

```
localhost:8088/save-user
```

我们可以从商品接口代码中可以看出，它会批量插入5条数据。

我们可以从SQL语句可以看出 **master-0** 和 **master-1** 库中都插入了数据。

我们再来看数据库
`master-0.t_user_0`
`master-0.t_user_1`
`master-1.t_user_0`
`master-1.t_user_1`
完成分库分表插入数据。

### 2、获取数据

这里获取列表接口的SQL。

```mysql
  select *  from t_user 
```
为了验证读在从库上执行， 此处额外在从库上插入一条记录，以和主库区分。 具体可参考 src/main/resources/sql/slave-user.sql

`结论` 从接口返回的结果可以很明显的看出，数据存储在主库,而数据库的读取在从库。

`注意` ShardingSphere并不支持`CASE WHEN`、`HAVING`、`UNION (ALL)`，`有限支持子查询`。这个官网有详细说明。

### 3、强制走主库查询
```shell
# 通过SQL 查询语句中指定SQL HINT的 WRITE_ROUTE_ONLY为true，使得SQL强制走主库
http://127.0.0.1:8088/list-user-from-master-forcefully
```