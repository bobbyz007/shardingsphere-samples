实现定制分片算法，特别注意配置了inline算法，激活范围查询需要指定allow-range-query-with-inline-sharding: true
```shell
# 插入进入精准分片逻辑
http://127.0.0.1:8088/save-user
http://127.0.0.1:8088/save-user

# =/in 查询操作进入精准分片逻辑
http://127.0.0.1:8088/list-user-by-key

# 范围查询进入范围分片逻辑：数据库分片也是范围
http://127.0.0.1:8088/list-user-by-range

# 范围查询进入范围分片逻辑：指定某个数据库分片
http://127.0.0.1:8088/list-user-by-key-range/0
http://127.0.0.1:8088/list-user-by-key-range/1
```

自定义标准分片： src/main/resources/ss-config-standard.yaml  
自定义复合分片：src/main/resources/ss-config-complex.yaml  
自定义hint分片：src/main/resources/ss-config-hint.yaml  