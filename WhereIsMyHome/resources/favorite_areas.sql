create table favorite_areas (
		favorite_area_id int primary key auto_increment,
        user_info_id int,
        sido_name varchar(45) not null,
        gungu_name varchar(45),
        dong_name varchar(45),
        foreign key(user_info_id) references user_infos(user_info_id)
);
