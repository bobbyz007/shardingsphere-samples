-- 为了验证读在从库上执行， 此处额外在从库上插入一条记录，以和主库区分。
-- 连接到从库，执行语句
use testdb_1;
-- age 和 id都是奇数，落入到 test_1.t_user_1
INSERT INTO `t_user_1` (`id`, `name`, `sex`, `age`, `create_time`, `update_time`, `status`)
VALUES (111,'哥哥','男',7,now(),now(),0);