create database if not exists wallethub;

CREATE TABLE IF NOT EXISTS wallethub.loginfo (
  id INT NOT NULL  AUTO_INCREMENT,
  `ip` VARCHAR(15) NULL,
  `date` DATETIME(3) NOT NULL,
  PRIMARY KEY(`id`),
  CONSTRAINT  UNIQUE (`ip`,`date`)
);