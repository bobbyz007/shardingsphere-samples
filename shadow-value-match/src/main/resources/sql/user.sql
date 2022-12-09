create database ds;
create database ds_shadow;

use ds;
CREATE TABLE IF NOT EXISTS t_user (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_type INT(11),
    username VARCHAR(200),
    pwd VARCHAR(200), PRIMARY KEY (user_id)
);

use ds_shadow;
CREATE TABLE IF NOT EXISTS t_user (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_type INT(11),
    username VARCHAR(200),
    pwd VARCHAR(200), PRIMARY KEY (user_id)
);