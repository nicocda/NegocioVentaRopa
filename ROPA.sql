CREATE DATABASE  IF NOT EXISTS `ropa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ropa`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ropa
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (65,'Nicolas','Giordano','Santa Fe 1016','3416678013','2016-07-31 18:02:56',NULL),(66,'Leo','peretto','asd','2323','2016-09-05 18:53:37',NULL);
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
INSERT INTO `precio` VALUES ('2016-07-31 00:00:18','89.9','B000001',NULL),('2016-07-31 00:00:18','273.52','RM00002',NULL),('2016-07-31 00:16:41','23.0','RH00002',NULL),('2016-07-31 00:16:41','223.0','RH00003',NULL),('2016-07-31 00:17:16','1223.0','RH00004',NULL),('2016-07-31 00:17:17','2323.0','RH00005',NULL),('2016-07-31 00:58:01','125.5','RH00001',NULL),('2016-07-31 00:58:02','540.0','RM00001',NULL),('2016-08-03 00:18:35','23.0','M000001',NULL),('2016-08-03 00:18:36','23.0','RN00001',NULL),('2016-09-05 00:39:58','123.0','RM00003',NULL),('2016-09-05 00:47:25','23.0','RH00006',NULL),('2016-09-05 00:47:26','232.0','RH00007',NULL),('2016-09-05 00:49:40','23.0','RH00008',NULL),('2016-09-05 00:49:41','23.0','RH00009',NULL),('2016-09-06 00:05:29','23.0','B000002',NULL),('2016-09-06 00:08:47','121.0','B000003',NULL),('2016-09-06 00:10:29','1.0','ZN00001',NULL),('2016-09-06 00:31:15','23.0','RH00010',NULL),('2016-09-06 00:31:16','112.0','RH00011',NULL),('2016-09-06 00:33:37','23.0','RH00012',NULL),('2016-09-06 00:33:37','11.0','RH00013',NULL),('2016-09-06 00:35:28','123.0','RH00014',NULL),('2016-09-06 00:35:28','233.0','RH00015',NULL),('2016-09-06 00:36:43','1212.0','RH00016',NULL),('2016-09-06 00:36:44','23.0','RH00017',NULL),('2016-09-06 00:40:38','23.0','RH00018',NULL),('2016-09-06 00:40:38','11.0','RH00019',NULL),('2016-09-06 00:41:16','23.0','RH00020',NULL),('2016-09-06 00:41:17','11.0','RH00021',NULL),('2016-09-06 00:42:57','123.0','RH00020',NULL),('2016-09-06 00:42:58','222.0','RH00021',NULL),('2016-09-06 00:55:17','232.0','RH00022',NULL),('2016-09-06 00:55:17','11.0','RH00023',NULL),('2016-09-06 00:56:24','232.0','RH00024',NULL),('2016-09-06 00:56:24','11.0','RH00025',NULL),('2016-09-10 00:38:23','232.0','RH00026',NULL),('2016-09-10 00:51:58','23.0','RH00027',NULL),('2016-09-10 00:52:17','22.0','RH00028',NULL),('2016-09-10 00:52:17','233.0','RH00029',NULL),('2016-09-10 00:52:46','1.0','RH00030',NULL),('2016-09-10 00:52:53','1.0','RH00031',NULL),('2016-09-10 00:52:53','23.0','RH00032',NULL),('2016-09-10 00:53:58','23.0','RH00033',NULL),('2016-09-10 00:54:07','111.0','RH00034',NULL),('2016-09-10 00:54:08','233.0','B000004',NULL),('2016-09-10 00:54:17','23.0','RH00035',NULL),('2016-09-10 00:56:07','23.0','RH00036',NULL),('2016-09-11 00:20:45','23.0','RH00037',NULL),('2016-09-11 00:20:45','112.0','RH00038',NULL),('2016-09-11 00:23:49','12.0','RH00039',NULL),('2016-09-11 00:27:51','23.0','B000005',NULL),('2016-09-11 00:31:51','23.0','ZH00001',NULL);
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
  `descripcion` varchar(150) DEFAULT NULL,
  `estado` int(11) DEFAULT '0',
  `idVenta` int(10) unsigned DEFAULT NULL,
  `idDevolucion` int(10) unsigned DEFAULT NULL,
  `fechaAlta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuarioAlta` varchar(45) DEFAULT NULL,
  `idSucursal` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prod_vta_idx` (`idVenta`),
  KEY `dev_producto_idx` (`idDevolucion`),
  KEY `fk_producto_usuario_idx` (`usuarioAlta`),
  KEY `producto_sucursal_idx` (`idSucursal`),
  CONSTRAINT `dev_producto` FOREIGN KEY (`idDevolucion`) REFERENCES `devolucion` (`id`),
  CONSTRAINT `fk_producto_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `producto_sucursal` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vta_producto` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('B000001','Collar de perlas australianas',0,2,NULL,'2016-07-31 18:00:17',NULL,1),('B000002','Lorem ipsum dolor sit amet, consectsdsadasdas',1,NULL,NULL,'2016-09-06 08:05:29',NULL,1),('B000003','Lorem ipsum dolor sit amet, consectetur a...',1,NULL,NULL,'2016-09-06 08:08:52',NULL,1),('B000004','asdasdas',0,4,NULL,'2016-09-10 21:54:07',NULL,1),('B000005','25156165 \'asad\'',1,NULL,NULL,'2016-09-11 02:27:51',NULL,1),('M000001','hola',0,17,NULL,'2016-08-03 16:18:35',NULL,1),('RH00001','asdasdas',0,2,NULL,'2016-07-31 17:58:01',NULL,1),('RH00002','asdsa',1,NULL,NULL,'2016-07-31 18:16:41',NULL,1),('RH00003','asdas',1,NULL,NULL,'2016-07-31 18:16:41',NULL,1),('RH00004','asdas',1,NULL,NULL,'2016-07-31 18:17:16',NULL,1),('RH00005','assd',1,NULL,NULL,'2016-07-31 18:17:16',NULL,1),('RH00006','test1',1,NULL,NULL,'2016-09-05 18:47:25',NULL,1),('RH00007','test2',1,NULL,NULL,'2016-09-05 18:47:26',NULL,1),('RH00008','asd',1,NULL,NULL,'2016-09-05 18:49:40',NULL,1),('RH00009','asdsa23+',1,NULL,NULL,'2016-09-05 18:49:41',NULL,1),('RH00010','asdsa',1,NULL,NULL,'2016-09-06 07:31:15',NULL,1),('RH00011','23',1,NULL,NULL,'2016-09-06 07:31:16',NULL,1),('RH00012','asd',1,NULL,NULL,'2016-09-06 07:33:36',NULL,1),('RH00013','zzx',1,NULL,NULL,'2016-09-06 07:33:37',NULL,1),('RH00014','sadas',1,NULL,NULL,'2016-09-06 07:35:27',NULL,1),('RH00015','asdas',1,NULL,NULL,'2016-09-06 07:35:28',NULL,1),('RH00016','asdasdsa',1,NULL,NULL,'2016-09-06 07:36:43',NULL,1),('RH00017','asdas',1,NULL,NULL,'2016-09-06 07:36:43',NULL,1),('RH00018','asd',1,NULL,NULL,'2016-09-06 07:40:37',NULL,1),('RH00019','asd',1,NULL,NULL,'2016-09-06 07:40:38',NULL,1),('RH00020','asd',1,NULL,NULL,'2016-09-06 07:41:16',NULL,1),('RH00021','asdasads',0,6,NULL,'2016-09-06 07:41:17',NULL,1),('RH00022','sadas',1,NULL,NULL,'2016-09-06 07:55:17',NULL,1),('RH00023','asas',1,NULL,NULL,'2016-09-06 07:55:17',NULL,1),('RH00024','asdas',1,NULL,NULL,'2016-09-06 07:56:24',NULL,1),('RH00025','asda',1,NULL,NULL,'2016-09-06 07:56:24',NULL,1),('RH00026','asda',1,NULL,NULL,'2016-09-10 21:38:23',NULL,1),('RH00027','asd23',1,NULL,NULL,'2016-09-10 21:51:58',NULL,1),('RH00028','asd',1,NULL,NULL,'2016-09-10 21:52:17',NULL,1),('RH00029','aaaa',1,NULL,NULL,'2016-09-10 21:52:17',NULL,1),('RH00030','1',1,NULL,NULL,'2016-09-10 21:52:46',NULL,1),('RH00031','as',0,8,NULL,'2016-09-10 21:52:53',NULL,1),('RH00032','asdas',0,9,NULL,'2016-09-10 21:52:53',NULL,1),('RH00033','asd',1,NULL,NULL,'2016-09-10 21:53:58',NULL,1),('RH00034','saasassa',1,NULL,NULL,'2016-09-10 21:54:07',NULL,1),('RH00035','23',1,NULL,NULL,'2016-09-10 21:54:17',NULL,1),('RH00036','asd',1,NULL,NULL,'2016-09-10 21:56:07',NULL,1),('RH00037','asdas',1,NULL,NULL,'2016-09-11 02:20:45',NULL,1),('RH00038','asd',1,NULL,NULL,'2016-09-11 02:20:45',NULL,1),('RH00039','asd',1,NULL,NULL,'2016-09-11 02:23:49',NULL,1),('RM00001','Jean Azul ThisWeek Talle 38',1,NULL,NULL,'2016-07-31 17:58:02',NULL,1),('RM00002','Remera Escotada verde Lacoste talle S',0,11,NULL,'2016-07-31 18:00:18',NULL,1),('RM00003','wed',0,10,NULL,'2016-09-05 19:39:58',NULL,1),('RN00001','asdas',0,10,NULL,'2016-08-03 16:18:35',NULL,1),('ZH00001','asdas \'B\'',1,NULL,NULL,'2016-09-11 02:31:50',NULL,1),('ZN00001','Lorem ipsum dolor sit amet, consectetur a...',0,7,NULL,'2016-09-06 08:10:29',NULL,1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) DEFAULT NULL,
  `localidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'San Martin 567','Rosario');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
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
INSERT INTO `tarjeta` VALUES (-1,65,-1,-1,1),(12,65,2,2,1),(211,65,2,2,1),(221,65,2,2,1),(1123,65,23,2,1),(1234,65,23,2,1),(1241,65,23,2,1),(1414,65,2,2,1),(2323,65,23,2,1),(21111,65,22,22,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipotarjeta`
--

LOCK TABLES `tipotarjeta` WRITE;
/*!40000 ALTER TABLE `tipotarjeta` DISABLE KEYS */;
INSERT INTO `tipotarjeta` VALUES (1,'CrediJURC'),(2,'Macro'),(3,'VISA');
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
  CONSTRAINT `fk_usuario_sucursal` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('Juan','Grasso','Juan Cruz Grasso','soygooglegrass@googl.com',2,'2016-08-07 18:48:03',NULL,1),('Leo','peretto','Leonardo Peretti','leoperetto@asd',3,'2016-09-10 21:27:48',NULL,1),('nicocda','nicolas23','Nicolas','asdas',3,'2016-07-30 00:00:00',NULL,1);
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
  CONSTRAINT `fk_venta_sucursal` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_tarjeta` FOREIGN KEY (`idTarjeta`) REFERENCES `tarjeta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`usuarioAlta`) REFERENCES `usuario` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,'2016-09-06',0,0,0,NULL,65,1,89.9,'2016-09-06 21:03:50',NULL,NULL,NULL),(2,'2016-09-08',0,0,0,NULL,65,3,35.6,'2016-09-08 20:01:52',NULL,NULL,NULL),(3,'2016-09-10',0,0,0,NULL,65,3,23,'2016-09-10 22:15:58',NULL,NULL,NULL),(4,'2016-09-10',0,0,0,NULL,65,3,233,'2016-09-10 22:17:00',NULL,NULL,NULL),(5,'2016-09-10',0,0,0,NULL,65,3,23,'2016-09-10 22:17:42',NULL,NULL,NULL),(6,'2016-09-10',0,0,0,NULL,65,3,222,'2016-09-10 22:18:44',NULL,NULL,NULL),(7,'2016-09-10',0,0,0,NULL,65,3,1,'2016-09-10 22:55:21',NULL,NULL,NULL),(8,'2016-09-10',0,0,0,NULL,65,3,1,'2016-09-10 23:02:52',NULL,NULL,NULL),(9,'2016-09-10',0,0,0,NULL,65,3,23,'2016-09-10 23:42:58',NULL,NULL,NULL),(10,'2016-09-10',0,0,0,NULL,65,3,100,'2016-09-10 23:51:12',NULL,NULL,NULL),(11,'2016-09-11',0,0,0,NULL,65,3,273.52,'2016-09-11 00:31:39',NULL,NULL,NULL),(17,'2016-09-11',0,0,0,NULL,65,3,23,'2016-09-11 01:08:18',NULL,1241,NULL);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-11 15:55:48
