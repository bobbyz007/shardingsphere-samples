```shell
# 根据hint插入到 ds_0.t_user_1
http://127.0.0.1:8088/save-single/0/1

# 根据hint插入到 ds_1.t_user_2
http://127.0.0.1:8088/save-single/1/2

# 同时还可以根据SQL注释提供的hint值来来制定，以下分别对应不指定SQL HINT以及指定SQL HINT
http://127.0.0.1:8088/list-user
http://127.0.0.1:8088/list-user-with-sql-hint

# SQL HINT数据源透传：指定数据源，不分片
http://127.0.0.1:8088/list-user-with-sql-hint-datasource
```