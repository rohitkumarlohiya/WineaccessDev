-- -----------------------------------------------------
-- Table `wineaccess`.`UserRole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE IF NOT EXISTS `USER_ROLE` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`ROLE_NAME` 						VARCHAR(45) NOT NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wineaccess`.`UserLegacyData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_LEGACY_DATA`;
CREATE TABLE IF NOT EXISTS `USER_LEGACY_DATA` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`LEGACY_DATA1` 						VARCHAR(45) NULL,
	`LEGACY_DATA2` 						VARCHAR(45) NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USERS`;
CREATE TABLE IF NOT EXISTS `USERS` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`FIRST_NAME` 						VARCHAR(255) NULL,
	`LAST_NAME` 						VARCHAR(255) NULL,
	`EMAIL` 							VARCHAR(255) NULL,
	`IS_ENABLED` 						TINYINT(1) NULL,
	`IS_RECIEVED_NEWS_LETTER` 			TINYINT(1) NULL,
	`SALUTATION`				 		VARCHAR(55) NULL,
	`GENDER` 							VARCHAR(45) NULL,  
	`USER_ROLE_ID` 						BIGINT(20) NULL,
	`USER_ACTIVATION_CODE` 				VARCHAR(50) NULL,
	`USER_REFERRAL_SOURCE_ID` 			BIGINT(20) UNSIGNED NULL,
	`PASSWORD` 							VARCHAR(100) NULL,
	`USER_LEGACY_DATA_ID` 				BIGINT(20) NULL,
	`ZIPCODE` 							INT(11) NULL,
	`STATE` 							VARCHAR(45) NULL,
    `IS_REGISTERED` 					TINYINT(1) NULL,
	`IS_STORE_SIGN_UP_USER` 			TINYINT(1) NULL,
	`FAILURE_COUNT` 					INT(2) NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`UserSession`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_SESSION`;
CREATE TABLE IF NOT EXISTS `USER_SESSION` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`TOKEN`								VARCHAR(200) NOT NULL ,
	`USER_ID` 							BIGINT(20) UNSIGNED NOT NULL,
	`IP_ADDRESS` 						VARCHAR(45) NULL,
	`BROWSER` 							VARCHAR(255) NULL,
	`OPERATING_SYSTEM` 					VARCHAR(255) NULL,
	`SESSION_START_TIME` 				DATETIME NOT NULL,
	`SESSION_END_TIME` 					DATETIME NULL,	
	`PALTEFORM_DEVICE`					VARCHAR(255) NULL,	
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`),
  INDEX `fk_user_sessions_users` (`USER_ID` ASC),
  CONSTRAINT `fk_user_sessions_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`UserActivityLog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_ACTIVITY_LOG`;
