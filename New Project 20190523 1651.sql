-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.18-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dbregistrofx
--

CREATE DATABASE IF NOT EXISTS dbregistrofx;
USE dbregistrofx;

--
-- Definition of table `tbl_alumnos`
--

DROP TABLE IF EXISTS `tbl_alumnos`;
CREATE TABLE `tbl_alumnos` (
  `codigo_alumno` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `genero` varchar(1) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `codigo_centro` int(11) DEFAULT NULL,
  `codigo_carrera` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_alumno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_alumnos`
--

/*!40000 ALTER TABLE `tbl_alumnos` DISABLE KEYS */;
INSERT INTO `tbl_alumnos` (`codigo_alumno`,`nombre`,`apellido`,`edad`,`genero`,`fecha_ingreso`,`codigo_centro`,`codigo_carrera`) VALUES 
 (1,'Gustavo','Barahona',36,'M','2018-03-07',1,1);
/*!40000 ALTER TABLE `tbl_alumnos` ENABLE KEYS */;


--
-- Definition of table `tbl_carreras`
--

DROP TABLE IF EXISTS `tbl_carreras`;
CREATE TABLE `tbl_carreras` (
  `codigo_carrera` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_carrera` varchar(100) DEFAULT NULL,
  `cantidad_asignaturas` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_carrera`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_carreras`
--

/*!40000 ALTER TABLE `tbl_carreras` DISABLE KEYS */;
INSERT INTO `tbl_carreras` (`codigo_carrera`,`nombre_carrera`,`cantidad_asignaturas`) VALUES 
 (1,'Ingeniería en Sistemas',5);
/*!40000 ALTER TABLE `tbl_carreras` ENABLE KEYS */;


--
-- Definition of table `tbl_centros_estudio`
--

DROP TABLE IF EXISTS `tbl_centros_estudio`;
CREATE TABLE `tbl_centros_estudio` (
  `codigo_centro` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_centro_estudio` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo_centro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_centros_estudio`
--

/*!40000 ALTER TABLE `tbl_centros_estudio` DISABLE KEYS */;
INSERT INTO `tbl_centros_estudio` (`codigo_centro`,`nombre_centro_estudio`) VALUES 
 (1,'U. Israel');
/*!40000 ALTER TABLE `tbl_centros_estudio` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
