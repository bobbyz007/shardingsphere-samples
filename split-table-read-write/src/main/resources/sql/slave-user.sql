-- 为了验证读在从库上执行， 此处额外在从库上插入一条记录，以和主库区分。
INSERT INTO `t_user_1` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (106,'哥哥','男',7,now(),now(),0);