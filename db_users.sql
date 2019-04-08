﻿-- MySQL Script generated by MySQL Workbench
-- 03/30/19 13:10:36
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema rejestracja
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rejestracja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rejestracja` DEFAULT CHARACTER SET utf8 ;
USE `rejestracja` ;

-- -----------------------------------------------------
-- Table `rejestracja`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rejestracja`.`User` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(35) NOT NULL,
  `nazwisko` VARCHAR(50) NOT NULL,
  `stanowisko` VARCHAR(40) NULL,
  `plec` VARCHAR(25) NULL,
  `email` VARCHAR(65) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `haslo` VARCHAR(50) NOT NULL,
  `aktywnosc_konta` BOOLEAN NOT NULL DEFAULT 1,
  `data_rejestracji` TIMESTAMPNOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rejestracja`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rejestracja`.`Role` (
  `id_roli` INT NOT NULL AUTO_INCREMENT,
  `nazwa_roli` VARCHAR(45) NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id_roli`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rejestracja`.`hash_user_rola`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rejestracja`.`hash_user_rola` (
  `id_user` INT NOT NULL,
  `id_role` INT NOT NULL,
  PRIMARY KEY (`id_user`, `id_role`),
  INDEX `fk_role` (`id_role` ASC),
  INDEX `fk_user` (`id_user` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `rejestracja`.`User` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role`
    FOREIGN KEY (`id_role`)
    REFERENCES `rejestracja`.`Role` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;