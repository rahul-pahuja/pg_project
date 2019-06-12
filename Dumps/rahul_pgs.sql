-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: rahul
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pgs`
--

DROP TABLE IF EXISTS `pgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pgs` (
  `picdes` varchar(100) NOT NULL,
  `pgname` varchar(30) NOT NULL,
  `mob` varchar(10) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `offaddress` varchar(45) DEFAULT NULL,
  `pname` varchar(45) NOT NULL,
  `pmob` varchar(10) NOT NULL,
  `pemail` varchar(45) DEFAULT NULL,
  `rtype` varchar(5) NOT NULL,
  `rno` varchar(5) NOT NULL,
  `food` varchar(5) NOT NULL,
  `doj` date NOT NULL,
  PRIMARY KEY (`pgname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pgs`
--

LOCK TABLES `pgs` WRITE;
/*!40000 ALTER TABLE `pgs` DISABLE KEYS */;
INSERT INTO `pgs` VALUES ('bin/application/user.png','Aakash','8284837737','','Fazilka','','Kamboj','7888431864','','NAC','14','Yes','2017-07-10'),('bin/application/user.png','Honey','7854126987','','Bathinda','','Suresh','9823467921','','AC','1','No','2017-07-16'),('bin/application/user.png','Ishant','8843715796','','Bathinda','','Ramesh','8847675149','','AC','17','No','2017-07-25'),('bin/application/user.png','Naveen','8284055977','','Mansa','','Bhushan','8840345824','','NAC','18','No','2017-07-10'),('bin/application/user.png','Parshav','9653845632','','Mansa','','Rajkumar','9784543856','','AC','10','Yes','2017-07-09'),('bin/application/user.png','Raghav','9464125276','','Bathinda','','Vinod Kumar','9417254328','','AC','14','No','2017-07-10'),('bin/application/user.png','Rahul','8198099365','','Mansa','','Rakesh','9417117792','','AC','11','Yes','2017-07-09'),('bin/application/user.png','Sahil','7516489615','','Mansa','','Rakesh','7586324598','','AC','16','Yes','2017-07-09'),('bin/application/user.png','Samyak','7569845632','','Mansa','','Ashwini','7564891523','','NAC','1','Yes','2017-07-16');
/*!40000 ALTER TABLE `pgs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-06 12:15:50
