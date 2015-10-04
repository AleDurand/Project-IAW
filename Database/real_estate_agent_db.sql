-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema real_estate_agent_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `real_estate_agent_db` ;

-- -----------------------------------------------------
-- Schema real_estate_agent_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `real_estate_agent_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `real_estate_agent_db` ;

-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`user` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`address` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `street` VARCHAR(45) NOT NULL COMMENT '',
  `suite` VARCHAR(45) NULL COMMENT '',
  `city` VARCHAR(45) NOT NULL COMMENT '',
  `zip_code` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`office` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `phone` VARCHAR(45) NULL COMMENT '',
  `address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `address_id`)  COMMENT '',
  INDEX `fk_office_address1_idx` (`address_id` ASC)  COMMENT '',
  CONSTRAINT `fk_office_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `real_estate_agent_db`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`geo_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`geo_location` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`geo_location` (
  `latitude` INT NOT NULL COMMENT '',
  `longitude` INT NOT NULL COMMENT '',
  `address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`address_id`)  COMMENT '',
  CONSTRAINT `fk_geo_location_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `real_estate_agent_db`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`real_estate_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`real_estate_agent` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`real_estate_agent` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `web` VARCHAR(45) NULL COMMENT '',
  `email` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`category` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `description` TINYTEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`property` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`property` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `rooms` INT NOT NULL COMMENT '',
  `size` INT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `state` ENUM('AVAILABLE', 'UNAVAILABLE') NULL COMMENT '',
  `address_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `address_id`)  COMMENT '',
  INDEX `fk_property_address1_idx` (`address_id` ASC)  COMMENT '',
  CONSTRAINT `fk_property_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `real_estate_agent_db`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`operation` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`operation` (
  `type` ENUM('FOR_SALE','FOR_RENT') NOT NULL COMMENT '',
  `price` DECIMAL NOT NULL COMMENT '',
  `property_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`property_id`, `type`)  COMMENT '',
  INDEX `fk_operation_property1_idx` (`property_id` ASC)  COMMENT '',
  CONSTRAINT `fk_operation_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_estate_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`photo` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`photo` (
  `url` VARCHAR(45) NOT NULL COMMENT '',
  `property_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`property_id`)  COMMENT '',
  UNIQUE INDEX `url_UNIQUE` (`url` ASC)  COMMENT '',
  INDEX `fk_photo_property1_idx` (`property_id` ASC)  COMMENT '',
  CONSTRAINT `fk_photo_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_estate_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`user_has_real_estate_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`user_has_real_estate_agent` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`user_has_real_estate_agent` (
  `user_id` INT NOT NULL COMMENT '',
  `real_estate_agent_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`user_id`, `real_estate_agent_id`)  COMMENT '',
  INDEX `fk_user_has_real_estate_agent_real_estate_agent1_idx` (`real_estate_agent_id` ASC)  COMMENT '',
  INDEX `fk_user_has_real_estate_agent_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_user_has_real_estate_agent_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `real_estate_agent_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_real_state_agent_real_estate_agent1`
    FOREIGN KEY (`real_estate_agent_id`)
    REFERENCES `real_estate_agent_db`.`real_estate_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`real_estate_agent_has_office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`real_estate_agent_has_office` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`real_estate_agent_has_office` (
  `real_estate_agent_id` INT NOT NULL COMMENT '',
  `office_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`real_estate_agent_id`, `office_id`)  COMMENT '',
  INDEX `fk_office_has_real_estate_agent_real_estate_agent1_idx` (`real_estate_agent_id` ASC)  COMMENT '',
  INDEX `fk_office_has_real_estate_agent_office1_idx` (`office_id` ASC)  COMMENT '',
  CONSTRAINT `fk_office_has_real_estate_agent_office1`
    FOREIGN KEY (`office_id`)
    REFERENCES `real_estate_agent_db`.`office` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_office_has_real_estate_agent_real_state_agent1`
    FOREIGN KEY (`real_estate_agent_id`)
    REFERENCES `real_estate_agent_db`.`real_estate_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`real_estate_agent_has_property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`real_estate_agent_has_property` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`real_estate_agent_has_property` (
  `real_estate_agent_id` INT NOT NULL COMMENT '',
  `property_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`real_estate_agent_id`, `property_id`)  COMMENT '',
  INDEX `fk_real_estate_agent_has_property_property1_idx` (`property_id` ASC)  COMMENT '',
  INDEX `fk_real_estate_agent_has_property_real_estate_agent1_idx` (`real_estate_agent_id` ASC)  COMMENT '',
  CONSTRAINT `fk_real_estate_agent_has_property_real_estate_agent1`
    FOREIGN KEY (`real_estate_agent_id`)
    REFERENCES `real_estate_agent_db`.`real_estate_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_real_estate_agent_has_property_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_estate_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_estate_agent_db`.`property_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_estate_agent_db`.`property_has_category` ;

CREATE TABLE IF NOT EXISTS `real_estate_agent_db`.`property_has_category` (
  `property_id` INT NOT NULL COMMENT '',
  `category_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`property_id`, `category_id`)  COMMENT '',
  INDEX `fk_category_has_property_property1_idx` (`property_id` ASC)  COMMENT '',
  INDEX `fk_category_has_property_category1_idx` (`category_id` ASC)  COMMENT '',
  CONSTRAINT `fk_category_has_property_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `real_estate_agent_db`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_property_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_estate_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
