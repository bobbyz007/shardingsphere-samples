# Shardingsphere Samples
## 一、技术架构

项目总体技术选型

```
SpringBoot 3.5.3 + shardingsphere 5.5.2 + MySQL 8.4.5 主从
```

## 二、数据分片算法
具体分片算法可参考：
https://shardingsphere.apache.org/document/current/cn/user-manual/common-config/builtin-algorithm/sharding/
https://blog.csdn.net/qq_36641443/article/details/127343113

## 三、mysql 8 主从搭建
refer: https://www.cnblogs.com/liuqingzheng/p/16328887.html

1. 启动主从库容器（挂载外部目录，端口映射成 33306/33307，密码设置为123456）
```shell
docker run  -di -v /home/justin/workspace/mysql/master/data/:/var/lib/mysql -v /home/justin/workspace/mysql/master/conf.d:/etc/mysql/conf.d -v /home/justin/workspace/mysql/master/my.cnf:/etc/mysql/my.cnf -p 33306:3306 --name mysql-master -e MYSQL_ROOT_PASSWORD=123456 mysql:8.4.5

docker run  -di -v /home/justin/workspace/mysql/slave/data/:/var/lib/mysql -v /home/justin/workspace/mysql/slave/conf.d:/etc/mysql/conf.d -v /home/justin/workspace/mysql/slave/my.cnf:/etc/mysql/my.cnf -p 33307:3306 --name mysql-slave -e MYSQL_ROOT_PASSWORD=123456 mysql:8.4.5
```
注意：事先创建好对应的目录，比如master/data, master/conf.d, master/my.cnf

分别进入主库和从库容器：授权
```shell
#找到mysql容器
docker ps -a
docker exec -ti 6d8efcfb544f /bin/bash

# enter container
mysql -uroot -p
grant all privileges on *.* to 'root'@'%';
```
主库my.cnf
```properties
[mysqld]
user = mysql
character-set-server = UTF8MB4
lower_case_table_names = 0
secure_file_priv = /var/lib/mysql
binlog_expire_logs_seconds = 259200
sql_mode = STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
max_connections = 1000

##主库----start--- 同一局域网内注意要唯一
server-id = 100  

## 开启二进制日志功能，可以随便取（关键）
log-bin = mysql-bin

##主库----end--- 
```
从库my.cnf
```properties
[mysqld]
user=mysql
character-set-server=UTF8MB4
lower_case_table_names = 0
secure_file_priv=/var/lib/mysql
binlog_expire_logs_seconds = 259200
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
max_connections=1000

##从库----start--- 
## 设置server_id,注意要唯一
server-id=101  

## 开启二进制日志功能，以备Slave作为其它Slave的Master时使用
log-bin=mysql-slave-bin   

## relay_log配置中继日志
relay_log=edu-mysql-relay-bin 
##从库----end--- 

[client]
default-character-set=utf8

[mysql]
default-character-set=utf8
```

2. 连接主库
```shell
mysql -h 127.0.0.1 -P 33306 -uroot -p123456
```
```shell
#在主库创建用户并授权
##创建test用户
create user 'test'@'%' identified by '123456';

##授权用户
grant all privileges on *.* to 'test'@'%';

###刷新权限
flush privileges;

#查看主服务器状态(显示如下图)
show binary log status;
```

3. 连接从库

因为默认使用了caching_sha2_password认证机制，需要设置通过秘钥进行安全的连接
```shell
# 也可以使用 --server-public-key-path= 指定服务端的公钥文件（把服务端的公钥文件拷贝出来）
mysql -h 127.0.0.1 -P 33307 -u root -p123456 --get-server-public-key
```
注意：在连接主库的change master命令中的master_host指定的是容器名 mysql-master，
这里是通过自定义network来实现通过容器名可直接互相访问，默认的docker0是不支持容器名访问的。
设置步骤如下：
```shell
docker network create test-network
docker network connect test-network mysql-master
docker network connect test-network mysql-slave
docker network ls
# 查看test-network已经桥接上了master和slave
docker network inspect test-network

#释放原来的docker0
docker network disconnect bridge mysql-master
docker network disconnect bridge mysql-slave
```

```shell
#配置详解
# change master to
# master_host='MySQL主服务器IP地址',
# master_user='之前在MySQL主服务器上面创建的用户名'，
# master_password='之前创建的密码',
# master_log_file='MySQL主服务器状态中的二进制文件名',
# master_log_pos='MySQL主服务器状态中的position值';
#命令如下
CHANGE REPLICATION SOURCE TO SOURCE_HOST='mysql-master',SOURCE_PORT=3306,SOURCE_USER='test',SOURCE_PASSWORD='123456',SOURCE_LOG_FILE='mysql-bin.000003',SOURCE_LOG_POS=0;
#启用从库
start replica;
#查看从库状态（如下图）
SHOW REPLICA STATUS\G
```
如果需要重新设置主库，则参考以下命令:
```shell
stop replica;
reset replica;
# 重置后再进行设置
```
参考： https://www.jianshu.com/p/3f893f42bfd4

4. 测试主从同步
```shell
#在主库上创建数据库test1
create database test1;
use test1;
#创建表
create table t_tom (id int not null,name varchar(100)not null ,age tinyint);
#插入数据
insert tom (id,name,age) values(1,'xxx',20),(2,'yyy',7),(3,'zzz',23);
```

```shell
#在从库上查看是否同步成功
#查看数据库
show database;
use test1;
#查看表
show tables;
#查看数据
select * from test1;
```


