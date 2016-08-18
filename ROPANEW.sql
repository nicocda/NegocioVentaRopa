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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (65,'Nicolas','Giordano','Santa Fe 1016','3416678013','2016-07-31 18:02:56',NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventlog`
--

LOCK TABLES `eventlog` WRITE;
/*!40000 ALTER TABLE `eventlog` DISABLE KEYS */;
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
INSERT INTO `precio` VALUES ('2016-07-31 00:00:18','89.9','B000001',NULL),('2016-07-31 00:00:18','273.52','RM00002',NULL),('2016-07-31 00:16:41','23.0','RH00002',NULL),('2016-07-31 00:16:41','223.0','RH00003',NULL),('2016-07-31 00:17:16','1223.0','RH00004',NULL),('2016-07-31 00:17:17','2323.0','RH00005',NULL),('2016-07-31 00:58:01','125.5','RH00001',NULL),('2016-07-31 00:58:02','540.0','RM00001',NULL),('2016-08-03 00:18:35','23.0','M000001',NULL),('2016-08-03 00:18:36','23.0','RN00001',NULL);
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
  `idSucursal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prod_vta_idx` (`idVenta`),
  KEY `dev_producto_idx` (`idDevolucion`),
  KEY `fk_producto_usuario_idx` (`usuarioAlta`),
  KEY `producto_sucursal_idx` (`idSucursal`),
  CONSTRAINT `dev_producto` FOREIGN KEY (`idDevolucion`) REFERENCES `devolucion` (`id`),
  CONSTRAINT `fk_producto_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `producto_sucursal` FOREIGN KEY (`idSucursal`) REFERENCES `sucursual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vta_producto` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('B000001','Collar de perlas australianas',1,NULL,NULL,'2016-07-31 18:00:17',NULL,NULL),('M000001','hola',1,NULL,NULL,'2016-08-03 16:18:35',NULL,NULL),('RH00001','Remera algodon verde talle \"M\"',1,NULL,NULL,'2016-07-31 17:58:01',NULL,NULL),('RH00002','asdsa',1,NULL,NULL,'2016-07-31 18:16:41',NULL,NULL),('RH00003','asdas',1,NULL,NULL,'2016-07-31 18:16:41',NULL,NULL),('RH00004','asdas',1,NULL,NULL,'2016-07-31 18:17:16',NULL,NULL),('RH00005','assd',1,NULL,NULL,'2016-07-31 18:17:16',NULL,NULL),('RM00001','Jean Azul ThisWeek Talle 38',1,NULL,NULL,'2016-07-31 17:58:02',NULL,NULL),('RM00002','Remera Escotada verde Lacoste talle S',1,NULL,NULL,'2016-07-31 18:00:18',NULL,NULL),('RN00001','asdas',1,NULL,NULL,'2016-08-03 16:18:35',NULL,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursual`
--

DROP TABLE IF EXISTS `sucursual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursual` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) DEFAULT NULL,
  `localidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursual`
--

LOCK TABLES `sucursual` WRITE;
/*!40000 ALTER TABLE `sucursual` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarjeta` (
  `id` int(11) NOT NULL,
  `idCliente` int(10) unsigned NOT NULL,
  `cuotas` int(11) DEFAULT NULL,
  `nroCupon` int(11) DEFAULT NULL,
  `tipoTarjeta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tarjeta_cliente_idx` (`idCliente`),
  KEY `tipo_tarjeta_idx` (`tipoTarjeta`),
  CONSTRAINT `tarjeta_cliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_tarjeta` FOREIGN KEY (`tipoTarjeta`) REFERENCES `tipotarjeta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipotarjeta`
--

DROP TABLE IF EXISTS `tipotarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipotarjeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipotarjeta`
--

LOCK TABLES `tipotarjeta` WRITE;
/*!40000 ALTER TABLE `tipotarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipotarjeta` ENABLE KEYS */;
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
  `idSucursal` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario`),
  KEY `fk_usuario_usuario_idx` (`usuarioAlta`),
  KEY `fk_usuario_sucursal_idx` (`idSucursal`),
  CONSTRAINT `fk_usuario_sucursal` FOREIGN KEY (`idSucursal`) REFERENCES `sucursual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('nicocda','nicolas23','Nicolas','asdas',4,'2016-07-30 00:00:00',NULL,NULL);
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
  `idTarjeta` int(11) DEFAULT NULL,
  `idSucursal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `asd_idx` (`idCliente`),
  KEY `fk_venta_usuario_idx` (`usuarioAlta`),
  KEY `fk_venta_tarjeta_idx` (`idTarjeta`),
  KEY `fk_venta_sucursal_idx` (`idSucursal`),
  CONSTRAINT `asd` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_venta_sucursal` FOREIGN KEY (`idSucursal`) REFERENCES `sucursual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_tarjeta` FOREIGN KEY (`idTarjeta`) REFERENCES `tarjeta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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

-- Dump completed on 2016-08-07 19:02:59
