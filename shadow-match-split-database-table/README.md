## 一、项目概述
分库分表与影子库集结合：配置影子算法，使得原分库分表中的一些数据转移落入到对应的影子库表中。

### 1、项目说明
插入：
```shell
# 批量插入10条数据
http://127.0.0.1:8088/shadow/insert/all/10
```

查询：
```shell
# 获取生产库中的订单数据
http://127.0.0.1:8088/shadow/list-order/production
# 获取影子库中的订单数据
http://127.0.0.1:8088/shadow/list-order/shadow
# 获取所有订单数据（包括生产库和影子库）
http://127.0.0.1:8088/shadow/list-order/all

http://127.0.0.1:8088/shadow/list-order-item

http://127.0.0.1:8088/shadow/list-address
```
