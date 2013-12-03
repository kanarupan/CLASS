SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `class` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `class` ;

-- -----------------------------------------------------
-- Table `class`.`student`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`student` (
  `idstudent` INT NOT NULL AUTO_INCREMENT ,
  `school_no` INT NOT NULL ,
  `student_school_id` VARCHAR(45) NOT NULL ,
  `gender` VARCHAR(45) NULL ,
  `from` DATE NULL ,
  `to` DATE NULL ,
  `religion` VARCHAR(45) NULL ,
  `language` VARCHAR(45) NULL ,
  `father` VARCHAR(45) NULL ,
  `mother` VARCHAR(45) NULL ,
  `siblings` INT NULL ,
  PRIMARY KEY (`school_no`, `student_school_id`) ,
  INDEX `id` USING BTREE (`idstudent` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`performance_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`performance_type` (
  `idperformance_type` INT NOT NULL AUTO_INCREMENT ,
  `performance_type_name` VARCHAR(100) NULL ,
  PRIMARY KEY (`idperformance_type`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`student_performance`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`student_performance` (
  `idstudent_performance` INT NOT NULL AUTO_INCREMENT ,
  `student_idstudent` INT NOT NULL ,
  `performance_type_idperformance_type` INT NOT NULL ,
  PRIMARY KEY (`idstudent_performance`) ,
  INDEX `fk_student_performance_student1_idx` (`student_idstudent` ASC) ,
  INDEX `fk_student_performance_performance_type1_idx` (`performance_type_idperformance_type` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`subject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`subject` (
  `idsubject` INT NOT NULL AUTO_INCREMENT ,
  `subject_code` VARCHAR(45) NULL ,
  `subject_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idsubject`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`exam_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`exam_type` (
  `idexam_type` INT NOT NULL AUTO_INCREMENT ,
  `exam_type_name` VARCHAR(100) NULL ,
  PRIMARY KEY (`idexam_type`) ,
  UNIQUE INDEX `exam_typecol_UNIQUE` (`exam_type_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`exam`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`exam` (
  `idexam` INT NOT NULL AUTO_INCREMENT ,
  `school_no` INT NOT NULL ,
  `date` DATE NOT NULL ,
  `grade` INT NOT NULL ,
  `division` VARCHAR(45) NOT NULL ,
  `term` INT NOT NULL ,
  `subject_idsubject` INT NOT NULL ,
  `exam_type_idexam_type` INT NOT NULL ,
  PRIMARY KEY (`idexam`) ,
  INDEX `fk_exam_detail_subject1_idx` (`subject_idsubject` ASC) ,
  INDEX `fk_exam_exam_type1_idx` (`exam_type_idexam_type` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`marks`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`marks` (
  `student_performance_idstudent_performance` INT NOT NULL ,
  `marks` FLOAT NULL ,
  `exam_idexam` INT NOT NULL ,
  INDEX `fk_marks_student_performance1_idx` (`student_performance_idstudent_performance` ASC) ,
  INDEX `fk_marks_exam1_idx` (`exam_idexam` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`results`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`results` (
  `student_performance_idstudent_performance` INT NOT NULL ,
  `results` VARCHAR(5) NULL ,
  `exam_idexam` INT NOT NULL ,
  INDEX `fk_results_student_performance1_idx` (`student_performance_idstudent_performance` ASC) ,
  INDEX `fk_results_exam1_idx` (`exam_idexam` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`attendance`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`attendance` (
  `student_performance_idstudent_performance` INT NOT NULL ,
  `year` YEAR NULL ,
  `term` INT NULL ,
  `total` INT NULL ,
  `present` INT NULL ,
  INDEX `fk_attendance_student_performance1_idx` (`student_performance_idstudent_performance` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`sports`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`sports` (
  `idsports` INT NOT NULL ,
  `student_performance_idstudent_performance` INT NOT NULL ,
  `performance` VARCHAR(100) NULL ,
  PRIMARY KEY (`idsports`) ,
  INDEX `fk_sports_student_performance1_idx` (`student_performance_idstudent_performance` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`community`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`community` (
  `idcommunity` INT NOT NULL ,
  `student_performance_idstudent_performance` INT NOT NULL ,
  `performance` VARCHAR(100) NULL ,
  PRIMARY KEY (`idcommunity`) ,
  INDEX `fk_community_student_performance1_idx` (`student_performance_idstudent_performance` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `class`.`class_analyzer_classifier`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `class`.`class_analyzer_classifier` (
  `idclass_analyzer_classifier` INT NOT NULL AUTO_INCREMENT ,
  `year` INT NOT NULL ,
  `grade` INT NOT NULL ,
  `term` INT NOT NULL ,
  `subject` VARCHAR(100) NOT NULL ,
  `model` LONGBLOB NOT NULL ,
  `type` VARCHAR(500) NOT NULL ,
  `bins` INT NOT NULL ,
  `modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`idclass_analyzer_classifier`) )
ENGINE = InnoDB;

USE `class` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
