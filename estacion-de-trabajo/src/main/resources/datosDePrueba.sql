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
  `PAQUETE_ID` bigint(20) NOT NULL,
  `ACTIVIDAD_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`PAQUETE_ID`,`ACTIVIDAD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAQUETE_ACTIVIDAD`
--

LOCK TABLES `PAQUETE_ACTIVIDAD` WRITE;
/*!40000 ALTER TABLE `PAQUETE_ACTIVIDAD` DISABLE KEYS */;
INSERT INTO `PAQUETE_ACTIVIDAD` VALUES (124,70),(124,71),(125,70),(125,73),(125,74);
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
INSERT INTO `PROVEEDOR_ACTIVIDAD` VALUES (11,70),(11,71),(11,1151),(11,1201),(11,1251),(11,1301),(11,1351),(11,1401),(11,1451),(11,1501),(11,1551),(11,1601),(11,1651),(12,75),(12,76),(13,73),(13,74);
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
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN_TABLE',1700);
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
  `ESTADOACTIVIDAD` varchar(30) DEFAULT NULL,
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
INSERT INTO `actividadTuristica` VALUES (70,'Rocha',800,'Festival gastronomico de productos locales en Rocha','3','2022-07-20 00:00:00','Degusta',52,'CONFIRMADA'),(71,'Rocha',500,'En el mes aniversario del Club Deportivo Union de Rocha te invitamos a una merienda deliciosa.','3','2022-07-21 00:00:00','Teatro con Sabores',52,'CONFIRMADA'),(73,'Colonia del Sacramento',400,'Con guia especializado y en varios idiomas. Varios circuitos posibles.','2','2022-08-01 00:00:00','Tour por Colonia del Sacramento',62,'CONFIRMADA'),(74,'Colonia del Sacramento',800,'Restaurante en la renovada Plaza de Toros con menu internacional','2','2022-08-01 00:00:00','Almuerzo en el Real de San Carlos',62,'CONFIRMADA'),(75,'Tranqueras',300,'Almuerzo en la Posada con ticket fijo. Menu que incluye bebida y postre casero.','2','2022-08-01 00:00:00','Almuerzo en Valle del Lunarejo',56,'CONFIRMADA'),(76,'Tranqueras',150,'Cabalgata por el area protegida. Varios recorridos para elegir.','2','2022-08-01 00:00:00','Cabalgata en Valle del Lunarejo',56,'CONFIRMADA'),(1651,'PDE',1,'fuera del caso de uso de confirmar/rechazar esta actividad no deberia ser listada, de ser asi falta un filtro en persistencia','1','2023-10-19 12:11:02','no listar esta actividad',15,'AGREGADA');
/*!40000 ALTER TABLE `actividadTuristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `ID` bigint(20) NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
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
INSERT INTO `departamento` VALUES (14,'Division Turismo de la Intendencia','Canelones','https://www.imcanelones.gub.uy/es'),(15,'Division Turismo de la Intendencia ','Maldonado','https://www.maldonado.gub.uy/'),(52,'La Organizacion de Gestion del Destino (OGD) Rocha es un ambito de articulacion publico','Rocha','www.turismorocha.gub.uy'),(53,'Division Turismo de la Intendencia','Treinta y Tres','https://treintaytres.gub.uy/'),(54,'Division Turismo de la Intendencia ','Cerro Largo','https://www.gub.uy/intendencia- cerro-largo'),(56,'Promociona e implementa proyectos e iniciati- vas sostenibles de interes','Rivera ','www.rivera.gub.uy/social/turismo/'),(57,'Division Turismo de la Intendencia','Artigas','http://www.artigas.gub.uy'),(58,'Division Turismo de la Intendencia ','Salto','https://www.salto.gub.u'),(59,'Division Turismo de la Intendencia','Paysandu','https://www.paysandu.gub.uy'),(60,'Division Turismo de la Intendencia','Rio Negro','https://www.rionegro.gub.uy'),(61,'Division Turismo de la Intendencia','Soriano ','https://www.soriano.gub.uy'),(62,'La propuesta del Departamento de Colonia divide en cuatro actos su espectaculo anual. Cada acto tiene su magia.','Colonia ','https://colonia.gub.uy/turismo'),(63,'Division Turismo de la Intenden','San Jose','https://sanjose.gub.uy'),(64,'Division Turismo de la Intendencia ','Flores','https://flores.gub.uy'),(65,'Division Turismo de la Intendencia ','Florida','http://www.florida.gub.uy'),(66,'Division Turismo de la Intendencia','Lavalleja','http://www.lavalleja.gub.uy'),(67,'Division Turismo de la Intendencia','Durazno','https://durazno.uy'),(68,'Division Turismo de la Intendencia ','Tacuarembo','https://tacuarembo.gub.uy'),(69,'Division Turismo de la Intendencia ','Montevideo','https://montevideo.gub.uy/areas- tematicas/turismo');
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
INSERT INTO `inscripcion` VALUES (113,3,2400,'2022-08-15 10:59:00',101,1),(114,5,4000,'2022-08-16 11:36:00',102,5),(115,3,1200,'2023-08-18 11:36:00',106,1),(116,1,400,'2023-08-19 11:39:00',106,2),(118,1,500,'2023-08-19 11:42:00',103,9),(119,10,5000,'2023-08-20 11:44:00',104,9),(120,2,1000,'2023-08-20 11:46:00',104,7),(121,1,500,'2023-08-21 11:47:00',104,3),(122,1,800,'2023-08-21 11:48:00',108,8),(1101,1,800,'2023-09-19 16:42:43',101,10),(1102,1,800,'2023-09-19 16:43:08',102,10);
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
  `ESTADOPAQUETE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquetes`
