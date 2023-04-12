CREATE DATABASE  IF NOT EXISTS `producto3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `producto3`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: producto3
-- ------------------------------------------------------
-- Server version	8.0.29

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  `nif` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tipo` enum('ESTANDARD','PREMIUM') DEFAULT NULL,
  `tasaCliente` float DEFAULT NULL,
  `descuentoCliente` float DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
CREATE TABLE `articulos` (
  `id_articulo` int NOT NULL AUTO_INCREMENT,
  `codigo_articulo` varchar(8) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `precio_venta` float DEFAULT NULL,
  `gastos_envio` float DEFAULT NULL,
  `tiempo_preparacion` int DEFAULT NULL,
  PRIMARY KEY (`id_articulo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
INSERT INTO `clientes` VALUES (1,'Jordi','Font','C/Rocafort 25','12345678A','jordi@gmail.com','ESTANDARD',0,0),(2,'Manuel','Forge','C/Marina','44444444A','manuel@gmail.com','PREMIUM',20,30),(3,'Marta','Garcia','C/Calabria','123465322X','marta@gmail.com','ESTANDARD',0,0),(4,'Oscar','Mayer','C/Tormenta','44444444B','oscar@gmail.com','PREMIUM',20,30);
UNLOCK TABLES;
-- Dump completed on 2023-04-07 14:43:53
