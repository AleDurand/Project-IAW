-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema real_state_agent_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `real_state_agent_db` ;

-- -----------------------------------------------------
-- Schema real_state_agent_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `real_state_agent_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `real_state_agent_db` ;

-- -----------------------------------------------------
-- Table `real_state_agent_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`user` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`real_state_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`real_state_agent` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`real_state_agent` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `web` VARCHAR(45) NULL COMMENT '',
  `email` VARCHAR(45) NULL COMMENT '',
  `user_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `user_id`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '',
  INDEX `fk_real_state_agent_user1_idx` (`user_id` ASC)  COMMENT '',
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_real_state_agent_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `real_state_agent_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`office` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `phone` VARCHAR(45) NULL COMMENT '',
  `real_state_agent_id` INT NOT NULL COMMENT '',
  `real_state_agent_user_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `real_state_agent_id`, `real_state_agent_user_id`)  COMMENT '',
  INDEX `fk_office_real_state_agent1_idx` (`real_state_agent_id` ASC, `real_state_agent_user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_office_real_state_agent1`
    FOREIGN KEY (`real_state_agent_id` , `real_state_agent_user_id`)
    REFERENCES `real_state_agent_db`.`real_state_agent` (`id` , `user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`geo_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`geo_location` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`geo_location` (
  `latitude` INT NOT NULL COMMENT '',
  `longitude` INT NOT NULL COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`address` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`address` (
  `street` VARCHAR(45) NOT NULL COMMENT '',
  `suite` VARCHAR(45) NULL COMMENT '',
  `city` VARCHAR(45) NOT NULL COMMENT '',
  `zip_code` INT NOT NULL COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`property_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`property_category` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`property_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `description` TINYTEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`photo` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`photo` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `url` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `url_UNIQUE` (`url` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`property` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`property` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `rooms` INT NOT NULL COMMENT '',
  `size` INT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `state` ENUM('available', 'unavailable') NULL COMMENT '',
  `real_state_agent_id` INT NOT NULL COMMENT '',
  `real_state_agent_user_id` INT NOT NULL COMMENT '',
  `property_category_id` INT NOT NULL COMMENT '',
  `photo_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `real_state_agent_id`, `real_state_agent_user_id`, `property_category_id`, `photo_id`)  COMMENT '',
  INDEX `fk_property_real_state_agent1_idx` (`real_state_agent_id` ASC, `real_state_agent_user_id` ASC)  COMMENT '',
  INDEX `fk_property_property_category1_idx` (`property_category_id` ASC)  COMMENT '',
  INDEX `fk_property_photo1_idx` (`photo_id` ASC)  COMMENT '',
  CONSTRAINT `fk_property_real_state_agent1`
    FOREIGN KEY (`real_state_agent_id`)
    REFERENCES `real_state_agent_db`.`real_state_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_property_category1`
    FOREIGN KEY (`property_category_id`)
    REFERENCES `real_state_agent_db`.`property_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_photo1`
    FOREIGN KEY (`photo_id`)
    REFERENCES `real_state_agent_db`.`photo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`operation` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`operation` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `type` ENUM('for_sale', 'for_rent') NOT NULL COMMENT '',
  `price` DECIMAL NOT NULL COMMENT '',
  `property_id` INT NOT NULL COMMENT '',
  `property_real_state_agent_id` INT NOT NULL COMMENT '',
  `property_real_state_agent_user_id` INT NOT NULL COMMENT '',
  `property_property_category_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `property_id`, `property_real_state_agent_id`, `property_real_state_agent_user_id`, `property_property_category_id`)  COMMENT '',
  INDEX `fk_operation_property1_idx` (`property_id` ASC, `property_real_state_agent_id` ASC, `property_real_state_agent_user_id` ASC, `property_property_category_id` ASC)  COMMENT '',
  CONSTRAINT `fk_operation_property1`
    FOREIGN KEY (`property_id` , `property_real_state_agent_id` , `property_real_state_agent_user_id` , `property_property_category_id`)
    REFERENCES `real_state_agent_db`.`property` (`id` , `real_state_agent_id` , `real_state_agent_user_id` , `property_category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
