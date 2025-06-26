## 一、项目概述
使用场景：当在正式环境中需要做全链路压测数据隔离或者是需要做测试这时候为了防止测试的数据污染生产数据库，使用影子库接收这些测试数据，影子库要和正式的生产库保持相同的配置。  

影子库：实际中使用的数据库的完整数据库数据拷贝，比如进行压测数据隔离的影子数据库，与生产数据库应当使用相同的配置。  

影子表：是实际业务数据表的一份拷贝，比如进行压测数据隔离的影子数据表。

### 1、项目说明

演示shadow影子算法 VALUE_MATCH，根据user_type列的值是否为1来匹配正式库和影子库。

插入操作：根据user_type的值分别插入到正式库和影子库。
注意：不支持批量插入
```
http://localhost:8088/save-single-user

// 批量操作不起作用
http://localhost:8088/save-user
```

删除操作：
```
// 从正式库中删除user_type为0的记录
http://localhost:8088/delete/1/0

// 从影子库中删除user_type为1的记录
http://localhost:8088/delete/1/1
```

查询操作：
```
// 从正式库中查询user_type为0 的记录
http://localhost:8088/list-user/0

# 从影子库中查询user_type为1 的记录
http://localhost:8088/list-user/1
```

truncate操作： 根据sql注释，配置hint算法匹配影子库
```
http://localhost:8088/truncate-table-shadow
```
```roomsql
// 仅truncate影子库
TRUNCATE TABLE t_user /*shadow:true,foo:bar*/;
```

### 2. 更多示例
比如shadow与读写分离、分库的结合，可参考官方样例：

application-shadow-readwrite-splitting.properties

application-shadow-sharding-databases.properties
```
https://github.com/apache/shardingsphere/tree/5.2.1/examples/shardingsphere-jdbc-example/single-feature-example/shadow-example/shadow-spring-boot-mybatis-example/src/main/resources
```
