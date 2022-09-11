-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema2
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `id_genre` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Id_genre_idx` (`id_genre`),
  CONSTRAINT `Id_genre` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Ti presento i miei','1996',2),(2,'Dead man walking','1999',4),(3,'Crimes of the Future','2022',3),(4,'IT','1987',1),(5,'Fascisti su Marte','2002',2),(6,'Solaris','1976',4),(7,'Blade Runner','1985',3),(8,'Halloween','1984',1),(9,'Little Miss Sunshine','2008',2),(10,'Casablanca','1956',4),(11,'Taken','2004',3),(12,'Le Colline hanno gli Occhi','1975',1),(13,'I Tenenbaum','1991',2),(14,'Inception','2018',3),(15,'Il mostro di Dusseldorf','1929',3),(16,'Il mostro della palude','1962',1),(21,'Spiderman 2','2006',3),(23,'La lunga notte','1962',1),(31,'Lo spaccacuori','2002',2),(37,'Topo gigio sterminatore','1989',1),(39,'Topo gigio bellissimo','1976',1),(40,'Apocalypto','2003',4),(41,'La lunga mano','2002',1);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-11 16:55:52
