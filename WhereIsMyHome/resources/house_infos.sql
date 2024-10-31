-- -----------------------------------------------------
-- Table `ssafyhome`.`house_infos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyhome`.`house_infos` ;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`house_infos` (
  `apt_seq` VARCHAR(20) NOT NULL,
  `sgg_cd` VARCHAR(5) NULL DEFAULT NULL,
  `umd_cd` VARCHAR(5) NULL DEFAULT NULL,
  `umd_nm` VARCHAR(20) NULL DEFAULT NULL,
  `jibun` VARCHAR(10) NULL DEFAULT NULL,
  `road_nm_sgg_cd` VARCHAR(5) NULL DEFAULT NULL,
  `road_nm` VARCHAR(20) NULL DEFAULT NULL,
  `road_nm_bonbun` VARCHAR(10) NULL DEFAULT NULL,
  `road_nm_bubun` VARCHAR(10) NULL DEFAULT NULL,
  `apt_nm` VARCHAR(40) NULL DEFAULT NULL,
  `build_year` INT NULL DEFAULT NULL,
  `latitude` VARCHAR(45) NULL DEFAULT NULL,
  `longitude` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`apt_seq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;