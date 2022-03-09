-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: clientes
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administradores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administradores`
--

LOCK TABLES `administradores` WRITE;
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
INSERT INTO `administradores` VALUES (3,'Administrador','Uno','administrador1@gmail.com','456',''),(4,'Administrador','Dos','administrador2@gmail.com','789',''),(13,'Joaquin','Yo','j@gmail.com','123','4');
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad_obrasocial`
--

DROP TABLE IF EXISTS `especialidad_obrasocial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad_obrasocial` (
  `porcentaje_cobertura` float NOT NULL,
  `id_os` int NOT NULL,
  `cod_especialidad` int NOT NULL,
  PRIMARY KEY (`id_os`,`cod_especialidad`),
  KEY `fk_cod_especialidad_idx` (`cod_especialidad`),
  CONSTRAINT `fk_cod_especialidad` FOREIGN KEY (`cod_especialidad`) REFERENCES `especialidades` (`codigo_esp`),
  CONSTRAINT `fk_id_os` FOREIGN KEY (`id_os`) REFERENCES `obras_sociales` (`id_obra_social`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad_obrasocial`
--

LOCK TABLES `especialidad_obrasocial` WRITE;
/*!40000 ALTER TABLE `especialidad_obrasocial` DISABLE KEYS */;
INSERT INTO `especialidad_obrasocial` VALUES (0.9,1,1),(0.8,1,2),(0.8,1,3),(0.9,1,4),(0.9,1,5),(0,1,6),(0.3,1,7),(0.9,2,1),(0.9,2,2),(0.75,2,3),(0.95,2,4),(0.95,2,5),(0.2,2,6),(0.3,2,7),(1,3,1),(1,3,2),(0.9,3,3),(0.8,3,4),(0.9,3,5),(0.2,3,6),(0.1,3,7),(0.9,4,1),(0.85,4,2),(0.9,4,3),(0.85,4,4),(0.75,4,5),(0.3,4,6),(0.4,4,7),(0.85,5,1),(0.75,5,2),(0.8,5,3),(0.85,5,4),(0.75,5,5),(0.2,5,6),(0.1,5,7),(0.8,6,1),(0.85,6,2),(0.9,6,3),(0.7,6,4),(0.9,6,5),(0.2,6,6),(0.1,6,7),(0.9,7,1),(0.9,7,2),(0.95,7,3),(0.75,7,4),(0.95,7,5),(0.3,7,6),(0.1,7,7),(0.85,8,1),(0.85,8,2),(0.85,8,3),(1,8,4),(0.95,8,5),(0.1,8,6),(0,8,7),(0.8,9,1),(0.7,9,2),(0.65,9,3),(1,9,4),(0.95,9,5),(0.2,9,6),(0.5,9,7),(1,10,1),(0.2,10,2),(0.2,10,3),(0.2,10,4),(0.3,10,5),(0.4,10,6),(0.9,10,7),(0.7,11,1),(1,11,2),(0.7,11,3),(0.8,11,4),(0.2,11,5),(0.1,11,6),(0.2,11,7);
/*!40000 ALTER TABLE `especialidad_obrasocial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades` (
  `codigo_esp` int NOT NULL AUTO_INCREMENT,
  `nombre_esp` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_esp`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades`
--

LOCK TABLES `especialidades` WRITE;
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
INSERT INTO `especialidades` VALUES (1,'cardiologia'),(2,'neurología'),(3,'traumatología'),(4,'Pediatria'),(5,'Neurología'),(6,'Kinesiología'),(7,'Podología');
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obras_sociales`
--

DROP TABLE IF EXISTS `obras_sociales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obras_sociales` (
  `id_obra_social` int NOT NULL AUTO_INCREMENT,
  `nombre_os` varchar(45) NOT NULL,
  PRIMARY KEY (`id_obra_social`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras_sociales`
--

LOCK TABLES `obras_sociales` WRITE;
/*!40000 ALTER TABLE `obras_sociales` DISABLE KEYS */;
INSERT INTO `obras_sociales` VALUES (1,'Federada Salud'),(2,'Osde'),(3,'Medicus'),(4,'Swiss Medical'),(5,'Galeno'),(6,'Medifé'),(7,'Pami'),(8,'ACA Salud'),(9,'SanCor Salud'),(10,'ManuSalud'),(11,'Salud2.0');
/*!40000 ALTER TABLE `obras_sociales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `num_tel` varchar(45) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `id_obra_social` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`email`),
  KEY `id_os_idx` (`id_obra_social`),
  CONSTRAINT `id_os` FOREIGN KEY (`id_obra_social`) REFERENCES `obras_sociales` (`id_obra_social`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (1,'johndoe@gmail.com','1234','John','Doe','3464521478','41932457',1),(2,'paciente2@gmail.com','456','Paciente','Segundo','3415478214','39521478',7),(3,'e_keen@gmail.com','abcd','Elizabeth','Keen','2225555','21458741',5),(4,'donald@gmail.com','home123','Donald','Ressler','341551247','21458724',2),(5,'asher_millstone@gmail.com','grupo935','Asher','Millstone','3464821457','30123698',5),(6,'connor_95@gmail.com','walsh95','Connor','Walsh','3464558201','38147852',5),(7,'ross_geller@gmail.com','dinosaurs','Ross','Geller','34145687','35123789',6),(8,'r_green@gmail.com','rachel','Rachel','Green','3464123657','35159874',9),(9,'c.bing@gmail.com','omg','Chandler','Bing','3464123614','35159845',7),(10,'holden.ford@gmail.com','hola789','Holden','Ford','346452147','32145876',4),(11,'house@gmail.com','house80','Gregory','House','341551236','36452123',1),(12,'lisa.cuddy@gmail.com','lisa80','Lisa','Cuddy','341551288','41932478',3),(16,'hp_@gmail.com','1111','Harry','Potter','12323442','87654321',9),(17,'john_wick@gmail.com','bbb','John','Wick','222222','12345676',6);
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesionales`
--

DROP TABLE IF EXISTS `profesionales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesionales` (
  `matricula` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre_prof` varchar(45) NOT NULL,
  `apellido_prof` varchar(45) NOT NULL,
  `cod_especialidad` int NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `mail_UNIQUE` (`email`),
  KEY `fk_codigo_especialidad_idx` (`cod_especialidad`),
  CONSTRAINT `fk_codigo_especialidad` FOREIGN KEY (`cod_especialidad`) REFERENCES `especialidades` (`codigo_esp`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesionales`
--

LOCK TABLES `profesionales` WRITE;
/*!40000 ALTER TABLE `profesionales` DISABLE KEYS */;
INSERT INTO `profesionales` VALUES ('11111','wilson@gmail.com','wilson123','Wilson','Zimmerman',1,1),('1234','j_snow@gmail.com','123','John','Snow',2,1),('123456','sn_1980@gmail.com','sn1980','Sophie','Neveu',3,0),('147258','edmond_85@gmail.com','origen','Edmond','Kirsch',3,1),('22222','robert_langdon@gmail.com','robert80','Robert','Langdon',1,1),('33333','e.stark@gmail.com','999','Eddard','Stark',4,1),('333333','s_brooks@gmail.com','brooks10','Sienna','Brooks',2,1),('40619','jgar@gmail.com','123','Joaq','Gar',7,1),('444444','h_sims@gmail.com','harry456','Harry','Sims',2,1),('55555','a.dumbledore@gmail.com','111','Albus','Dumbledore',3,1),('632154','t_lannister@gmail.com','opq','Tyrion','Lannister',2,1),('963852','sirius_black@gmail.com','hello','Sirius','Black',5,1),('987333','e_foreman@gmail.com','eric80','Eric','Foreman',3,1),('987654','james_wilson@gmail.com','james80','James','Wilson',1,1);
/*!40000 ALTER TABLE `profesionales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turnos` (
  `numero` int NOT NULL AUTO_INCREMENT,
  `fecha_turno` date NOT NULL,
  `hora_turno` time NOT NULL,
  `matricula_prof` varchar(10) DEFAULT NULL,
  `id_paciente` int DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `fk_mat_profesional_idx` (`matricula_prof`),
  KEY `fk_id_paciente_idx` (`id_paciente`),
  CONSTRAINT `fk_id_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id`),
  CONSTRAINT `fk_mat_profesional` FOREIGN KEY (`matricula_prof`) REFERENCES `profesionales` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (2,'2021-12-01','10:30:00','11111',1),(3,'2021-12-01','10:00:00','11111',NULL),(4,'2021-12-01','11:00:00','11111',NULL),(5,'2021-12-01','11:30:00','11111',NULL),(6,'2021-12-01','11:30:00','22222',NULL),(7,'2021-12-01','12:30:00','22222',NULL),(8,'2021-12-01','14:30:00','22222',2),(9,'2021-12-01','15:30:00','22222',NULL),(10,'2021-12-02','15:00:00','22222',4),(11,'2021-12-02','11:00:00','22222',NULL),(12,'2021-12-02','09:00:00','22222',NULL),(13,'2021-12-02','09:00:00','987654',3),(14,'2021-12-02','09:30:00','987654',NULL),(15,'2021-12-02','08:30:00','987654',NULL),(16,'2021-12-02','15:00:00','333333',2),(17,'2021-12-02','15:45:00','333333',NULL),(18,'2021-12-02','16:15:00','333333',NULL),(19,'2021-12-02','17:00:00','333333',NULL),(20,'2021-12-02','17:45:00','333333',NULL),(21,'2021-12-02','18:15:00','333333',NULL),(22,'2021-12-02','14:00:00','444444',NULL),(23,'2021-12-02','15:00:00','444444',NULL),(24,'2021-12-02','16:00:00','444444',NULL),(25,'2021-12-02','17:00:00','444444',NULL),(26,'2021-12-05','17:30:00','444444',NULL),(27,'2021-12-05','16:30:00','444444',17),(28,'2021-12-05','18:00:00','444444',7),(29,'2021-12-06','10:00:00','123456',NULL),(30,'2021-12-06','10:30:00','123456',NULL),(31,'2021-12-06','11:00:00','123456',NULL),(32,'2021-12-06','11:30:00','123456',NULL),(33,'2021-12-06','12:00:00','123456',NULL),(34,'2021-12-06','12:30:00','123456',NULL),(35,'2021-12-06','13:00:00','123456',NULL),(36,'2021-12-06','13:30:00','123456',NULL),(37,'2021-12-06','12:30:00','147258',NULL),(38,'2021-12-06','13:30:00','147258',NULL),(39,'2021-12-06','14:30:00','147258',3),(40,'2021-12-07','15:30:00','147258',NULL),(41,'2021-12-07','16:30:00','147258',1),(42,'2021-12-07','17:30:00','147258',NULL),(44,'2022-03-01','09:30:00','55555',NULL),(45,'2022-03-01','10:30:00','55555',NULL),(46,'2022-03-01','11:30:00','55555',NULL),(47,'2022-03-01','14:30:00','55555',NULL),(48,'2022-03-01','16:15:00','55555',NULL),(49,'2022-04-03','16:45:00','55555',NULL),(50,'2022-04-03','10:15:00','55555',NULL),(51,'2022-04-05','14:00:00','33333',NULL),(52,'2022-04-05','14:30:00','33333',NULL),(53,'2022-04-05','15:30:00','33333',NULL),(54,'2022-04-05','16:00:00','33333',NULL),(55,'2022-04-05','18:30:00','33333',NULL),(56,'2022-03-10','10:00:00','40619',2),(57,'2022-03-10','10:30:00','40619',NULL),(58,'2022-03-10','11:00:00','40619',NULL),(59,'2022-03-10','11:30:00','40619',9),(60,'2022-03-10','12:00:00','40619',NULL),(61,'2022-03-10','12:30:00','40619',10);
/*!40000 ALTER TABLE `turnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `dni` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'johndoe@gmail.com','1234','John','Doe','3464521478','41932457'),(2,'paciente2@gmail.com','456','Paciente','Segundo','3415478214','39521478');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valor_especialidad`
--

DROP TABLE IF EXISTS `valor_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valor_especialidad` (
  `fecha_desde` date NOT NULL,
  `cod_especialidad` int NOT NULL,
  `valor` int NOT NULL,
  PRIMARY KEY (`fecha_desde`,`cod_especialidad`),
  KEY `fk_cod_especialidad_idx` (`cod_especialidad`),
  CONSTRAINT `fk_cod_esp` FOREIGN KEY (`cod_especialidad`) REFERENCES `especialidades` (`codigo_esp`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_especialidad`
--

LOCK TABLES `valor_especialidad` WRITE;
/*!40000 ALTER TABLE `valor_especialidad` DISABLE KEYS */;
INSERT INTO `valor_especialidad` VALUES ('2010-10-05',1,100),('2010-10-05',2,120),('2010-10-05',3,110),('2012-12-10',1,200),('2012-12-10',2,200),('2012-12-10',3,230),('2015-12-10',1,430),('2015-12-10',2,400),('2015-12-10',3,400),('2019-08-01',1,500),('2019-08-01',2,500),('2019-08-01',3,450),('2020-09-05',1,700),('2020-09-05',2,800),('2020-09-05',3,800),('2021-01-01',1,900),('2021-01-01',2,900),('2021-01-01',3,950),('2022-02-25',1,950),('2022-02-25',2,895),('2022-02-25',4,500),('2022-02-25',5,650),('2022-02-25',6,450),('2022-02-25',7,1500),('2022-03-09',6,950);
/*!40000 ALTER TABLE `valor_especialidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-09  4:50:23
