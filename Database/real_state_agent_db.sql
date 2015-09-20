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
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`address` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`address` (
  `id` INT NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `suite` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `zip_code` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`office` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`, `address_id`),
  INDEX `fk_office_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_office_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `real_state_agent_db`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`geo_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`geo_location` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`geo_location` (
  `latitude` INT NOT NULL,
  `longitude` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`address_id`),
  CONSTRAINT `fk_geo_location_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `real_state_agent_db`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`real_state_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`real_state_agent` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`real_state_agent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `web` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`category` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`property` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`property` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rooms` INT NOT NULL,
  `size` INT NULL,
  `description` TEXT NULL,
  `state` ENUM('AVAILABLE', 'UNAVAILABLE') NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`, `address_id`),
  INDEX `fk_property_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_property_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `real_state_agent_db`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`operation` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`operation` (
  `type` ENUM('FOR_SALE','FOR_RENT') NOT NULL,
  `price` DECIMAL NOT NULL,
  `property_id` INT NOT NULL,
  PRIMARY KEY (`property_id`),
  INDEX `fk_operation_property1_idx` (`property_id` ASC),
  CONSTRAINT `fk_operation_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_state_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`photo` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`photo` (
  `url` VARCHAR(45) NOT NULL,
  `property_id` INT NOT NULL,
  PRIMARY KEY (`property_id`),
  UNIQUE INDEX `url_UNIQUE` (`url` ASC),
  INDEX `fk_photo_property1_idx` (`property_id` ASC),
  CONSTRAINT `fk_photo_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_state_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`user_has_real_state_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`user_has_real_state_agent` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`user_has_real_state_agent` (
  `user_id` INT NOT NULL,
  `real_state_agent_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `real_state_agent_id`),
  INDEX `fk_user_has_real_state_agent_real_state_agent1_idx` (`real_state_agent_id` ASC),
  INDEX `fk_user_has_real_state_agent_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_real_state_agent_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `real_state_agent_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_real_state_agent_real_state_agent1`
    FOREIGN KEY (`real_state_agent_id`)
    REFERENCES `real_state_agent_db`.`real_state_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`real_state_agent_has_office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`real_state_agent_has_office` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`real_state_agent_has_office` (
  `real_state_agent_id` INT NOT NULL,
  `office_id` INT NOT NULL,
  PRIMARY KEY (`real_state_agent_id`, `office_id`),
  INDEX `fk_office_has_real_state_agent_real_state_agent1_idx` (`real_state_agent_id` ASC),
  INDEX `fk_office_has_real_state_agent_office1_idx` (`office_id` ASC),
  CONSTRAINT `fk_office_has_real_state_agent_office1`
    FOREIGN KEY (`office_id`)
    REFERENCES `real_state_agent_db`.`office` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_office_has_real_state_agent_real_state_agent1`
    FOREIGN KEY (`real_state_agent_id`)
    REFERENCES `real_state_agent_db`.`real_state_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`real_state_agent_has_property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`real_state_agent_has_property` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`real_state_agent_has_property` (
  `real_state_agent_id` INT NOT NULL,
  `property_id` INT NOT NULL,
  PRIMARY KEY (`real_state_agent_id`, `property_id`),
  INDEX `fk_real_state_agent_has_property_property1_idx` (`property_id` ASC),
  INDEX `fk_real_state_agent_has_property_real_state_agent1_idx` (`real_state_agent_id` ASC),
  CONSTRAINT `fk_real_state_agent_has_property_real_state_agent1`
    FOREIGN KEY (`real_state_agent_id`)
    REFERENCES `real_state_agent_db`.`real_state_agent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_real_state_agent_has_property_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_state_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `real_state_agent_db`.`property_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `real_state_agent_db`.`property_has_category` ;

CREATE TABLE IF NOT EXISTS `real_state_agent_db`.`property_has_category` (
  `property_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`property_id`, `category_id`),
  INDEX `fk_category_has_property_property1_idx` (`property_id` ASC),
  INDEX `fk_category_has_property_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_category_has_property_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `real_state_agent_db`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_property_property1`
    FOREIGN KEY (`property_id`)
    REFERENCES `real_state_agent_db`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
