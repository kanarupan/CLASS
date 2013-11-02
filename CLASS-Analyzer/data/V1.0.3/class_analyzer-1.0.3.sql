SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `class_analyzer` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `class_analyzer` ;

-- -----------------------------------------------------
-- Table `class_analyzer`.`class_analyzer_classifier`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class_analyzer`.`class_analyzer_classifier` (
  `idclass_analyzer_clalssifier` INT NOT NULL AUTO_INCREMENT ,
  `year` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `grade` INT NOT NULL ,
  `term` INT NOT NULL ,
  `subject` VARCHAR(45) NOT NULL ,
  `model` BLOB NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `bins` INT NOT NULL ,
  PRIMARY KEY (`idclass_analyzer_clalssifier`) )
ENGINE = InnoDB;

USE `class_analyzer` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
