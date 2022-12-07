-- 除了自动同步自主库的记录，再额外插入一条记录。
INSERT INTO `t_user` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (20000,'妈妈','男',27,now(),now(),0);