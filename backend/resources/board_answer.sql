DROP TABLE IF EXISTS `ssafyhome`.`board_answer`;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`board_answer`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    question_id INT  NOT NULL,
    content     TEXT NOT NULL,
    author      VARCHAR(50),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES board_question (id) ON DELETE CASCADE,
    FOREIGN KEY (author) REFERENCES members (id) ON DELETE CASCADE
);