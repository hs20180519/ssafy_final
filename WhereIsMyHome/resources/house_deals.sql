-- -----------------------------------------------------
-- Table `ssafyhome`.`housedeals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyhome`.`housedeals` ;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`housedeals` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `apt_seq` VARCHAR(20) NULL DEFAULT NULL,
  `apt_dong` VARCHAR(40) NULL DEFAULT NULL,
  `floor` VARCHAR(3) NULL DEFAULT NULL,
  `deal_year` INT NULL DEFAULT NULL,
  `deal_month` INT NULL DEFAULT NULL,
  `deal_day` INT NULL DEFAULT NULL,
  `exclu_use_ar` DECIMAL(7,2) NULL DEFAULT NULL,
  `deal_amount` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `apt_seq_to_house_info_idx` (`apt_seq` ASC) VISIBLE,
  CONSTRAINT `apt_seq_to_house_info`
    FOREIGN KEY (`apt_seq`)
    REFERENCES `ssafyhome`.`houseinfos` (`apt_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 7084512
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;