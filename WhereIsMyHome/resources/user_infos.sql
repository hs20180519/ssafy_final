create table user_infos (
		user_info_id int auto_increment,
        user_email varchar(45),
        user_password varchar(64) not null,
        user_name varchar(45),
        primary key(user_info_id, user_email)
);