CREATE TABLE IF NOT EXISTS `USER_ACTIVITY_LOG` (
	`Id` 									BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`USER_ID` 								BIGINT(20) UNSIGNED NOT NULL,
	`ACTIVITY` 								TEXT NOT NULL,
	`USER_SESSION_ID` 						BIGINT(20) UNSIGNED NOT NULL,
	`CREATED_DATE` 							DATETIME NOT NULL,
	`CREATED_BY` 							VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 							DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 							VARCHAR(100) DEFAULT NULL , 
  PRIMARY KEY (`ID`),
  INDEX `fk_activity_logs_users` (`USER_ID` ASC),
  INDEX `fk_UserActivityLog_UserSession1_idx` (`USER_SESSION_ID` ASC),
  CONSTRAINT `fk_activity_logs_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserActivityLog_UserSession`
    FOREIGN KEY (`USER_SESSION_ID`)
    REFERENCES `USER_SESSION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`UserCredit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_CREDIT`;
CREATE TABLE IF NOT EXISTS `USER_CREDIT` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`USER_ID` 							BIGINT(20) UNSIGNED NOT NULL,
	`TRANSACTION_REASON` 				VARCHAR(255) NULL,
	`TRANSACTION_AMOUNT` 				DECIMAL(10,4) NULL,
	`EXPIRATION_DATE` 					DATETIME NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL , 
  PRIMARY KEY (`ID`),
  INDEX `fk_user_credits_users` (`USER_ID` ASC),
  CONSTRAINT `fk_user_credits_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`AddressPreference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ADDRESS_PREFERENCE`;
CREATE TABLE IF NOT EXISTS `ADDRESS_PREFERENCE` (
	`ID` 										BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`PREFERED_CARRIER` 							VARCHAR(45) NULL,
	`PREFERRED_SHIPPING_METHOD` 				VARCHAR(45) NULL,
	`PREFERED_PACKAGE_TYPE` 					VARCHAR(45) NULL,
	`COMBINE_SHIPPMENTS_WHEN_APPROPRIATE`		VARCHAR(45) NULL,
	`DELIVERY_STARTING` 						TIME NULL,
	`DELIVERY_ENDING` 							TIME NULL,
	`DESIRED_DELIVERY_DATE` 					DATE NULL,
	`CREATED_DATE` 								DATETIME NOT NULL,
	`CREATED_BY` 								VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 								DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 								VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`UserAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_ADDRESS`;
CREATE TABLE IF NOT EXISTS `USER_ADDRESS` (
	`ID` 										BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`ADDRESS_LINE1` 							VARCHAR(255) NULL,
	`ADDRESS_LINE2` 							VARCHAR(255) NULL,
	`CITY` 										INT(11) NULL,
	`STATE` 									INT(11) NULL,
	`COUNTRY` 									INT(11) NULL,
	`ZIPCODE` 									INT(11) NULL,
	`PHONE` 									INT(45) NULL,
	`FIRST_NAME` 								VARCHAR(255) NULL,
	`LAST_NAME` 								VARCHAR(255) NULL,
	`BUSINESS_NAME` 							VARCHAR(255) NULL,
	`BIRTH_DATE` 								DATETIME NULL,
	`USER_ID` 									BIGINT(20) UNSIGNED NULL,
	`ADDRESS_PREFERENCE_ID` 					BIGINT(20) UNSIGNED NULL,
	`CREATED_DATE` 								DATETIME NOT NULL,
	`CREATED_BY` 								VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 								DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 								VARCHAR(100) DEFAULT NULL ,  
  PRIMARY KEY (`ID`),
  INDEX `fk_UserAddress_User1_idx` (`USER_ID` ASC),
  INDEX `fk_UserAddress_AddressPreference1_idx` (`ADDRESS_PREFERENCE_ID` ASC),
  CONSTRAINT `fk_UserAddress_User1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserAddress_AddressPreference1`
    FOREIGN KEY (`ADDRESS_PREFERENCE_ID`)
    REFERENCES `ADDRESS_PREFERENCE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`UserGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_GROUP`;
CREATE TABLE IF NOT EXISTS `USER_GROUP` (
	`ID` 										BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`NAME` 										VARCHAR(45) NULL,
	`DESC` 										VARCHAR(255) NULL,
	`CREATED_DATE` 								DATETIME NOT NULL,
	`CREATED_BY` 								VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 								DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 								VARCHAR(100) DEFAULT NULL ,
PRIMARY KEY (`ID`))
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `wineaccess`.`GroupUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GROUP_USER`;
CREATE TABLE IF NOT EXISTS `GROUP_USER` (
	`ID` 										BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`USER_ID` 									BIGINT(20) UNSIGNED NOT NULL,
	`GROUP_ID` 									BIGINT(20) UNSIGNED NULL,
	`CREATED_DATE` 								DATETIME NOT NULL,
	`CREATED_BY` 								VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 								DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 								VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`Id`),
  INDEX `fk_group_users_users` (`USER_ID` ASC),
  INDEX `fk_group_users_user_groups` (`GROUP_ID` ASC),
  CONSTRAINT `fk_group_users_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_users_user_groups`
    FOREIGN KEY (`GROUP_ID`)
    REFERENCES `USER_GROUP` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `wineaccess`.`UserEmailLog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_EMAIL_LOG`;
CREATE TABLE IF NOT EXISTS `USER_EMAIL_LOG` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`USER_ID` 							BIGINT(20) UNSIGNED NOT NULL,	
	`SUBJECT` 							VARCHAR(45) NULL,
	`CONTENT` 							VARCHAR(45) NULL,
	`DELIVERY_STATUS` 					VARCHAR(45) NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`),
  INDEX `fk_user_email_logs_users` (`USER_ID` ASC),
  CONSTRAINT `fk_user_email_logs_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wineaccess`.`UserCreditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_CREDIT_CARD`;
CREATE TABLE IF NOT EXISTS `USER_CREDIT_CARD` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`USER_ID` 							BIGINT(20) UNSIGNED NOT NULL,
	`CREDIT_CARDNO` 					INT(11) NULL,
	`CREDIT_CARD_TYPE` 					VARCHAR(45) NULL,
	`EXPIRATION_DATE` 					DATE NULL,
	`PG_PROFILE_ID` 					VARCHAR(45) NULL,
	`USER_ADDRESS_ID` 					BIGINT(20) UNSIGNED NOT NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,  
  PRIMARY KEY (`ID`),
  INDEX `fk_user_credit_cards_users` (`USER_ID` ASC),
  INDEX `fk_UserCreditCard_UserAddress1_idx` (`USER_ADDRESS_ID` ASC),
  CONSTRAINT `fk_user_credit_cards_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserCreditCard_UserAddress1`
    FOREIGN KEY (`USER_ADDRESS_ID`)
    REFERENCES `USER_ADDRESS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wineaccess`.`UserComment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_COMMENT`;
CREATE TABLE IF NOT EXISTS `USER_COMMENT` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`USER_ID` 							BIGINT(20) UNSIGNED NOT NULL,
	`COMMENT` 							TEXT NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`),
  INDEX `fk_user_comments_users` (`USER_ID` ASC),
  CONSTRAINT `fk_user_comments_users`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `wineaccess`.`UserSSO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_SSO`;
CREATE TABLE IF NOT EXISTS `USER_SSO` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`USER_ID` 							BIGINT(20) UNSIGNED NOT NULL,
	`SSO_SOURCE` 						VARCHAR(45) NULL,
	`SSO_TOKEN` 						VARCHAR(45) NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`Id`),
  INDEX `fk_UserSSO_User1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_UserSSO_User`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wineaccess`.`NewsLatter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NEWS_LETTER`;
CREATE TABLE IF NOT EXISTS `NEWS_LETTER` (
	`ID` 								BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`NAME` 								VARCHAR(45) NULL,
	`WEB_NAME` 							VARCHAR(45) NULL,
	`EFF_DATE` 							VARCHAR(45) NULL,
	`END_DATE` 							VARCHAR(45) NULL,
	`EMAIL_SUBJECT` 					VARCHAR(45) NULL,
	`FROM_EMAIL` 						VARCHAR(45) NULL,	
	`SUBMIT_DATE` 						VARCHAR(45) NULL,
	`TITLE` 							VARCHAR(45) NULL,
	`CREATED_DATE` 						DATETIME NOT NULL,
	`CREATED_BY` 						VARCHAR(100) NOT NULL ,
	`UPDATED_DATE` 						DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` 						VARCHAR(100) DEFAULT NULL ,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wineaccess`.`UserEmailPreferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USER_EMAIL_PREFERENCES`;
CREATE TABLE IF NOT EXISTS `USER_EMAIL_PREFERENCES` (
	`ID` 						BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
	`USER_ID` 					BIGINT(20) UNSIGNED NOT NULL,
	`NEWS_LETTER_ID` 			BIGINT(20) UNSIGNED NOT NULL,
	`NITIFICATION_FREQUENCY` 	INT(11) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_UserEmailPreferences_NewsLatter1_idx` (`NEWS_LETTER_ID` ASC),
  INDEX `fk_UserEmailPreferences_User1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_UserEmailPreferences_NewsLatter1`
    FOREIGN KEY (`NEWS_LETTER_ID`)
    REFERENCES `NEWS_LETTER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserEmailPreferences_User1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