--

LOCK TABLES `paquetes` WRITE;
/*!40000 ALTER TABLE `paquetes` DISABLE KEYS */;
INSERT INTO `paquetes` VALUES (124,'Actividades para hacer en familia y disfrutar arte y gastronomia',20,'2022-08-10 00:00:00','Disfrutar Rocha',60,0),(125,'Paseos por el casco historico y se puede terminar con Almuerzo en la plaza de Toros',15,'2022-08-01 00:00:00','Un dia en Colonia ',45,0);
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
  `IMAGEPATH` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
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
INSERT INTO `proveedor` VALUES (11,'1970-09-14','Hola! me llamo Washington y soy el encargado\ndel portal de turismo del departamento de Rocha -\nUruguay','washington@turismorocha.gub.uy','tinyurl.com/3whe8372','Rocha','Washington','washington','$2a$10$Df10g0qs95whKNttjwT3wO33qwTtX8TwcW7KKc7fO2KeXmWAKkTvy','http://turismorocha.gub.uy/'),(12,'1965-06-27','Pablo es el presidente de la Sociedad de Fomento\nTuristico de Rivera (conocida como Socfomturriv)','eldiez@socfomturriv.org.uy','tinyurl.com/mu4jeas3','Bengoechea','Pablo','eldiez','$2a$10$tOSjoupkc0Zl9mgUER706.dJRXJka554UqDoWqcymzg1e7Wiago9K','http://wwww.socfomturriv.org.uy'),(13,'1990-12-31','Departamento de Turismo del Departamento de Colo-\nnia','meche@colonia.gub.uy','tinyurl.com/4hs4v9c5','Venn','Mercedes','meche','$2a$10$71wwQBYom.15YXRwA5SrkuBeeNuEcIXG1dtFMi6mPng4Ttj.HyaRm','https://colonia.gub.uy/turismo');
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
  `IMAGEPATH` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NACIONALITY` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
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
INSERT INTO `turista` VALUES (1,'1927-02-23','mirtha.legrand.ok@hotmail.com.ar','tinyurl.com/2e3s66tw','Martínez','Argentina','Rosa María','lachiqui','$2a$10$iW/cH.zYyl0.4weN8hrvzu1OIYzgIF8S0JPuw/03Z2m8RiOfLYzte'),(2,'1926-04-21','isabelita@thecrown.co.uk','tinyurl.com/ycy8mbrn','Windsor ','Reino Unido','Elizabeth','isabelita','$2a$10$s2GeW2iItIKM4UTsQq2hxexflu.dlIf4.2tNB62CjsCKEoUsIeIvS'),(3,'1937-12-31','anibal@fing.edu.uy','tinyurl.com/y2u3tybh','Lecter','Lituania','Anibal ','anibal','$2a$10$BJY6lprbg/0/0e/OSFM7tOh6WIM.1lT2Qns.4nu61ivO8aMY1cplC'),(4,'1990-04-15','e.waston@gmail.com','tinyurl.com/2p9ed8et','Waston','Reino Unido','Emma','waston','$2a$10$DFqTm1v0HeFNpp1krcTiYOwlo09q1IS1mNU56BmEHiXh8i/0ETNDC'),(5,'1971-07-30','suavemente@hotmail.com','tinyurl.com/mtwppxxz','Lacio','Estados Unidos de América','Elvis','elelvis','$2a$10$DOcO6NjJjP9YPFDnZ7.7PuybUOttK1mnvPutzxZMoXI61g8MCansq'),(6,'2004-02-19','eleven11@gmail.com','tinyurl.com/3ztpasya','Once','España','Eleven','eleven11','$2a$10$RVNEfiiwHBBe0yplqjho1.IxMjrjX3pDljNdRGyaY5DJv8eRxIpfq'),(7,'1999-05-01','bobesponja@nickelodeon.com','tinyurl.com/43zymcch','Esponja','Japón','Bob','bobesponja','$2a$10$qZmRTRPDgIYY3jclfFtKyuNNIWrugN1cbax5XLIYM6zkMy2WHGTX2'),(8,'1976-04-11','eltony@manya.org.uy','tinyurl.com/mr3a38w4','Pacheco','Uruguay','Antonio','tony','$2a$10$dqTh/eqBEVHOFEwBOBfYBOk0rqY7wUuilp81/JF4pCy25InKJl6Xa'),(9,'1976-03-17','chino@trico.org.uy','tinyurl.com/2b556k7t','Recoba','Uruguay','Alvaro','chino','$2a$10$wjEm9SfVIZF.fAbxV.DkaOVtQ1u6ZGlzVs7rA3xN0O85JXTQU7Uei'),(10,'1922-02-07','johann.sebastian@gmail.com','tinyurl.com/3mbeyawm','Mastropiero','Austria','Johann Sebastian','mastropiero','$2a$10$bZlKWARbmTqdd6DavGkTCeLgJyQKyfWHuXoA/H9KJznSpiT7Yfmf6');
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

-- Dump completed on 2023-10-03 20:08:53
