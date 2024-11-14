DROP TABLE IF EXISTS `ssafyhome`.`members` ;

create table members(
	id varchar(45) primary key,
    password varchar(128) not null,
    email varchar(45) not null unique,
    name varchar(45)
);