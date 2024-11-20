DROP TABLE IF EXISTS `ssafyhome`.`addresses`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`addresses`
(
    addressId     INT AUTO_INCREMENT PRIMARY KEY,
    memberId      VARCHAR(45) NOT NULL,
    zipcode       VARCHAR(10),
    jibunAddress  VARCHAR(255),
    detailAddress VARCHAR(255),
    FOREIGN KEY (memberId) REFERENCES members (id)
);
