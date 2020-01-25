CREATE DATABASE  IF NOT EXISTS `js_exam`;
USE `js_exam`;

DROP TABLE IF EXISTS `names`;

CREATE TABLE `names` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fname` varchar(255),
  `lname` varchar(255),
  `gender` varchar(255),
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;