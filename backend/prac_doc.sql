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

/*Table structure for table `appoinment_status_constants` */

DROP TABLE IF EXISTS `appoinment_status_constants`;

CREATE TABLE `appoinment_status_constants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `appoinment_status_constants` */

insert  into `appoinment_status_constants`(`id`,`status`) values (1,'PENDING'),(2,'APPROVED'),(3,'PROCESSED'),(4,'CANCELLED');

/*Table structure for table `dr_aleart_table` */

DROP TABLE IF EXISTS `dr_aleart_table`;

CREATE TABLE `dr_aleart_table` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `appointment_status_id` int(11) NOT NULL COMMENT '1=pending, 2=approved,3=cancelled',
  `message` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0= unread, 1=read',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dr_aleart_table` */

/*Table structure for table `dr_appointment_table` */

DROP TABLE IF EXISTS `dr_appointment_table`;

CREATE TABLE `dr_appointment_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `dr_id` int(11) NOT NULL,
  `dr_name` varchar(255) NOT NULL,
  `date_time` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `dr_appointment_table` */

insert  into `dr_appointment_table`(`id`,`patient_id`,`patient_name`,`dr_id`,`dr_name`,`date_time`,`status`) values (14,11,'akshay yadav',1,'Dr. Nikhalesh Nilawar','2017-02-03 10:30:00',2),(15,11,'akshay yadav',1,'Dr. Nikhalesh Nilawar','2017-02-03 14:30:00',1),(16,11,'akshay yadav',1,'Dr. Nikhalesh Nilawar','2017-02-03 09:00:00',1),(17,11,'akshay yadav',1,'Dr. Nikhalesh Nilawar','2017-02-03 20:30:00',1),(18,11,'akshay yadav',1,'Dr. Nikhalesh Nilawar','2017-03-25 10:30:00',1);

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
  `dr_specialization_id` int(11) NOT NULL,
  `dr_qualification` varchar(255) NOT NULL,
  `dr_experience` int(11) NOT NULL,
  `dr_consulation_fee` int(11) NOT NULL,
  `dr_contact_num` varchar(10) NOT NULL,
  `dr_profile_pic_url` varchar(255) DEFAULT NULL,
  `dr_clinic_name` varchar(255) NOT NULL,
  `dr_clinic_address` varchar(255) NOT NULL,
  `dr_clinic_pics_url` varchar(255) DEFAULT NULL,
  `dr_clinic_lat` varchar(255) NOT NULL,
  `dr_clinic_long` varchar(255) NOT NULL,
  `dr_clinic_rating` float NOT NULL DEFAULT '1',
  `dr_verified_via` varchar(255) DEFAULT NULL,
  `dr_services` varchar(255) DEFAULT NULL,
  `dr_morning_time_slot_id` int(11) DEFAULT '-1',
  `dr_afternoon_time_slot_id` int(11) DEFAULT '-1',
  `dr_evening_time_slot_id` int(11) DEFAULT '-1',
  `dr_night_time_slot_id` int(11) DEFAULT '-1',
  `dr_working_day` varchar(255) NOT NULL DEFAULT 'ALL',
  `dr_specialization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `dr_table` */

insert  into `dr_table`(`id`,`dr_uname`,`dr_pwd`,`dr_full_name`,`dr_specialization_id`,`dr_qualification`,`dr_experience`,`dr_consulation_fee`,`dr_contact_num`,`dr_profile_pic_url`,`dr_clinic_name`,`dr_clinic_address`,`dr_clinic_pics_url`,`dr_clinic_lat`,`dr_clinic_long`,`dr_clinic_rating`,`dr_verified_via`,`dr_services`,`dr_morning_time_slot_id`,`dr_afternoon_time_slot_id`,`dr_evening_time_slot_id`,`dr_night_time_slot_id`,`dr_working_day`,`dr_specialization`) values (1,'niknilwar','123456','Dr. Nikhalesh Nilawar',1,'BDS, Fellowship in Implantology',17,200,'0959506346',NULL,'Nilawar Multispeciality dental clinic','1st floor, Sadoday Arcade, WHC Road. Landmark:Opposite Bank of Baroda',NULL,'','',4.5,'Medical License','Cosmetic Veneers, Dental Implant Fixing, Cosmetic/Aesthetic Dentistry',1,4,5,10,'M,T,W,T,F',NULL);

/*Table structure for table `dr_time_slots` */

DROP TABLE IF EXISTS `dr_time_slots`;

CREATE TABLE `dr_time_slots` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `slot` varchar(255) NOT NULL,
  `timing` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `dr_time_slots` */

insert  into `dr_time_slots`(`id`,`slot`,`timing`) values (1,'morning','08:00 AM,08:30 AM,09:00 AM,09:30 AM,10:00 AM,10:30 AM,11:00 AM,11:30 AM'),(2,'morning','09:00 AM,09:30 AM,10:00 AM,10:30 AM,11:00 AM,11:30 AM'),(3,'morning','10:00 AM,10:30 AM,11:00 AM,11:30 AM'),(4,'afternoon','12:00 PM,12:30 PM,01:00 PM,01:30 PM,02:00 PM,02:30 PM,03:00 PM,03:30 PM'),(5,'evening','04:00 PM,04:30 PM,05:00 PM,05:30 PM,06:00 PM,06:30 PM,07:00 PM,07:30 PM'),(6,'night','08:00 PM,08:30 PM,09:00 PM,09:30 PM,10:00 PM,10:30 PM'),(7,'night','08:00 PM,08:30 PM,09:00 PM,09:30 PM,10:00 PM'),(8,'night','08:00 PM,08:30 PM,09:00 PM,09:30 PM'),(9,'night','08:00 PM,08:30 PM,09:00 PM'),(10,'night','08:00 PM,08:30 PM');

/*Table structure for table `user_aleart_table` */

DROP TABLE IF EXISTS `user_aleart_table`;

CREATE TABLE `user_aleart_table` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `appointment_status_id` int(11) NOT NULL COMMENT '1=pending, 2=approved,3=cancelled',
  `message` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0= unread, 1=read',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_aleart_table` */

/*Table structure for table `user_table` */

DROP TABLE IF EXISTS `user_table`;

CREATE TABLE `user_table` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `dob` date DEFAULT NULL,
  `mobile_no` varchar(255) NOT NULL,
  `profile_pic_url` varchar(255) DEFAULT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `user_table` */

insert  into `user_table`(`id`,`uname`,`pwd`,`address`,`full_name`,`gender`,`dob`,`mobile_no`,`profile_pic_url`,`created_on`,`updated_on`) values (1,'siddharth','12345678','ballarpur','siddharth suresh yadav','M','1990-12-30','9595063464',NULL,'2016-10-21 16:00:59','2016-10-21 16:00:59'),(10,'ram','1111','ballarpur','ram kumar yadav','M','2016-11-03','9595868633',NULL,'2016-11-02 15:39:33','2016-11-02 15:58:15'),(11,'akshay','1111','ballarpur','akshay yadav','M','1990-12-30','9595063464',NULL,'2016-11-03 16:02:01','2016-11-03 16:02:01'),(12,'tom','12345','tytyt','tom jerry','F','1990-12-30','9595068989',NULL,'2016-12-08 14:10:27','2016-12-08 14:10:27'),(13,'nik','444444','ghhjiyvv hh','nik ram','F','2016-12-08','959506366',NULL,'2016-12-08 14:27:54','2016-12-08 14:27:54'),(14,'shital','ssssssss','ttt','shital mahajan','M','2016-12-08','9595063422',NULL,'2016-12-08 14:40:41','2016-12-08 14:40:41');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
