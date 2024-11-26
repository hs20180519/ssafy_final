DROP TABLE IF EXISTS `ssafyhome`.`favorite_properties`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`favorite_properties`
(
    member_id VARCHAR(45) NOT NULL,
    apt_seq   VARCHAR(20) NOT NULL,
    PRIMARY KEY (member_id, apt_seq),
    FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE CASCADE,
    FOREIGN KEY (apt_seq) REFERENCES house_infos (apt_seq)
);