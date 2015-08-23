-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema prueba
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `prueba` ;

-- -----------------------------------------------------
-- Schema prueba
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prueba` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `prueba` ;

-- -----------------------------------------------------
-- Table `prueba`.`Tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba`.`Tag` ;

CREATE TABLE IF NOT EXISTS `prueba`.`Tag` (
  `id` BIGINT(20) NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba`.`Commerce`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba`.`Commerce` ;

CREATE TABLE IF NOT EXISTS `prueba`.`Commerce` (
  `id` BIGINT(20) NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `web` VARCHAR(45) NULL COMMENT '',
  `description` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba`.`Office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba`.`Office` ;

CREATE TABLE IF NOT EXISTS `prueba`.`Office` (
  `id` BIGINT(20) NOT NULL COMMENT '',
  `name` VARCHAR(45) NULL COMMENT '',
  `latitude` BIGINT(20) NULL COMMENT '',
  `longitude` BIGINT(20) NULL COMMENT '',
  `description` VARCHAR(45) NULL COMMENT '',
  `hours` VARCHAR(45) NULL COMMENT '',
  `address` VARCHAR(45) NULL COMMENT '',
  `Commerce_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `Commerce_id`)  COMMENT '',
  INDEX `fk_Office_Commerce1_idx` (`Commerce_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Office_Commerce1`
    FOREIGN KEY (`Commerce_id`)
    REFERENCES `prueba`.`Commerce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba`.`Phone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba`.`Phone` ;

CREATE TABLE IF NOT EXISTS `prueba`.`Phone` (
  `id` BIGINT(20) NOT NULL COMMENT '',
  `cod_country` INT(10) NOT NULL COMMENT '',
  `type` INT(10) NOT NULL COMMENT '',
  `cod_area` VARCHAR(45) NOT NULL COMMENT '',
  `number` VARCHAR(45) NOT NULL COMMENT '',
  `Office_id` BIGINT(20) NOT NULL COMMENT '',
  `Office_Commerce_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `Office_id`, `Office_Commerce_id`)  COMMENT '',
  INDEX `fk_Phone_Office1_idx` (`Office_id` ASC, `Office_Commerce_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Phone_Office1`
    FOREIGN KEY (`Office_id` , `Office_Commerce_id`)
    REFERENCES `prueba`.`Office` (`id` , `Commerce_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba`.`Email`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba`.`Email` ;

CREATE TABLE IF NOT EXISTS `prueba`.`Email` (
  `id` BIGINT NOT NULL COMMENT '',
  `mail` VARCHAR(45) NOT NULL COMMENT '',
  `Office_id` BIGINT(20) NOT NULL COMMENT '',
  `Office_Commerce_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `Office_id`, `Office_Commerce_id`)  COMMENT '',
  INDEX `fk_Email_Office1_idx` (`Office_id` ASC, `Office_Commerce_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Email_Office1`
    FOREIGN KEY (`Office_id` , `Office_Commerce_id`)
    REFERENCES `prueba`.`Office` (`id` , `Commerce_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `prueba`.`Commerce_has_Tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prueba`.`Commerce_has_Tag` ;

CREATE TABLE IF NOT EXISTS `prueba`.`Commerce_has_Tag` (
  `Commerce_id` BIGINT(20) NOT NULL COMMENT '',
  `Tag_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`Commerce_id`, `Tag_id`)  COMMENT '',
  INDEX `fk_Commerce_has_Tag_Tag1_idx` (`Tag_id` ASC)  COMMENT '',
  INDEX `fk_Commerce_has_Tag_Commerce1_idx` (`Commerce_id` ASC)  COMMENT '',
  CONSTRAINT `fk_Commerce_has_Tag_Commerce1`
    FOREIGN KEY (`Commerce_id`)
    REFERENCES `prueba`.`Commerce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Commerce_has_Tag_Tag1`
    FOREIGN KEY (`Tag_id`)
    REFERENCES `prueba`.`Tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
