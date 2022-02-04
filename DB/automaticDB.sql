-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema automaticDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `automaticDB` ;

-- -----------------------------------------------------
-- Schema automaticDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `automaticDB` DEFAULT CHARACTER SET utf8 ;
USE `automaticDB` ;

-- -----------------------------------------------------
-- Table `vehicle_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle_type` ;

CREATE TABLE IF NOT EXISTS `vehicle_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle` ;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vin` VARCHAR(100) NULL,
  `make` VARCHAR(500) NULL,
  `model` VARCHAR(500) NULL,
  `year` INT NULL,
  `color` VARCHAR(100) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `vehicle_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vehicle_vehicle_type1_idx` (`vehicle_type_id` ASC),
  CONSTRAINT `fk_vehicle_vehicle_type1`
    FOREIGN KEY (`vehicle_type_id`)
    REFERENCES `vehicle_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `services` ;

CREATE TABLE IF NOT EXISTS `services` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(500) NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `odometer` INT NULL,
  `cost` DOUBLE NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `vehicle_service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle_service` ;

CREATE TABLE IF NOT EXISTS `vehicle_service` (
  `vehicle_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`vehicle_id`, `service_id`),
  INDEX `fk_Vehicle_has_Service_Service1_idx` (`service_id` ASC),
  INDEX `fk_Vehicle_has_Service_Vehicle_idx` (`vehicle_id` ASC),
  CONSTRAINT `fk_Vehicle_has_Service_Vehicle`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vehicle_has_Service_Service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(75) NOT NULL,
  `email` VARCHAR(300) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `enabled` TINYINT NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(150) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


-- -----------------------------------------------------
-- Table `user_vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_vehicle` ;

CREATE TABLE IF NOT EXISTS `user_vehicle` (
  `user_id` INT NOT NULL,
  `vehicle_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `vehicle_id`),
  INDEX `fk_user_has_Vehicle_Vehicle1_idx` (`vehicle_id` ASC),
  INDEX `fk_user_has_Vehicle_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_Vehicle_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_Vehicle_Vehicle1`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `repair_shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `repair_shop` ;

CREATE TABLE IF NOT EXISTS `repair_shop` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `location` VARCHAR(150) NULL,
  `phone_number` VARCHAR(15) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `technician`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `technician` ;

CREATE TABLE IF NOT EXISTS `technician` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(150) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shop_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_technician_maintanence_shop1_idx` (`shop_id` ASC),
  CONSTRAINT `fk_technician_maintanence_shop1`
    FOREIGN KEY (`shop_id`)
    REFERENCES `repair_shop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `service_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_location` ;

CREATE TABLE IF NOT EXISTS `service_location` (
  `service_id` INT NOT NULL,
  `repair_shop_id` INT NOT NULL,
  PRIMARY KEY (`service_id`, `repair_shop_id`),
  INDEX `fk_service_has_location_location1_idx` (`repair_shop_id` ASC),
  INDEX `fk_service_has_location_service1_idx` (`service_id` ASC),
  CONSTRAINT `fk_service_has_location_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_has_location_location1`
    FOREIGN KEY (`repair_shop_id`)
    REFERENCES `repair_shop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `technician_services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `technician_services` ;

CREATE TABLE IF NOT EXISTS `technician_services` (
  `technician_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`technician_id`, `service_id`),
  INDEX `fk_technician_has_service_service1_idx` (`service_id` ASC),
  INDEX `fk_technician_has_service_technician1_idx` (`technician_id` ASC),
  CONSTRAINT `fk_technician_has_service_technician1`
    FOREIGN KEY (`technician_id`)
    REFERENCES `technician` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_technician_has_service_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SET SQL_MODE = '';
DROP USER IF EXISTS automatic@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'automatic'@'localhost' IDENTIFIED BY 'automatic';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'automatic'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `vehicle_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `automaticDB`;
INSERT INTO `vehicle_type` (`id`, `type`) VALUES (1, 'Car');
INSERT INTO `vehicle_type` (`id`, `type`) VALUES (2, 'Truck');
INSERT INTO `vehicle_type` (`id`, `type`) VALUES (3, 'Motor-Cycle');
INSERT INTO `vehicle_type` (`id`, `type`) VALUES (4, 'Boat');
INSERT INTO `vehicle_type` (`id`, `type`) VALUES (5, 'RV');
INSERT INTO `vehicle_type` (`id`, `type`) VALUES (6, 'Other');

COMMIT;

