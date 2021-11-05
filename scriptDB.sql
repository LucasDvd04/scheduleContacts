-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema schedule
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema schedule
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `schedule` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `schedule` ;

-- -----------------------------------------------------
-- Table `schedule`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schedule`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `schedule`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schedule`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ddd` VARCHAR(3) NOT NULL,
  `number` VARCHAR(9) NOT NULL,
  `idContato` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idContato` (`idContato` ASC) VISIBLE,
  CONSTRAINT `phone_ibfk_1`
    FOREIGN KEY (`idContato`)
    REFERENCES `schedule`.`contact` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
