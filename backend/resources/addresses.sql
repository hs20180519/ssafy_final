DROP TABLE IF EXISTS `ssafyhome`.`addresses`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`addresses`
(
    addressId     INT AUTO_INCREMENT PRIMARY KEY,
    memberId      VARCHAR(45) NOT NULL,
    name		VARCHAR(20),
   	roadNameAddress  VARCHAR(255),
    FOREIGN KEY (memberId) REFERENCES members (id) ON DELETE CASCADE
);
