CREATE DATABASE  IF NOT EXISTS `sportsclub` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sportsclub`;
-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: sportsclub
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
                          `member_id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(45) DEFAULT NULL,
                          `address` varchar(45) DEFAULT NULL,
                          `zip` int DEFAULT NULL,
                          `gender` varchar(1) DEFAULT NULL,
                          `year` varchar(45) DEFAULT NULL,
                          PRIMARY KEY (`member_id`),
                          KEY `fk_medlem_postnr_idx` (`zip`),
                          CONSTRAINT `fk_member_zip` FOREIGN KEY (`zip`) REFERENCES `zip` (`zip`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--


/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (2,'Hans Sørensen','Agernvej 3',3700,'m','2000'),(3,'Jens Kofoed','Agrevej 5',3700,'m','2001'),(4,'Peter Lundin','Ahlegårdsvejen 7',3700,'m','2002'),(5,'Frederik Carlsen','Ahlstrandsvej 12',3700,'m','2005'),(6,'Peter Petersen','Ahornvej 54',3700,'m','1999'),(7,'Ida Kofoed','Blekingevej 43',3700,'k','1999'),(8,'Bente Svendsen','Blemmelyngvejen 32',3700,'k','1998'),(9,'Tove Jørgensen','Blommehaven 2',3700,'k','1995'),(10,'Søren Langeland','Danielstræde 4',3700,'m','1996'),(11,'Jacob Sørensen','Degnebrovejen 5',3700,'m','1996'),(12,'Nikolaj Kofoed','Dittes Vej 1',3700,'m','1970'),(13,'Nynne Sofia','Doktorbakken 112',3700,'k','1970'),(14,'Per Jørgensen','Doktordammen 76',3760,'m','1980'),(15,'Ole Lukøje','Ejnar Jensens Vej 32',3730,'m','1985'),(16,'Rasmus Kofoed','Ejnar Mikkelsensvej 6',3740,'m','1990'),(17,'Jens Nielsen','Ekkodalsvejen 6',3740,'m','1950'),(18,'Marinus Bastiansen','Elisabetsvej 11',3730,'m','1955'),(19,'Jesper Find Nilsen','Ellebakken 4',3760,'m','1958'),(20,'Lars Kofoed','Ellebyvej 56',3740,'m','1959'),(21,'Søren Olsen','Fabriksvej 45',3770,'m','1960'),(22,'Emil Jørgensen','Falckvej 7',3740,'m','1954'),(23,'Benjamin Sørensen','Falsterbovej 98',3760,'m','1943'),(24,'Rigmor Jensen','Fasanvangen 8',3782,'k','2000'),(25,'Ulrikke Kofoed','Glappevej 34',3730,'k','2001'),(26,'Karla Svendsen','Glastorvet 3',3782,'k','2002'),(27,'Sørine Olsen','Glasværksvej 6',3760,'k','2005'),(28,'Magda Boulet','Glentevangen 8',3782,'k','1999'),(29,'Viktor Sørensen','Godthåbsvej 42',3782,'m','1999'),(30,'Frank Kofoed','Hallandsvej 65',3782,'m','1980'),(31,'Karl Nielsen','Hallebakken 87',3760,'m','1985'),(32,'Ulrik Pedersen','Halledalsvej 74',3770,'m','1990'),(33,'Jeppe Sørensen','Hallegårdsvejen 25',3740,'m','1950'),(34,'Mathias Kofoed','Helligdomsvej 67',3700,'m','1955'),(35,'Marcus Nielsen','Helligpedervej 27',3770,'m','1958'),(36,'Joshua Johansen','Helsevej 28',3700,'m','1959'),(37,'Kenneth Sørensen','Henrik Hansensgade 29',3700,'m','1980'),(38,'Dorthe Schou','Hentregårdsvej 61',3760,'k','1985'),(39,'Dorte Kofoed','Jomfrudalen 64',3700,'k','1990'),(40,'Johan Frederiksen','Jomfruskoven 3',3700,'m','1950'),(41,'Johanne Nielsen','Jomfrustien 14',3730,'k','1955'),(42,'Inger Lundbæk','Jons Kapelvej 17',3700,'k','1958'),(43,'Rikke Sørensen','Jordbærdalen 6',3700,'k','1959'),(44,'Vibeke Wagener','Jordbærhaven 8',3700,'k','2002'),(45,'Charlotte Nielsen','Knudsvej 10',3700,'k','2005'),(46,'Mats Edin','Kobbevej 62',3770,'m','1999'),(47,'Bjørn Andersen','Kodalsvejen 12',3770,'m','1999'),(48,'Jakob Skov','Kofoedsvej 7',3730,'m','1998'),(49,'Jønke Svendsen','Jomfrudalen 13',3770,'m','1995'),(50,'Svend Svin','Jomfruskoven 21',3770,'m','2004'),(51,'Nynne Sofia','Doktorbakken 112',3700,'k','1970'),(52,'Nynne Sofia','Doktorbakken 112',3700,'k','1970'),(53,'Ole Olsen','Banegade 2',3700,'m','1967'),(55,'Ole Olsen','Banegade 2',3700,'m','1967');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
                                `member_id` int NOT NULL,
                                `team_id` varchar(10) NOT NULL,
                                `price` int DEFAULT NULL,
                                PRIMARY KEY (`member_id`,`team_id`),
                                KEY `fk_medlem_has_hold_hold1_idx` (`team_id`),
                                KEY `fk_medlem_has_hold_medlem1_idx` (`member_id`),
                                CONSTRAINT `fk_registration_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
                                CONSTRAINT `fk_registration_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--


/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (2,'yo02',168),(3,'fo01',146),(4,'h01',126),(5,'tai01',198),(6,'tai02',130),(7,'sv01',180),(8,'sv02',129),(9,'ten01',119),(10,'ten02',197),(11,'bt01',157),(12,'gym01',128),(13,'yo02',116),(14,'fo01',194),(15,'h01',195),(16,'tai01',198),(17,'tai02',157),(18,'sv01',102),(19,'sv02',126),(20,'ten01',105),(21,'ten02',118),(22,'bt01',114),(23,'gym01',179),(24,'yo02',184),(25,'fo01',138),(26,'h01',127),(27,'tai01',183),(28,'tai02',153),(29,'sv01',137),(30,'sv02',164),(31,'ten01',163),(32,'ten02',122),(33,'bt01',127),(34,'gym01',113),(35,'yo02',198),(36,'fo01',194),(37,'h01',135),(38,'tai01',135),(39,'tai02',119),(40,'sv01',158),(41,'sv02',189),(42,'ten01',181),(43,'ten02',102),(44,'bt01',151),(45,'gym01',174),(46,'yo02',107),(47,'fo01',122),(48,'h01',176),(49,'tai01',138),(50,'tai02',121);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;


--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport` (
                         `sport_id` int NOT NULL AUTO_INCREMENT,
                         `sport` varchar(45) NOT NULL,
                         PRIMARY KEY (`sport_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--


/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'Yoga'),(2,'Fodbold'),(3,'Håndbold'),(4,'TaiChi'),(5,'Svømning'),(6,'Tennis'),(7,'Bordtennis'),(8,'Gymnastik');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;


--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
                        `team_id` varchar(10) NOT NULL,
                        `sport_id` int NOT NULL,
                        PRIMARY KEY (`team_id`),
                        KEY `fk_hold_sportsgren1_idx` (`sport_id`),
                        CONSTRAINT `fk_team_sport1` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`sport_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--


/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('yo01',1),('yo02',1),('fo01',2),('h01',3),('tai01',4),('tai02',4),('sv01',5),('sv02',5),('ten01',6),('ten02',6),('bt01',7),('gym01',8);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;


--
-- Table structure for table `zip`
--

DROP TABLE IF EXISTS `zip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zip` (
                       `zip` int NOT NULL,
                       `city` varchar(45) NOT NULL,
                       PRIMARY KEY (`zip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zip`
--


/*!40000 ALTER TABLE `zip` DISABLE KEYS */;
INSERT INTO `zip` VALUES (3700,'Rønne'),(3730,'Nexø'),(3740,'Svanneke'),(3760,'Gudhjem'),(3770,'Allinge'),(3782,'Klemmensker');
/*!40000 ALTER TABLE `zip` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-17  0:49:00
