/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.2.0 : Database - test_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `test_db`;

/*Table structure for table `result` */

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result` (
  `studentId` int NOT NULL,
  `componentId` int NOT NULL,
  `date` date NOT NULL,
  `result` float DEFAULT NULL,
  PRIMARY KEY (`studentId`,`componentId`,`date`),
  KEY `resultComponentId` (`componentId`),
  CONSTRAINT `resultComponentId` FOREIGN KEY (`componentId`) REFERENCES `study_component` (`id`),
  CONSTRAINT `resultStudentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `result` */

insert  into `result`(`studentId`,`componentId`,`date`,`result`) values 
(6,2,'2023-12-01',7.45),
(6,4,'2023-12-15',6.8),
(8,2,'2023-12-05',9.5);

/*Table structure for table `semester` */

DROP TABLE IF EXISTS `semester`;

CREATE TABLE `semester` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `semester` */

insert  into `semester`(`id`,`name`) values 
(2,'sem1'),
(3,'sem2');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(64) DEFAULT NULL,
  `lastName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`id`,`firstName`,`lastName`) values 
(6,'Lukas','Potters'),
(7,'Test','Student'),
(8,'Some','Person');

/*Table structure for table `student_semester` */

DROP TABLE IF EXISTS `student_semester`;

CREATE TABLE `student_semester` (
  `studentId` int NOT NULL,
  `semesterId` int NOT NULL,
  PRIMARY KEY (`studentId`,`semesterId`),
  KEY `semesterId` (`semesterId`),
  CONSTRAINT `semesterId` FOREIGN KEY (`semesterId`) REFERENCES `semester` (`id`) ON DELETE CASCADE,
  CONSTRAINT `studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student_semester` */

/*Table structure for table `study_component` */

DROP TABLE IF EXISTS `study_component`;

CREATE TABLE `study_component` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `semesterId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `componentSemesterId` (`semesterId`),
  CONSTRAINT `componentSemesterId` FOREIGN KEY (`semesterId`) REFERENCES `semester` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `study_component` */

insert  into `study_component`(`id`,`name`,`semesterId`) values 
(2,'component1',2),
(3,'component2',2),
(4,'component3',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
