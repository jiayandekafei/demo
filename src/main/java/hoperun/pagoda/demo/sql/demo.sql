-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_agent_info`
--

DROP TABLE IF EXISTS `tb_agent_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_agent_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dbName` varchar(45) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `alias` varchar(200) DEFAULT NULL,
  `isSchedule` varchar(45) DEFAULT NULL,
  `scheduleTime` varchar(200) DEFAULT NULL,
  `allCodeNum` int(11) DEFAULT NULL,
  `commentCodeNum` int(11) DEFAULT NULL,
  `blankCodeNum` int(11) DEFAULT NULL,
  `usefulCodeNum` int(11) DEFAULT NULL,
  `callTimes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_agent_info`
--

LOCK TABLES `tb_agent_info` WRITE;
/*!40000 ALTER TABLE `tb_agent_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_agent_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_customer`
--

DROP TABLE IF EXISTS `tb_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_customer` (
  `customerId` int(3) NOT NULL AUTO_INCREMENT COMMENT 'customer id',
  `customername` varchar(100) NOT NULL COMMENT 'customer name ',
  `description` varchar(100) DEFAULT NULL COMMENT 'description',
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='customer info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (15,'testCustomer1','testCustomer1'),(16,'testCustomer2','testCustomer2'),(27,'customer3',NULL);
/*!40000 ALTER TABLE `tb_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_db_info`
--

DROP TABLE IF EXISTS `tb_db_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_db_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dbName` varchar(255) NOT NULL,
  `dbFileName` varchar(255) DEFAULT NULL,
  `formSize` int(11) DEFAULT NULL,
  `usefulFormSize` int(11) DEFAULT NULL,
  `viewSize` int(11) DEFAULT NULL,
  `usefulViewSize` int(11) DEFAULT NULL,
  `allCodeNum` int(11) DEFAULT NULL,
  `usefulCodeNum` int(11) DEFAULT NULL,
  `documentSize` int(11) DEFAULT NULL,
  `accessCount` int(11) DEFAULT NULL,
  `categoryNo` varchar(10) DEFAULT NULL,
  `groupId` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_db_info`
--

