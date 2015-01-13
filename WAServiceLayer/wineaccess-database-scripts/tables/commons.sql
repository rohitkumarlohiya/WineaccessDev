-- -----------------------------------------------------
-- Table `wineaccess`.`MasterDataType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MASTER_DATA_TYPE`;
CREATE TABLE IF NOT EXISTS `MASTER_DATA_TYPE` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`NAME` 						                VARCHAR(45) NOT NULL,
	`DESCRIPTION` 						        TEXT NULL,
	`STATUS` 						        TINYINT(1) NOT NULL,
	`INSERT_TIME` 							DATETIME NOT NULL ,
	`UPDATE_TIME` 						        DATETIME NULL ,
	`INSERTED_BY` 							BIGINT(20) UNSIGNED NOT NULL,
	`UPDATED_BY` 					                BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_master_data_type_inserted_idx` (`INSERTED_BY` ASC),
  INDEX `fk_master_data_type_updated_idx` (`UPDATED_BY` ASC),
   CONSTRAINT `fk_master_data_type_created_by`
    FOREIGN KEY (`INSERTED_BY`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `fk_master_data_type_updated_by`
    FOREIGN KEY (`UPDATED_BY`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wineaccess`.`MasterData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MASTER_DATA`;
CREATE TABLE IF NOT EXISTS `MASTER_DATA` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`MASTER_DATA_TYPE_ID`						BIGINT(20) UNSIGNED NOT NULL ,
	`INSERT_TIME` 							DATETIME NOT NULL ,
	`UPDATE_TIME` 						        DATETIME NULL ,
	`INSERTED_BY` 							BIGINT(20) UNSIGNED NOT NULL,
	`UPDATED_BY` 					                BIGINT(20) UNSIGNED NULL,
	`NAME` 								VARCHAR(45) NOT NULL ,
	`LABEL` 							VARCHAR(45) NULL ,	
	
  PRIMARY KEY (`ID`),
  INDEX `fk_master_data_master_data_types_idx` (`MASTER_DATA_TYPE_ID` ASC),
  INDEX `fk_master_data_inserted_idx` (`INSERTED_BY` ASC),
  INDEX `fk_master_data_updated_idx` (`UPDATED_BY` ASC),
  CONSTRAINT `fk_master_data_master_data_types`
    FOREIGN KEY (`MASTER_DATA_TYPE_ID`)
    REFERENCES `MASTER_DATA_TYPE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `fk_master_data_created_by`
    FOREIGN KEY (`INSERTED_BY`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `fk_master_data_updated_by`
    FOREIGN KEY (`UPDATED_BY`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;