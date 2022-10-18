create database library_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use library_management;
create table user(
                     id int(11) not null AUTO_INCREMENT,
                     name varchar(255) comment '姓名',
                     age int(11) comment '年龄',
                     sex varchar(1) comment '性别',
                     phone varchar(255) comment '联系方式',
                     adress varchar(255) comment '地址',
                     primary key (id)
)ENGINE = InnoDB default CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;


