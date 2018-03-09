-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: HPCHARACTERS
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `Gryffindors`
--

DROP TABLE IF EXISTS `Gryffindors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Gryffindors` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Gryffindors`
--

LOCK TABLES `Gryffindors` WRITE;
/*!40000 ALTER TABLE `Gryffindors` DISABLE KEYS */;
INSERT INTO `Gryffindors` VALUES (1000),(1001),(1002),(1008),(1009),(1010),(1011),(1012),(1013),(1014),(1015),(1016),(1028),(1030),(1031),(1032),(1035),(1036);
/*!40000 ALTER TABLE `Gryffindors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hufflepuffs`
--

DROP TABLE IF EXISTS `Hufflepuffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hufflepuffs` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hufflepuffs`
--

LOCK TABLES `Hufflepuffs` WRITE;
/*!40000 ALTER TABLE `Hufflepuffs` DISABLE KEYS */;
INSERT INTO `Hufflepuffs` VALUES (1017),(1018),(1019),(1020),(1021);
/*!40000 ALTER TABLE `Hufflepuffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuidditchPlayers`
--

DROP TABLE IF EXISTS `QuidditchPlayers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuidditchPlayers` (
  `id` int(11) NOT NULL,
  `last` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuidditchPlayers`
--

LOCK TABLES `QuidditchPlayers` WRITE;
/*!40000 ALTER TABLE `QuidditchPlayers` DISABLE KEYS */;
INSERT INTO `QuidditchPlayers` VALUES (1000,'Potter','Seeker'),(1001,'Weasley','Keeper'),(1003,'Chang','Seeker'),(1007,'Davies','Chaser'),(1008,'Weasley','Chaser'),(1010,'Bell','Chaser'),(1011,'Spinnet','Chaser'),(1012,'Johnson','Chaser'),(1013,'Wood','Keeper'),(1014,'Weasley','Beater'),(1015,'Weasley','Beater'),(1020,'Diggory','Seeker'),(1021,'Smith','Chaser'),(1022,'Malfoy','Seeker'),(1027,'Flint','Chaser');
/*!40000 ALTER TABLE `QuidditchPlayers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ravenclaws`
--

DROP TABLE IF EXISTS `Ravenclaws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ravenclaws` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ravenclaws`
--

LOCK TABLES `Ravenclaws` WRITE;
/*!40000 ALTER TABLE `Ravenclaws` DISABLE KEYS */;
INSERT INTO `Ravenclaws` VALUES (1003),(1004),(1005),(1006),(1007),(1029),(1037);
/*!40000 ALTER TABLE `Ravenclaws` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Slytherins`
--

DROP TABLE IF EXISTS `Slytherins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Slytherins` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Slytherins`
--

LOCK TABLES `Slytherins` WRITE;
/*!40000 ALTER TABLE `Slytherins` DISABLE KEYS */;
INSERT INTO `Slytherins` VALUES (1022),(1023),(1024),(1025),(1026),(1027),(1033),(1034),(1038),(1039);
/*!40000 ALTER TABLE `Slytherins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Students`
--

DROP TABLE IF EXISTS `Students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Students` (
  `id` int(11) NOT NULL,
  `last` varchar(255) DEFAULT NULL,
  `first` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `house` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Students`
--

LOCK TABLES `Students` WRITE;
/*!40000 ALTER TABLE `Students` DISABLE KEYS */;
INSERT INTO `Students` VALUES (1000,'Potter','Harry','wizard','Gryffindor'),(1001,'Weasley','Ron','wizard','Gryffindor'),(1002,'Granger','Hermione','witch','Gryffindor'),(1003,'Chang','Cho','witch','Ravenclaw'),(1004,'Lovegood','Luna','witch','Ravenclaw'),(1005,'Boot','Terry','wizard','Ravenclaw'),(1006,'Corner','Michael','wizard','Ravenclaw'),(1007,'Davies','Roger','wizard','Ravenclaw'),(1008,'Weasley','Ginny','witch','Gryffindor'),(1009,'Longbottom','Neville','wizard','Gryffindor'),(1010,'Bell','Katie','witch','Gryffindor'),(1011,'Spinnet','Alicia','witch','Gryffindor'),(1012,'Johnson','Angelina','witch','Gryffindor'),(1013,'Wood','Oliver','wizard','Gryffindor'),(1014,'Weasley','Fred','wizard','Gryffindor'),(1015,'Weasley','George','wizard','Gryffindor'),(1016,'Weasley','Percy','wizard','Gryffindor'),(1017,'Macmillan','Ernie','wizard','Hufflepuff'),(1018,'Abbott','Hannah','witch','Hufflepuff'),(1019,'Bones','Susan','witch','Hufflepuff'),(1020,'Diggory','Cedric','wizard','Hufflepuff'),(1021,'Smith','Zacharias','wizard','Hufflepuff'),(1022,'Malfoy','Draco','wizard','Slytherin'),(1023,'Parkinson','Pansy','witch','Slytherin'),(1024,'Greengrass','Astoria','witch','Slytherin'),(1025,'Nott','Theodore','wizard','Slytherin'),(1026,'Zabini','Blaise','wizard','Slytherin'),(1027,'Flint','Marcus','wizard','Slytherin'),(1028,'Brown','Lavendar','witch','Gryffindor'),(1029,'Patil','Padma','witch','Ravenclaw'),(1030,'Patil','Parvati','witch','Gryffindor'),(1031,'Thomas','Dean','wizard','Gryffindor'),(1032,'Finnigan','Seamus','wizard','Gryffindor'),(1033,'Greengrass','Daphne','witch','Slytherin'),(1034,'Bulstrode','Millicent','witch','Slytherin'),(1035,'Creevey','Colin','wizard','Gryffindor'),(1036,'Creevey','Dennis','wizard','Gryffindor'),(1037,'Clearwater','Penelope','witch','Ravenclaw'),(1038,'Crabbe','Vincent','wizard','Slytherin'),(1039,'Goyle','Greg','wizard','Slytherin');
/*!40000 ALTER TABLE `Students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-08 22:32:17
