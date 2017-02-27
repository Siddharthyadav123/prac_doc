-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 04, 2017 at 10:03 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prac_doc_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `appoinment_status_constants`
--

CREATE TABLE `appoinment_status_constants` (
  `id` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appoinment_status_constants`
--

INSERT INTO `appoinment_status_constants` (`id`, `status`) VALUES
(1, 'PENDING'),
(2, 'APPROVED'),
(3, 'PROCESSED'),
(4, 'CANCELLED');

-- --------------------------------------------------------

--
-- Table structure for table `dr_aleart_table`
--

CREATE TABLE `dr_aleart_table` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `appointment_status_id` int(11) NOT NULL COMMENT '1=pending, 2=approved,3=cancelled',
  `message` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0= unread, 1=read'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dr_appointment_table`
--

CREATE TABLE `dr_appointment_table` (
  `id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `dr_id` int(11) NOT NULL,
  `dr_name` varchar(255) NOT NULL,
  `date_time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dr_appointment_table`
--

INSERT INTO `dr_appointment_table` (`id`, `patient_id`, `patient_name`, `dr_id`, `dr_name`, `date_time`, `status`) VALUES
(21, 11, 'akshay yadav', 21, 'Dr. Manoj Waghmare', '2017-02-04 00:00:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `dr_specialization`
--

CREATE TABLE `dr_specialization` (
  `id` int(2) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dr_specialization`
--

INSERT INTO `dr_specialization` (`id`, `name`) VALUES
(1, 'Dentist'),
(2, 'Gynecologist/Obstetrician'),
(3, 'Dermatologiest'),
(4, 'Homeopath'),
(5, 'Ayurveda'),
(6, 'Cardiologist'),
(7, 'Gastroenterologist'),
(8, 'Neurologist'),
(9, 'Ear-Nose-Throat(ENT)Specialist'),
(10, 'Psychistrist'),
(11, 'General Physician'),
(12, 'Physiotherapist'),
(13, 'Pediatrician'),
(14, 'Urologist'),
(15, 'Orthopedist'),
(16, 'Ophthalmologist'),
(17, 'Dietitian/Nutritionist');

-- --------------------------------------------------------

--
-- Table structure for table `dr_table`
--

CREATE TABLE `dr_table` (
  `id` int(255) NOT NULL,
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
  `dr_specialization` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dr_table`
--

INSERT INTO `dr_table` (`id`, `dr_uname`, `dr_pwd`, `dr_full_name`, `dr_specialization_id`, `dr_qualification`, `dr_experience`, `dr_consulation_fee`, `dr_contact_num`, `dr_profile_pic_url`, `dr_clinic_name`, `dr_clinic_address`, `dr_clinic_pics_url`, `dr_clinic_lat`, `dr_clinic_long`, `dr_clinic_rating`, `dr_verified_via`, `dr_services`, `dr_morning_time_slot_id`, `dr_afternoon_time_slot_id`, `dr_evening_time_slot_id`, `dr_night_time_slot_id`, `dr_working_day`, `dr_specialization`) VALUES
(1, 'niknilwar', '123456', 'Dr. Nikhalesh Nilawar', 1, 'BDS, Fellowship in Implantology', 17, 200, '0959506346', NULL, 'Nilawar Multispeciality dental clinic', '1st floor, Sadoday Arcade, WHC Road. Landmark:Opposite Bank of Baroda', NULL, '', '', 4.5, 'Medical License', 'Cosmetic Veneers, Dental Implant Fixing, Cosmetic/Aesthetic Dentistry', 1, 4, 5, 10, 'M,T,W,T,F', NULL),
(2, 'Rashmi', '123456', 'Dr. Rashmi Gupta', 1, 'BDS,Certified course of AIC Implants', 22, 200, '9449459595', NULL, 'Dr. Rashmi''s Dental Clinic', '1st Floor,Samar Chambers,WHC Road', NULL, '21.139274', '79.060253', 4, 'Medical License', 'Artificial Teeth,Acrylic Partial Denture,Impaction/Impacted Tooth Extraction', 1, 4, 5, 10, 'M,T,W,T,F,S', NULL),
(3, 'Joglekar', '123456', 'Dr. Ketaki Joglekar', 1, 'BDS,MDS-Oral & Maxillofacial Surgery', 9, 300, '9541578624', NULL, 'SmileKraft Maxillofacial Surgery', '66/1,Ashish Apartments,2nd Floor,Abhyankar Road', NULL, '21.136262', '79.082490', 3.5, 'Medical License', 'Oral Cancer Treatment,Oral Surgery,Surgical Tooth Extraction', 1, 4, 5, 9, 'M,T,W,T,F,S', NULL),
(4, 'Magarkar', '123456', 'Dr. Shashwat Magarkar', 1, 'BDS,MDS-Oral & Maxillofacial Surgery  ', 10, 300, '9415423486', NULL, 'SmileKraft Maxillofacial Surgery And Dental Hospital', '66/1,Ashish Apartment,2nd Floor,Abhyankar Road', NULL, '21.136050', '79.082286', 4.5, 'Medical License', 'Oral Surgery,Oral Cancer Treatment,Surgical Tooth Extraction', -1, -1, 5, 9, 'M,T,,W,T,F,S', NULL),
(5, 'Garg', '123456', 'Dr. Ketan Garg', 1, 'BDS,MDS-Oral & Maxillofacial Surgery', 12, 200, '7449465564', NULL, 'Garg''s Dental Speciality Centre', 'Doctors Enclave,Residency Road,Sadar Bazar', NULL, '21.160178', '79.079104', 4, 'Medical License', 'Oral Surgery,Oral & Maxillofacial Surgery,jaw Reshaping', 3, 4, 6, 9, 'M,T,W,T,F,S', NULL),
(6, 'Chandak', '123456', 'Dr. Mukesh Chandak', 1, 'Fellowship and Diplomate in Implantology.', 19, 200, '8941254411', NULL, 'Chandak Dental Clinic ', 'Pushpak Vihar,1st floor,opposite Hotel,Darodkar square,Gandhi putla,Central Avenue road', NULL, '21.149440', '79.111703', 3.5, 'Medical License', 'Impaction,Gum Disease Treatment,Complete Dentures Fixing', 3, 4, 5, 6, 'M,,T,W,T,F,S', NULL),
(7, 'Arya', '123456\r\n', 'Dr. Harshwardhan Arya', 1, 'BDS,MDS-Prosthodontics', 18, 500, '9515786354', NULL, 'Dr Arya''s Dental Clinic', '186,Ground Floor,Indu Yash ', NULL, '21.139890', '79.055814', 4.5, 'Medical License', 'Cosmetic Dentistry,Flap Surgery,Dental Implant Fixing', 1, 4, 5, 9, 'M,T,W,T,F,S', NULL),
(8, 'Magrawal', '123456', 'Meghna Agrawal', 2, 'MBBS,MD-Obstetrics & Gynaecology', 12, 300, '9672384567', NULL, 'Disha Clinics', '1st Floor,Sharhari Complex,Ramdaspeth', NULL, '21.133754', '79.074595', 4, 'Medical License', 'Dysmenorrhea Treatment,Gynae Problems,Maternal Care', 3, 4, -1, -1, 'M,T,,W,T,F,S\r\n\r\n', NULL),
(9, 'Sdhande', '123456', 'Dr. Sunita Dhande', 2, 'MBBS,MD-Obstetrics & Gynaecology', 32, 700, '8452367598', NULL, 'Mother Care Nursing Home', '5th Floor,Midas Heights,Central Bazar Road', NULL, '21.133704', '79.075052', 4.5, 'Medical License', 'Infertility Evaluation,Assisted Reproduction,MTP and Family Planning centre', 2, 4, -1, -1, 'M,T,W,T,F,S', NULL),
(10, 'vagrawal', '123456', 'Dr.Vinita Agrawal ', 2, 'MBBS,DGO,DNB-Obstetrics & Gynecology', 13, 300, '9856743589', NULL, 'Sanvi Clinic', 'Ground Floor,Siddhivinayak Complex,Khare town,Dharampeth', NULL, '21.142836', '79.060589', 3.5, 'Medical License', 'Amnicentesis,Cordocentesis,Adiana System', -1, -1, 5, 9, 'M,T,W,T,F,S', NULL),
(11, 'Sbhave', '123456', 'Dr. Swati Bhave', 2, 'MBBS,MD-Obstetrics & Gynaecology', 24, 200, '9963665874', NULL, 'Dr. Swati Bhave''s Clinic', 'Himalaya Harmony,WHC Road,Laxmi Nagar ', NULL, '21.120669', '79.064511', 4, 'Medical License', 'High Risk Preganancy Care,Dysmenorrhea Treatment', -1, -1, -1, 6, 'M,T,W,T,F,S', NULL),
(12, 'ASingh', '123456', 'Dr. Anita Singh', 2, 'MBBS,MD-Obstetrics & Gynaecology', 31, 500, '9988775647', NULL, 'Avantika Hospital', '5th Floor,34/2 Aashirwad Complex,Central Bazar', NULL, '21.134031', '79.074312', 3, 'Medical License', 'Gynae Problems,Obstetrics Problems,Maternal Care', 3, 4, -1, -1, 'M,T,W,T,F', NULL),
(13, 'Smalpani', '123456', 'Dr.Swati Malpani', 2, 'MBBS,DGO,Certificate in Laproscopy', 16, 300, '8894626487', NULL, 'Geeta Prem Nursing Home', 'Ganga Vihar,Shrikrishna Nagar Square,Middle Ring Road', NULL, '21.135111', '79.1311166', 3.5, 'Medical License', 'family planning and full contraceptive services\r\n', 1, 4, 7, 9, 'M,T,W,T,F,S', NULL),
(14, 'Apatil', '123456', 'Dr. Arti Patil', 2, 'MBBS,DGO,Fellowship in Minimal Access Surgery', 12, 400, '8967888978', NULL, 'SMP Hospital', '2nd Floor,Yugdharma Complex', NULL, '21.134690', '79.075706', 4, 'Medical License', 'Gynae Problems,IUD placement,Pre and Post Delivery Care', 3, 4, -1, -1, 'M,T,w,T,F,S\r\n', NULL),
(15, 'Lkalmegh', '123456', 'Dr. Leena Bire Kalmegh', 2, 'MD-Obstetrics & Gynaecology,MBBS ', 16, 500, '9665587548', NULL, 'Gananam Hospital And Research Institute', 'Ashirwad Commercial Complex,Farmland,Central Bazar Road,New Ramadaspeth', NULL, '21.133967', '79.074260', 4, 'Medical License', 'High-Risk Pregnancy Care,Colposcopy,Intra-Uterine Insemination,Gynae Problems', 3, 4, -1, -1, 'M,T,W,T,F,S', NULL),
(16, 'Dkiratkar', '123456', 'Dr. Deepti Kiratkar', 2, 'MBBS,DGO,CIMP', 16, 300, '8996748265', NULL, 'Anmol Maternity And Nursing Home', '301,Everest Arcade,Sakkardara Square,Umred Road', NULL, '21.127887', '79.114343', 4, 'Medical License', 'Casearean Section,Hsterectomy,Dilatation and curettage,Child Birth Education', 3, 4, -1, -1, 'M,T,W,T,F,S', NULL),
(17, 'Kshahane', '123456', 'Dr. Kunda Shahane', 2, 'MBBS,MS-Obstetrics & Gynaecology,FIFM,FMF', 13, 500, '8966335478', NULL, 'MayFlower Clinic', 'Crystal Plaza,2nd floor,Central Bazar Road,New Ramdaspeth', NULL, '21.132872', '79.070567', 4, 'Medical License', '11 to 14 wks aneuploidy,CVS,Doppler,Amniocentesis', 3, 4, 5, -1, 'M,T,W,T,F,S', NULL),
(18, 'Stiwari', '123456', 'Dr.S.Tiwari', 2, 'MBBS,DGO,Diploma in Minimal Access Suregery', 5, 400, '9447856325', NULL, 'Health Multispeciallity Hospital', 'Mansi Celebration Hall,Rajeev Nagar,Opp Hanuman Mandir,Hingna Road', NULL, '20.919092', '78.963990', 3.5, 'Medical License', NULL, 1, 4, -1, -1, 'M,,T,W,T,F,S', NULL),
(19, 'Akalbande', '123456', 'Aarti Kalbande', 2, 'MD-Obstretrics & Gynaecology', 25, 250, '8996351472', NULL, 'Dr. Aarti Kalbande Clinic ', 'Sahayog Nagar', NULL, '21.181953', '79.065561', 4.5, 'Medical License', 'Amniocentesis,In vitro fertilization,central ceclage', 3, -1, -1, 9, 'M,T,W,T,F,S', NULL),
(20, 'Nbhargava', '123456', 'Dr.Neha Bhargava', 2, 'MBBS,MS-Obstretics & Gynaecology', 6, 460, '8666542398', NULL, 'Obstetrics and gynaecology Clinic', '2nd Floor,Care Hospital,Panchsheek Square', NULL, '21.138672', '79.078982', 4, 'Medical License', NULL, 3, 4, -1, 9, 'M,T,W,T,F,S', NULL),
(21, 'Mwaghmare', '123456', 'Dr. Manoj Waghmare', 3, 'MBBS,DNB-Dermatology & Venereology', 12, 500, '8995774155', NULL, 'Dermacity Skin Clinic', 'B Wing,Neeti Gaurav Complex,Central Bazar Road', NULL, '21.134865', '79.077009', 4.5, 'Medical License', 'Cosmetic Treatment,Hair Transplant,Dermabrasion', 3, 4, -1, -1, 'M,T,W,T,S', NULL),
(22, 'Ajain', '123456', 'Dr. Anshul Jain', 3, 'MBBS,MD-Dermatology', 11, 300, '9663387951', NULL, 'Pearl Hospital', '4th Floor,Dhanshree Complex', NULL, '21.140608', '79.085562', 4, 'Medical License', 'Dermabrasion,Laser Resurfacing,Acne Treatment', 3, 4, 6, -1, 'M,T,W,T,F,S', NULL),
(23, 'Raamir', '123456', 'Dr.Riyaz Aamir', 3, 'MBBS,Diploma in Dermatology & Vernerology', 22, 300, '9558742255', NULL, 'Skinmax Clinic', 'Acharaj Tower,Upper Ground Floor,Chhindwara Road,Sadar Bazar', NULL, '21.168389', '79.079487', 4, 'Medical License', 'Dermabrasion,Laser Resurfacing,Acne Treatment', 3, -1, -1, -1, 'M,T,W,T,F,S', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dr_time_slots`
--

CREATE TABLE `dr_time_slots` (
  `id` int(10) NOT NULL,
  `slot` varchar(255) NOT NULL,
  `timing` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dr_time_slots`
--

INSERT INTO `dr_time_slots` (`id`, `slot`, `timing`) VALUES
(1, 'morning', '08:00 AM,08:30 AM,09:00 AM,09:30 AM,10:00 AM,10:30 AM,11:00 AM,11:30 AM'),
(2, 'morning', '09:00 AM,09:30 AM,10:00 AM,10:30 AM,11:00 AM,11:30 AM'),
(3, 'morning', '10:00 AM,10:30 AM,11:00 AM,11:30 AM'),
(4, 'afternoon', '12:00 PM,12:30 PM,01:00 PM,01:30 PM,02:00 PM,02:30 PM,03:00 PM,03:30 PM'),
(5, 'evening', '04:00 PM,04:30 PM,05:00 PM,05:30 PM,06:00 PM,06:30 PM,07:00 PM,07:30 PM'),
(6, 'night', '08:00 PM,08:30 PM,09:00 PM,09:30 PM,10:00 PM,10:30 PM'),
(7, 'night', '08:00 PM,08:30 PM,09:00 PM,09:30 PM,10:00 PM'),
(8, 'night', '08:00 PM,08:30 PM,09:00 PM,09:30 PM'),
(9, 'night', '08:00 PM,08:30 PM,09:00 PM'),
(10, 'night', '08:00 PM,08:30 PM');

-- --------------------------------------------------------

--
-- Table structure for table `user_aleart_table`
--

CREATE TABLE `user_aleart_table` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `appointment_status_id` int(11) NOT NULL COMMENT '1=pending, 2=approved,3=cancelled',
  `message` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0= unread, 1=read'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `id` int(10) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `dob` date DEFAULT NULL,
  `mobile_no` varchar(255) NOT NULL,
  `profile_pic_url` varchar(255) DEFAULT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`id`, `uname`, `pwd`, `address`, `full_name`, `gender`, `dob`, `mobile_no`, `profile_pic_url`, `created_on`, `updated_on`) VALUES
(1, 'siddharth', '12345678', 'ballarpur', 'siddharth suresh yadav', 'M', '1990-12-30', '9595063464', NULL, '2016-10-21 16:00:59', '2016-10-21 10:30:59'),
(10, 'ram', '1111', 'ballarpur', 'ram kumar yadav', 'M', '2016-11-03', '9595868633', NULL, '2016-11-02 15:39:33', '2016-11-02 10:28:15'),
(11, 'akshay', '1111', 'ballarpur', 'akshay yadav', 'M', '1990-12-30', '9595063464', NULL, '2016-11-03 16:02:01', '2016-11-03 10:32:01'),
(12, 'tom', '12345', 'tytyt', 'tom jerry', 'F', '1990-12-30', '9595068989', NULL, '2016-12-08 14:10:27', '2016-12-08 08:40:27'),
(13, 'nik', '444444', 'ghhjiyvv hh', 'nik ram', 'F', '2016-12-08', '959506366', NULL, '2016-12-08 14:27:54', '2016-12-08 08:57:54'),
(14, 'shital', 'ssssssss', 'ttt', 'shital mahajan', 'M', '2016-12-08', '9595063422', NULL, '2016-12-08 14:40:41', '2016-12-08 09:10:41');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appoinment_status_constants`
--
ALTER TABLE `appoinment_status_constants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dr_aleart_table`
--
ALTER TABLE `dr_aleart_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dr_appointment_table`
--
ALTER TABLE `dr_appointment_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dr_specialization`
--
ALTER TABLE `dr_specialization`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dr_table`
--
ALTER TABLE `dr_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dr_time_slots`
--
ALTER TABLE `dr_time_slots`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_aleart_table`
--
ALTER TABLE `user_aleart_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uname` (`uname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appoinment_status_constants`
--
ALTER TABLE `appoinment_status_constants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `dr_appointment_table`
--
ALTER TABLE `dr_appointment_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `dr_specialization`
--
ALTER TABLE `dr_specialization`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `dr_table`
--
ALTER TABLE `dr_table`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `dr_time_slots`
--
ALTER TABLE `dr_time_slots`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
