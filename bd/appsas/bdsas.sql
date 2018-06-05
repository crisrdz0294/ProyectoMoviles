-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-06-2018 a las 17:58:12
-- Versión del servidor: 10.1.32-MariaDB
-- Versión de PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdsas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcalificacion`
--

CREATE TABLE `tbcalificacion` (
  `idcalificacion` int(11) NOT NULL,
  `comentario` text NOT NULL,
  `calificacion` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `idservicio` int(11) NOT NULL,
  `cedulausuario` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbcalificacion`
--

INSERT INTO `tbcalificacion` (`idcalificacion`, `comentario`, `calificacion`, `fecha`, `idservicio`, `cedulausuario`) VALUES
(1, 'el mejor servicio que pude encontrar', 11, '2018-06-04', 2, '702270570');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbimagen`
--

CREATE TABLE `tbimagen` (
  `idimagen` int(11) NOT NULL,
  `imagen` bit(64) NOT NULL,
  `idservicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbimagen`
--

INSERT INTO `tbimagen` (`idimagen`, `imagen`, `idservicio`) VALUES
(1, b'0000000000000000000000000000000000000000000000000000000000000011', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbservicio`
--

CREATE TABLE `tbservicio` (
  `idservicio` int(11) NOT NULL,
  `nombreservicio` varchar(200) NOT NULL,
  `descripcionservicio` text NOT NULL,
  `ubicacionservicio` varchar(100) NOT NULL,
  `estadoservicio` tinyint(4) NOT NULL,
  `precioservicio` double NOT NULL,
  `tiposervicio` int(11) NOT NULL,
  `cedulausuario` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbservicio`
--

INSERT INTO `tbservicio` (`idservicio`, `nombreservicio`, `descripcionservicio`, `ubicacionservicio`, `estadoservicio`, `precioservicio`, `tiposervicio`, `cedulausuario`) VALUES
(2, 'El mejor servicio', 'El mejor servicio de todos los servicios', '80,80', 1, 1111, 1, '702270570');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuario`
--

CREATE TABLE `tbusuario` (
  `cedula` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(200) NOT NULL,
  `correo` varchar(200) NOT NULL,
  `tipo` int(11) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `clave` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbusuario`
--

INSERT INTO `tbusuario` (`cedula`, `nombre`, `apellidos`, `correo`, `tipo`, `telefono`, `clave`) VALUES
('12345678', 'Administrador', 'Administrador', 'admin@gmail.com', 1, '88888888', '12345'),
('207270826', 'Jose Ignacio', 'Alfaro Rodriguez', 'nacho@gmail.com', 3, '12345678', 'abcd1234'),
('702270570', 'Cristopher', 'Rodriguez', 'cris@gmail.com', 2, '12345678', '12345a');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbcalificacion`
--
ALTER TABLE `tbcalificacion`
  ADD PRIMARY KEY (`idcalificacion`),
  ADD KEY `idservicio` (`idservicio`),
  ADD KEY `cedulausuario` (`cedulausuario`);

--
-- Indices de la tabla `tbimagen`
--
ALTER TABLE `tbimagen`
  ADD PRIMARY KEY (`idimagen`),
  ADD KEY `idservicio` (`idservicio`);

--
-- Indices de la tabla `tbservicio`
--
ALTER TABLE `tbservicio`
  ADD PRIMARY KEY (`idservicio`),
  ADD KEY `cedulausuario` (`cedulausuario`);

--
-- Indices de la tabla `tbusuario`
--
ALTER TABLE `tbusuario`
  ADD PRIMARY KEY (`cedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbcalificacion`
--
ALTER TABLE `tbcalificacion`
  MODIFY `idcalificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbimagen`
--
ALTER TABLE `tbimagen`
  MODIFY `idimagen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbservicio`
--
ALTER TABLE `tbservicio`
  MODIFY `idservicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbcalificacion`
--
ALTER TABLE `tbcalificacion`
  ADD CONSTRAINT `tbcalificacion_ibfk_1` FOREIGN KEY (`idservicio`) REFERENCES `tbservicio` (`idservicio`),
  ADD CONSTRAINT `tbcalificacion_ibfk_2` FOREIGN KEY (`cedulausuario`) REFERENCES `tbusuario` (`cedula`);

--
-- Filtros para la tabla `tbimagen`
--
ALTER TABLE `tbimagen`
  ADD CONSTRAINT `tbimagen_ibfk_1` FOREIGN KEY (`idservicio`) REFERENCES `tbservicio` (`idservicio`);

--
-- Filtros para la tabla `tbservicio`
--
ALTER TABLE `tbservicio`
  ADD CONSTRAINT `tbservicio_ibfk_1` FOREIGN KEY (`cedulausuario`) REFERENCES `tbusuario` (`cedula`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
