CREATE DATABASE  IF NOT EXISTS `ropa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ropa`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ropa
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) NOT NULL,
  `fechaAlta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuarioAlta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`telefono`),
  KEY `fk_usuario_alta_idx` (`usuarioAlta`),
  CONSTRAINT `fk_usuario_alta` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (62,'Leonardo','Peretti','Montevideo e Italia','03XXX-XXXXX','2016-05-14 01:30:53','Juan'),(63,'Leonardo','Peretti','Montevideo e Italia','03XXX-XXXXX','2016-05-14 01:31:51','Juan');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER 
	trigger_log_antes_agregar_cliente
BEFORE INSERT ON 
	cliente 
FOR EACH ROW
BEGIN  
    SET @permitirLog := (SELECT permitirLog FROM configuracion WHERE id = 1);
    SET @tipoLog := (SELECT tipoLog FROM configuracion WHERE id = 1);
    
    IF (@permitirLog AND @tipoLog = "DEBUG") THEN
		INSERT INTO
			eventlog(tipoDeEvento, descripcion, nivelLog)
		VALUES
			("CRUD", "Añadiendo nuevo cliente.", "DEBUG");
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER 
	trigger_log_despues_agregar_cliente
AFTER INSERT ON 
	cliente 
FOR EACH ROW
BEGIN  
    SET @permitirLog := (SELECT permitirLog FROM configuracion WHERE id = 1);
    SET @tipoLog := (SELECT tipoLog FROM configuracion WHERE id = 1);
    
    IF (@permitirLog AND @tipoLog = "DEBUG") THEN
		INSERT INTO
			eventlog(tipoDeEvento, descripcion, nivelLog)
		VALUES
			("CRUD", CONCAT("Nuevo cliente: '", new.id, " - ", new.nombre, " ", new.apellido ,"' agregado por el usuario: '", new.usuarioAlta, "'."), "DEBUG");
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permitirLog` tinyint(4) DEFAULT NULL,
  `tipoLog` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,1,'INFO');
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuota`
--

DROP TABLE IF EXISTS `cuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuota` (
  `idVenta` int(10) unsigned NOT NULL,
  `fecha` date NOT NULL,
  `importe` double DEFAULT NULL,
  PRIMARY KEY (`idVenta`,`fecha`),
  CONSTRAINT `c_vta` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuota`
--

LOCK TABLES `cuota` WRITE;
/*!40000 ALTER TABLE `cuota` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucion`
--

DROP TABLE IF EXISTS `devolucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fechaDevolucion` date DEFAULT NULL,
  `idVenta` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dev_vta_idx` (`idVenta`),
  CONSTRAINT `dev_vta` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucion`
--

LOCK TABLES `devolucion` WRITE;
/*!40000 ALTER TABLE `devolucion` DISABLE KEYS */;
/*!40000 ALTER TABLE `devolucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventlog`
--

DROP TABLE IF EXISTS `eventlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoDeEvento` varchar(30) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `fechaAlta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nivelLog` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventlog`
--

LOCK TABLES `eventlog` WRITE;
/*!40000 ALTER TABLE `eventlog` DISABLE KEYS */;
INSERT INTO `eventlog` VALUES (20,'CRUD','Añadiendo nuevo cliente.','2016-05-14 01:30:53','DEBUG'),(21,'CRUD','Nuevo cliente: \'62 - Leonardo Peretti\' agregado por el usuario: \'Juan\'.','2016-05-14 01:30:53','DEBUG');
/*!40000 ALTER TABLE `eventlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precio` (
  `fecha` datetime NOT NULL,
  `precio` varchar(45) DEFAULT NULL,
  `idProducto` varchar(12) NOT NULL,
  `usuarioAlta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fecha`,`idProducto`),
  KEY `prec_prod_idx` (`idProducto`),
  KEY `fk_precio_usuario_idx` (`usuarioAlta`),
  CONSTRAINT `fk_precio_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prec_prod` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio`
--

LOCK TABLES `precio` WRITE;
/*!40000 ALTER TABLE `precio` DISABLE KEYS */;
/*!40000 ALTER TABLE `precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id` varchar(12) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` int(11) DEFAULT '0',
  `idVenta` int(10) unsigned DEFAULT NULL,
  `idDevolucion` int(10) unsigned DEFAULT NULL,
  `fechaAlta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuarioAlta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prod_vta_idx` (`idVenta`),
  KEY `dev_producto_idx` (`idDevolucion`),
  KEY `fk_producto_usuario_idx` (`usuarioAlta`),
  CONSTRAINT `dev_producto` FOREIGN KEY (`idDevolucion`) REFERENCES `devolucion` (`id`),
  CONSTRAINT `fk_producto_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vta_producto` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nombreyApellido` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `tipoUsuario` int(11) DEFAULT '0',
  `fechaAlta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuarioAlta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usuario`),
  KEY `fk_usuario_usuario_idx` (`usuarioAlta`),
  CONSTRAINT `fk_usuario_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('Juan','Grasso','Juan Cruz Grasso','juangrasso@gmail.com',1,'2016-05-13 21:49:33',NULL),('Leo','Peretti','Leonardo Gabriel Peretti','leo.peretti5@gmail.com',1,'2016-05-13 21:49:33',NULL),('nicocda','nicolas23','Nicolas Giordano','nicocda09@gmail.com',1,'2016-05-13 21:49:33',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fechaVenta` date NOT NULL,
  `isReserva` tinyint(4) DEFAULT '0',
  `isPrestamo` tinyint(4) DEFAULT '0',
  `seña` double DEFAULT '0',
  `fechaCaducidad` date DEFAULT NULL,
  `idCliente` int(10) unsigned NOT NULL,
  `formaPago` int(11) NOT NULL,
  `importe` double DEFAULT NULL,
  `fechaAlta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuarioAlta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `asd_idx` (`idCliente`),
  KEY `fk_venta_usuario_idx` (`usuarioAlta`),
  CONSTRAINT `asd` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ropa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-14 16:24:08
