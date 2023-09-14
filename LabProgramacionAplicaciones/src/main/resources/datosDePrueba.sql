-- MySQL dump 10.13  Distrib 5.7.42, for Linux (x86_64)
--
-- Host: localhost    Database: turismouy
-- ------------------------------------------------------
-- Server version	5.7.42-0ubuntu0.18.04.1

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
-- Table structure for table `PAQUETE_ACTIVIDAD`
--

DROP TABLE IF EXISTS `PAQUETE_ACTIVIDAD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAQUETE_ACTIVIDAD` (
  `ACTIVIDAD_ID` bigint(20) NOT NULL,
  `PAQUETE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ACTIVIDAD_ID`,`PAQUETE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAQUETE_ACTIVIDAD`
--

LOCK TABLES `PAQUETE_ACTIVIDAD` WRITE;
/*!40000 ALTER TABLE `PAQUETE_ACTIVIDAD` DISABLE KEYS */;
INSERT INTO `PAQUETE_ACTIVIDAD` VALUES (124,70),(124,71),(125,73),(125,74);
/*!40000 ALTER TABLE `PAQUETE_ACTIVIDAD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROVEEDOR_ACTIVIDAD`
--

DROP TABLE IF EXISTS `PROVEEDOR_ACTIVIDAD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROVEEDOR_ACTIVIDAD` (
  `PROVEEDOR_ID` bigint(20) NOT NULL,
  `ACTIVIDAD_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`PROVEEDOR_ID`,`ACTIVIDAD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROVEEDOR_ACTIVIDAD`
--

LOCK TABLES `PROVEEDOR_ACTIVIDAD` WRITE;
/*!40000 ALTER TABLE `PROVEEDOR_ACTIVIDAD` DISABLE KEYS */;
INSERT INTO `PROVEEDOR_ACTIVIDAD` VALUES (11,70),(11,71),(12,75),(12,76),(13,73),(13,74);
/*!40000 ALTER TABLE `PROVEEDOR_ACTIVIDAD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN_TABLE',150);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividadTuristica`
--

DROP TABLE IF EXISTS `actividadTuristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividadTuristica` (
  `ID` bigint(20) NOT NULL,
  `CIUDAD` varchar(255) DEFAULT NULL,
  `COSTO` float DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `DURACION` varchar(255) DEFAULT NULL,
  `FECHAALTA` datetime DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `EDEPARTAMENTO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`),
  KEY `FK_actividadTuristica_EDEPARTAMENTO_ID` (`EDEPARTAMENTO_ID`),
  CONSTRAINT `FK_actividadTuristica_EDEPARTAMENTO_ID` FOREIGN KEY (`EDEPARTAMENTO_ID`) REFERENCES `departamento` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadTuristica`
--

LOCK TABLES `actividadTuristica` WRITE;
/*!40000 ALTER TABLE `actividadTuristica` DISABLE KEYS */;
INSERT INTO `actividadTuristica` VALUES (70,'Rocha',800,'Festival gastron´omico de productos locales en Rocha','3','2022-07-20 00:00:00','Degusta',52),(71,'Rocha',500,'En el mes aniversario del Club Deportivo Uni´on de Rocha te invitamos a una merienda deliciosa.','3','2022-07-21 00:00:00','Teatro con Sabores',52),(73,'Colonia del Sacramento',400,'Con guia especializado y en varios idiomas. Varios circuitos posibles.','2','2022-08-01 00:00:00','Tour por Colonia del Sacramento',62),(74,'Colonia del Sacramento',800,'Restaurante en la renovada Plaza de Toros con menu internacional','2','2022-08-01 00:00:00','Almuerzo en el Real de San Carlos',62),(75,'Tranqueras',300,'Almuerzo en la Posada con ticket fijo. Menu que incluye bebida y postre casero.','2','2022-08-01 00:00:00','Almuerzo en Valle del Lunarejo',56),(76,'Tranqueras',150,'Cabalgata por el ´area protegida. Varios recorridos para elegir.','2','2022-08-01 00:00:00','Cabalgata en Valle del Lunarejo',56);
/*!40000 ALTER TABLE `actividadTuristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (14,'Division Turismo de la Intendencia','Canelones','https://www.imcanelones.gub.uy/es'),(15,'Division Turismo de la Intendencia ','Maldonado','https://www.maldonado.gub.uy/'),(52,'La Organizaci´on de Gesti´on del Destino (OGD) Rocha es un ´ambito de articulaci´on p´ublico','Rocha','www.turismorocha.gub.uy'),(53,'Divisi´on Turismo de la Intendencia','Treinta y Tres','https://treintaytres.gub.uy/'),(54,'Divisi´on Turismo de la Intendencia ','Cerro Largo','https://www.gub.uy/intendencia- cerro-largo'),(56,'Promociona e implementa proyectos e iniciati- vas sostenibles de inter´es','Rivera ','www.rivera.gub.uy/social/turismo/'),(57,'Divisi´on Turismo de la Intendencia','Artigas','http://www.artigas.gub.uy'),(58,'Divisi´on Turismo de la Intendencia ','Salto','https://www.salto.gub.u'),(59,'Divisi´on Turismo de la Intendencia','Paysandu','https://www.paysandu.gub.uy'),(60,'Divisi´on Turismo de la Intendencia','Rio Negro','https://www.rionegro.gub.uy'),(61,'Divisi´on Turismo de la Intendencia','Soriano ','https://www.soriano.gub.uy'),(62,'La propuesta del Departamento de Colonia divide en cuatro actos su espect´aculo anual. Cada acto tiene su magia.','Colonia ','https://colonia.gub.uy/turismo'),(63,'Divisi´on Turismo de la Intenden','San Jose','https://sanjose.gub.uy'),(64,'Divisi´on Turismo de la Intendencia ','Flores','https://flores.gub.uy'),(65,'Divisi´on Turismo de la Intendencia ','Florida','http://www.florida.gub.uy'),(66,'Divisi´on Turismo de la Intendencia','Lavalleja','http://www.lavalleja.gub.uy'),(67,'Divisi´on Turismo de la Intendencia','Durazno','https://durazno.uy'),(68,'Divisi´on Turismo de la Intendencia ','Tacuarembo','https://tacuarembo.gub.uy'),(69,'Divisi´on Turismo de la Intendencia ','Montevideo','https://montevideo.gub.uy/areas- tematicas/turismo');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscripcion` (
  `ID` bigint(20) NOT NULL,
  `CANTIDADTURISTAS` int(11) DEFAULT NULL,
  `COSTOTOTAL` float DEFAULT NULL,
  `FECHA` datetime DEFAULT NULL,
  `ESALIDATURISTICA_ID` bigint(20) DEFAULT NULL,
  `ETURISTA_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_inscripcion_ESALIDATURISTICA_ID` (`ESALIDATURISTICA_ID`),
  KEY `FK_inscripcion_ETURISTA_ID` (`ETURISTA_ID`),
  CONSTRAINT `FK_inscripcion_ESALIDATURISTICA_ID` FOREIGN KEY (`ESALIDATURISTICA_ID`) REFERENCES `salidaTuristica` (`ID`),
  CONSTRAINT `FK_inscripcion_ETURISTA_ID` FOREIGN KEY (`ETURISTA_ID`) REFERENCES `turista` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES (113,3,2400,'2022-08-15 10:59:00',101,1),(114,5,4000,'2022-08-16 11:36:00',102,5),(115,3,1200,'2023-08-18 11:36:00',106,1),(116,1,400,'2023-08-19 11:39:00',106,2),(117,2,1600,'2023-08-19 11:41:00',108,10),(118,1,500,'2023-08-19 11:42:00',103,9),(119,10,5000,'2023-08-20 11:44:00',104,9),(120,2,1000,'2023-08-20 11:46:00',104,7),(121,1,500,'2023-08-21 11:47:00',104,3),(122,1,800,'2023-08-21 11:48:00',108,8);
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquetes`
--

DROP TABLE IF EXISTS `paquetes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paquetes` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `DESCUENTO` float DEFAULT NULL,
  `FECHAALTA` datetime DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `VALIDEZ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquetes`
--

LOCK TABLES `paquetes` WRITE;
/*!40000 ALTER TABLE `paquetes` DISABLE KEYS */;
INSERT INTO `paquetes` VALUES (124,'Actividades para hacer en familia y disfrutar arte y gastronomia',20,'2022-08-10 00:00:00','Disfrutar Rocha',60),(125,'Paseos por el casco historico y se puede terminar con Almuerzo en la plaza de Toros',15,'2022-08-01 00:00:00','Un dia en Colonia ',45);
/*!40000 ALTER TABLE `paquetes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `ID` bigint(20) NOT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `WEBSITEURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `NICKNAME` (`NICKNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (11,'1970-09-14','Hola! me llamo Washington y soy el encargado\ndel portal de turismo del departamento de Rocha -\nUruguay','washington@turismorocha.gub.uy','Rocha','Washington','washington','http://turismorocha.gub.uy/'),(12,'1965-06-27','Pablo es el presidente de la Sociedad de Fomento\nTuristico de Rivera (conocida como Socfomturriv)','eldiez@socfomturriv.org.uy','Bengoechea','Pablo','eldiez','http://wwww.socfomturriv.org.uy'),(13,'1990-12-31','Departamento de Turismo del Departamento de Colo-\nnia','meche@colonia.gub.uy','Venn','Mercedes','meche','https://colonia.gub.uy/turismo');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salidaTuristica`
--

DROP TABLE IF EXISTS `salidaTuristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salidaTuristica` (
  `ID` bigint(20) NOT NULL,
  `CANTIDADMAXTURISTAS` int(11) DEFAULT NULL,
  `FECHAALTA` datetime DEFAULT NULL,
  `FECHASALIDA` datetime DEFAULT NULL,
  `LUGAR` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `EACTIVIDADTURISTICA_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`),
  KEY `FK_salidaTuristica_EACTIVIDADTURISTICA_ID` (`EACTIVIDADTURISTICA_ID`),
  CONSTRAINT `FK_salidaTuristica_EACTIVIDADTURISTICA_ID` FOREIGN KEY (`EACTIVIDADTURISTICA_ID`) REFERENCES `actividadTuristica` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salidaTuristica`
--

LOCK TABLES `salidaTuristica` WRITE;
/*!40000 ALTER TABLE `salidaTuristica` DISABLE KEYS */;
INSERT INTO `salidaTuristica` VALUES (101,20,'2022-07-21 10:59:00','2022-08-20 17:00:00','Sociedad Agropecuaria de Rocha','Degusta Agosto',70),(102,20,'2022-07-22 11:03:00','2022-03-09 17:00:00','Sociedad Agropecuaria de Rocha','Degusta Setiembre',70),(103,30,'2022-07-23 11:05:00','2022-09-04 18:00:00','Club Deportivo Union','Teatro con Sabores 1',71),(104,30,'2022-07-23 11:10:00','2022-09-11 18:00:00','Club Deportivo Union','Teatro con Sabores 2',71),(105,5,'2022-08-05 11:11:00','2022-09-11 10:00:00','Encuentro en la base del Faro','Tour Colonia del Sacramento 11-09',73),(106,5,'2022-08-05 11:14:00','2022-09-18 10:00:00','Encuentro en la base del Faro','Tour Colonia del Sacramento 18-09',73),(107,5,'2022-08-04 11:16:00','2023-09-18 12:00:00','Restaurante de la Plaza de Toros','Almuerzo 1',74),(108,5,'2022-08-04 11:18:00','2022-09-25 12:00:00','Restaurante de la Plaza de Toros','Almuerzo 2 ',74),(109,4,'2022-08-15 11:21:00','2022-09-10 12:00:00','Posada Del Lunarejo','Almuerzo 3 ',75),(110,4,'2022-08-15 11:22:00','2022-09-11 12:00:00','Posada Del Lunarejo','Almuerzo 4',75),(111,4,'2022-08-15 11:24:00','2022-09-10 16:00:00','Posada del Lunarejo','Cabalgata 1',76),(112,4,'2023-08-15 11:25:00','2022-09-11 16:00:00','Posada del Lunarejo','Cabalgata 2 ',76);
/*!40000 ALTER TABLE `salidaTuristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turista`
--

DROP TABLE IF EXISTS `turista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turista` (
  `ID` bigint(20) NOT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NACIONALITY` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `NICKNAME` (`NICKNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turista`
--

LOCK TABLES `turista` WRITE;
/*!40000 ALTER TABLE `turista` DISABLE KEYS */;
INSERT INTO `turista` VALUES (1,'1927-02-23','mirtha.legrand.ok@hotmail.com.ar','Martínez','Argentina','Rosa María','lachiqui'),(2,'1926-04-21','isabelita@thecrown.co.uk','Windsor ','Reino Unido','Elizabeth','isabelita'),(3,'1937-12-31','anibal@fing.edu.uy ','Lecter ','Lituania','Anibal ','anibal '),(4,'1990-04-15','e.waston@gmail.com','Waston','Reino Unido','Emma','waston'),(5,'1971-07-30','suavemente@hotmail.com','Lacio','Estados Unidos de América','Elvis','elelvis'),(6,'2004-02-19','eleven11@gmail.com','Once','España','Eleven','eleven11'),(7,'1999-05-01','bobesponja@nickelodeon.com','Esponja','Japón','Bob','bobesponja'),(8,'1976-04-11','eltony@manya.org.uy','Pacheco','Uruguay','Antonio','tony'),(9,'1976-03-17','chino@trico.org.uy','Recoba','Uruguay','Alvaro','chino'),(10,'1922-02-07','johann.sebastian@gmail.com','Mastropiero','Austria','Johann Sebastian','mastropiero');
/*!40000 ALTER TABLE `turista` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-14 12:25:06
