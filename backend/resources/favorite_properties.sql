DROP TABLE IF EXISTS `ssafyhome`.`favorite_areas`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`favorite_properties`
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(45) NOT NULL,
    apt_seq   VARCHAR(20) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE CASCADE,
    FOREIGN KEY (apt_seq) REFERENCES house_infos (apt_seq)
)