LOCK TABLES `tb_db_info` WRITE;
/*!40000 ALTER TABLE `tb_db_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_db_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_db_similarity`
--

DROP TABLE IF EXISTS `tb_db_similarity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_db_similarity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryNo` varchar(3) DEFAULT NULL,
  `enName` varchar(45) DEFAULT NULL,
  `jpName` varchar(200) DEFAULT NULL,
  `groupId` int(11) NOT NULL,
  `categorySize` int(11) DEFAULT NULL,
  `mainForm` varchar(200) DEFAULT NULL,
  `dbSize` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_db_similarity`
--

LOCK TABLES `tb_db_similarity` WRITE;
/*!40000 ALTER TABLE `tb_db_similarity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_db_similarity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_exe_status`
--

DROP TABLE IF EXISTS `tb_exe_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_exe_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  `status` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_exe_status`
--

LOCK TABLES `tb_exe_status` WRITE;
/*!40000 ALTER TABLE `tb_exe_status` DISABLE KEYS */;
INSERT INTO `tb_exe_status` VALUES (1,2,1,'C'),(2,1,1,'C'),(3,2,2,'C'),(4,1,2,'C'),(5,1,13,'C'),(6,2,13,'C');
/*!40000 ALTER TABLE `tb_exe_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_form_info`
--

DROP TABLE IF EXISTS `tb_form_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_form_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dbName` varchar(45) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `alias` varchar(200) DEFAULT NULL,
  `totalField` int(11) DEFAULT NULL,
  `displayField` int(11) DEFAULT NULL,
  `conditionalField` int(11) DEFAULT NULL,
  `hideField` int(11) DEFAULT NULL,
  `allCodeNum` int(11) DEFAULT NULL,
  `commentCodeNum` int(11) DEFAULT NULL,
  `blankCodeNum` int(11) DEFAULT NULL,
  `usefulCodeNum` int(11) DEFAULT NULL,
  `callTimes` int(11) DEFAULT NULL,
  `buttonNum` int(11) DEFAULT NULL,
  `actionNum` int(11) DEFAULT NULL,
  `docNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_form_info`
--

LOCK TABLES `tb_form_info` WRITE;
/*!40000 ALTER TABLE `tb_form_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_form_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_form_similarity`
--

DROP TABLE IF EXISTS `tb_form_similarity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_form_similarity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formName` varchar(200) DEFAULT NULL,
  `fieldNum` int(11) DEFAULT NULL,
  `codeNum` int(11) DEFAULT NULL,
  `dbName` varchar(200) DEFAULT NULL,
  `categorySize` int(11) DEFAULT NULL,
  `categoryNo` int(11) DEFAULT NULL,
  `allCategory` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_form_similarity`
--

LOCK TABLES `tb_form_similarity` WRITE;
/*!40000 ALTER TABLE `tb_form_similarity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_form_similarity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_group`
--

DROP TABLE IF EXISTS `tb_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_group` (
  `groupId` int(3) NOT NULL AUTO_INCREMENT COMMENT 'group id',
  `groupname` varchar(45) NOT NULL COMMENT 'group name',
  `customerId` int(3) DEFAULT NULL COMMENT 'customer id',
  `server` varchar(100) DEFAULT NULL,
  `serverUser` varchar(45) DEFAULT NULL,
  `serverPassword` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `notesDBPath` varchar(100) DEFAULT NULL,
  `exportResultPath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='project info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_group`
--

LOCK TABLES `tb_group` WRITE;
/*!40000 ALTER TABLE `tb_group` DISABLE KEYS */;
INSERT INTO `tb_group` VALUES (1,'group1',15,'10.50.10.37','Administrator','runhe123','group1','20191101','/home/app/pagoda/db/group1'),(2,'group2',16,'10.50.10.37','Administrator','runhe123','住友','20191101','E:\\notesDB\\export\\group1'),(13,'group3',27,'10.50.10.37','Administrator','runhe123',NULL,'hoperundev','E:\\notesDB\\export\\group3');
/*!40000 ALTER TABLE `tb_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_message`
--

DROP TABLE IF EXISTS `tb_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acceptor` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `sender` varchar(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_message`
--

LOCK TABLES `tb_message` WRITE;
/*!40000 ALTER TABLE `tb_message` DISABLE KEYS */;
INSERT INTO `tb_message` VALUES (1,'testPM,','reject','dd',NULL,'2019-12-7'),(2,'testPM,','waitting','dd',1,'2019-12-6'),(3,'testPM,','waitting','sss',1,'2019-12-7'),(4,'test2','角色不对','admin',2,'2019-12-18'),(5,'admin,','one new user need approve','admin',1,'2019-12-18'),(6,'admin,','test test',NULL,2,'2019-12-18'),(7,'admin,','test1',NULL,1,'2019-12-18'),(8,'admin,','test1',NULL,1,'2019-12-19'),(9,'admin,','dddd',NULL,NULL,'2019-12-17'),(10,'admin,','dafadgfg',NULL,NULL,'2019-12-17'),(11,'admin,','dafadgfg',NULL,NULL,'2019-12-17'),(12,'admin,','dafadgfg',NULL,NULL,'2019-12-17'),(13,'admin,','dafadgfg',NULL,NULL,'2019-12-17'),(14,'admin,','dafadgfg',NULL,NULL,'2019-12-17'),(15,'test1','对对对','admin',2,'2020-01-14'),(29,'registerTest,','no group','admin',2,'2020-01-16'),(30,'admin,','new approver',NULL,0,'2020-02-04'),(31,'pm1,','new user need approve',NULL,0,'2020-02-04'),(32,'admin,','new user need approve',NULL,0,'2020-02-04'),(33,'pm2,','new user need approve',NULL,0,'2020-02-04'),(34,'pm2,','new user need approve',NULL,0,'2020-02-04');
/*!40000 ALTER TABLE `tb_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_role` (
  `roleId` int(2) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(10) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (2,'PM'),(3,'user');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_user` (
  `userId` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `jobTitle` varchar(45) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `superuser` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin','$2a$10$fsD2qu5n.OT5dd3B4T0Ptu6qvGaPVBLDQrco0/ZFF2yg51dNHjHpC','admin@hoperun.com',NULL,'A','','Y'),(114,'pm1','$2a$10$0RmtgrXi9EPt0XsZqnur1OcXJ6G1FkrgXImKzupowcHYeuYq/whga','pm1@test.com','PM','A',NULL,'N'),(115,'pm2','$2a$10$vMVyypo1p4cwXYJZ096WFOc7ARY4xk2HUbDtOaL5mk.vIVP/y3H/i','pm2@test.com','PM','A',NULL,'N'),(116,'user1','$2a$10$ZhWIomwb5i5MkvFkmJBSOOFUof24o7.zJorl/6gCGmM3aKq8BeIlK','user1@test.com','PG','A',NULL,'N'),(117,'user21','$2a$10$xmxe/aP04vmS7tHgxEoADeaAE57M9kIxCZY9w2DiS1eBvj6O2Ot4u','user21@test.com','PG','A',NULL,'N'),(118,'user22','$2a$10$dGVAYtPAqYiNJhc8Lgh6Re3fhXg.GLZ2ZHA3fhPXISEBDHrLSyaoC','user22@test.com','PG','A',NULL,'N');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(8) DEFAULT NULL,
  `roleId` int(3) DEFAULT NULL,
  `groupId` int(3) DEFAULT NULL,
  `status` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
INSERT INTO `tb_user_role` VALUES (189,114,2,1,'A'),(190,115,2,2,'A'),(191,116,3,1,'A'),(192,117,3,2,'A'),(193,118,3,2,'A'),(194,0,0,0,'A');
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role_temp`
--

DROP TABLE IF EXISTS `tb_user_role_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_user_role_temp` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(8) DEFAULT NULL,
  `roleId` int(3) DEFAULT NULL,
  `groupId` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role_temp`
--

LOCK TABLES `tb_user_role_temp` WRITE;
/*!40000 ALTER TABLE `tb_user_role_temp` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_role_temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_view_info`
--

DROP TABLE IF EXISTS `tb_view_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_view_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dbName` varchar(45) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `alias` varchar(200) DEFAULT NULL,
  `columnNum` int(11) DEFAULT NULL,
  `actionNum` int(11) DEFAULT NULL,
  `allCodeNum` int(11) DEFAULT NULL,
  `commentCodeNum` int(11) DEFAULT NULL,
  `blankCodeNum` int(11) DEFAULT NULL,
  `usefulCodeNum` int(11) DEFAULT NULL,
  `callTimes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_view_info`
--

LOCK TABLES `tb_view_info` WRITE;
/*!40000 ALTER TABLE `tb_view_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_view_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-15 13:14:07
