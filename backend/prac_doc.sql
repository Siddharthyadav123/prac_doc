/*
SQLyog Community v11.01 (32 bit)
MySQL - 5.5.24-log : Database - prac_doc_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prac_doc_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `prac_doc_db`;

/*Table structure for table `dr_specialization` */

DROP TABLE IF EXISTS `dr_specialization`;

CREATE TABLE `dr_specialization` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `dr_specialization` */

insert  into `dr_specialization`(`id`,`name`) values (1,'Dentist'),(2,'Gynecologist/Obstetrician'),(3,'Dermatologiest'),(4,'Homeopath'),(5,'Ayurveda'),(6,'Cardiologist'),(7,'Gastroenterologist'),(8,'Neurologist'),(9,'Ear-Nose-Throat(ENT)Specialist'),(10,'Psychistrist'),(11,'General Physician'),(12,'Physiotherapist'),(13,'Pediatrician'),(14,'Urologist'),(15,'Orthopedist'),(16,'Ophthalmologist'),(17,'Dietitian/Nutritionist');

/*Table structure for table `dr_table` */

DROP TABLE IF EXISTS `dr_table`;

CREATE TABLE `dr_table` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `dr_uname` varchar(255) NOT NULL,
  `dr_pwd` varchar(255) NOT NULL,
  `dr_full_name` varchar(255) NOT NULL,
  `dr_specialization` int(1) NOT NULL,
  `dr_qualification` varchar(255) NOT NULL,
  `dr_experience` int(2) NOT NULL,
  `dr_consulation_fee` int(4) NOT NULL,
  `dr_contact_num` varchar(10) NOT NULL,
  `dr_clinic_name` varchar(255) NOT NULL,
  `dr_clinic_address` varchar(255) NOT NULL,
  `dr_clinic_rating` float NOT NULL DEFAULT '1',
  `dr_verified_via` varchar(255) DEFAULT NULL,
  `dr_services` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `dr_table` */

insert  into `dr_table`(`id`,`dr_uname`,`dr_pwd`,`dr_full_name`,`dr_specialization`,`dr_qualification`,`dr_experience`,`dr_consulation_fee`,`dr_contact_num`,`dr_clinic_name`,`dr_clinic_address`,`dr_clinic_rating`,`dr_verified_via`,`dr_services`) values (1,'niknilwar','123456','Dr. Nikhalesh Nilawar',1,'BDS, Fellowship in Implantology',17,200,'0959506346','Nilawar Multispeciality dental clinic','1st floor, Sadoday Arcade, WHC Road. Landmark:Opposite Bank of Baroda',4.5,'Medical License','Cosmetic Veneers, Dental Implant Fixing, Cosmetic/Aesthetic Dentistry');

/*Table structure for table `user_table` */

DROP TABLE IF EXISTS `user_table`;

CREATE TABLE `user_table` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `mobile_no` varchar(255) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `user_table` */

insert  into `user_table`(`id`,`uname`,`pwd`,`address`,`full_name`,`mobile_no`,`created_on`,`updated_on`) values (1,'siddharth','12345678','ballarpur','siddharth suresh yadav','9595063464','2016-10-21 16:00:59','2016-10-21 16:00:59'),(10,'ram','1111','ballarpur','ram kumar yadav','9595868633','2016-11-02 15:39:33','2016-11-02 15:58:15');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
