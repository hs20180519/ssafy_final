DROP TABLE IF EXISTS `ssafyhome`.`addresses`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`addresses`
(
    address_id     INT AUTO_INCREMENT PRIMARY KEY,
    member_id      VARCHAR(45) NOT NULL,
    name		VARCHAR(20),
   	road_name_address  VARCHAR(255),
    FOREIGN KEY (member_id) REFERENCES members (id) ON DELETE CASCADE
);