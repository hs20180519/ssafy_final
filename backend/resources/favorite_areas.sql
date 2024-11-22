DROP TABLE IF EXISTS `ssafyhome`.`favorite_areas`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`favorite_areas`
(
    id        int primary key auto_increment,
    member_id varchar(45),
    dong_code varchar(10),
    foreign key (member_id) references members (id) ON DELETE CASCADE,
    foreign key (dong_code) references dong_codes (dong_code)
);