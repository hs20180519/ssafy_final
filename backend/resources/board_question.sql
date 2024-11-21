DROP TABLE IF EXISTS `ssafyhome`.`board_question`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`board_question`
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    author     VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    view_count INT       DEFAULT 0,
    FOREIGN KEY (author) REFERENCES members (id) ON DELETE CASCADE
);
