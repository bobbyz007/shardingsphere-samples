## 一、项目概述

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