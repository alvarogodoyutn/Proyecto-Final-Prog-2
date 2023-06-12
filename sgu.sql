-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-06-2023 a las 02:09:27
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sgu`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `alu_dni` int(11) NOT NULL,
  `alu_nombre` varchar(45) DEFAULT NULL,
  `alu_apellido` varchar(45) DEFAULT NULL,
  `alu_fec_nac` date DEFAULT NULL,
  `alu_domicilio` varchar(45) DEFAULT NULL,
  `alu_telefono` varchar(45) DEFAULT NULL,
  `alu_insc_cod` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`alu_dni`, `alu_nombre`, `alu_apellido`, `alu_fec_nac`, `alu_domicilio`, `alu_telefono`, `alu_insc_cod`) VALUES
(38155023, 'Sofia', 'Romero', '2023-06-30', 'Mendoza', '15261487912336', NULL),
(41256847, 'Tomas', 'Fernandez', '2023-06-04', 'Mendoza', '15261258974859', NULL),
(41264781, 'Valentin', 'Pereira', '2023-06-22', 'Mendoza', '15263487989792', NULL),
(41266350, 'Martin', 'Mac Allister', '1999-06-30', 'Mendoza', '152634588448', NULL),
(41568715, 'Lautaro', 'Rivero', '2023-06-09', 'Mendoza', '15236481795214', NULL),
(42158788, 'Pablo', 'Duarte', '2000-02-22', 'Mendoza', '15261489333200', NULL),
(42687500, 'Mia', 'Messina', '2023-06-03', 'Mendoza', '15263428512696', NULL),
(43512009, 'Lucas', 'Vazquez', '2001-03-15', 'Mendoza', '152614312589', NULL),
(43689005, 'Juliana', 'Alvarez', '2023-06-18', 'Mendoza', '15263487912533', NULL),
(44212367, 'Mariana', 'Suarez', '2002-09-13', 'Mendoza', '152634879950', NULL),
(45881512, 'Paulo', 'Gimenez', '2023-06-23', 'Mendoza', '152641871561', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `car_cod` int(11) NOT NULL,
  `car_nombre` varchar(45) DEFAULT NULL,
  `car_duracion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursado`
--

CREATE TABLE `cursado` (
  `cur_alu_dni` int(11) NOT NULL,
  `cur_mat_cod` int(11) NOT NULL,
  `cur_nota` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `cursado`
--

INSERT INTO `cursado` (`cur_alu_dni`, `cur_mat_cod`, `cur_nota`) VALUES
(38155023, 36, 6),
(41256847, 3697, 7),
(41264781, 1554, 10),
(41264781, 5012, 8),
(41266350, 1569, 8),
(41266350, 5012, 5),
(41568715, 36, 9),
(42158788, 1569, 8),
(42158788, 5012, 8),
(42687500, 36, 10),
(42687500, 2036, 8),
(43512009, 36, 7),
(43689005, 2036, 10),
(44212367, 3697, 10),
(45881512, 2036, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `insc_cod` int(11) NOT NULL,
  `insc_nombre` varchar(45) DEFAULT NULL,
  `insc_fecha` date DEFAULT NULL,
  `insc_car_cod` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `mat_cod` int(11) NOT NULL,
  `mat_nombre` varchar(45) DEFAULT NULL,
  `mat_prof_dni` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`mat_cod`, `mat_nombre`, `mat_prof_dni`) VALUES
(36, 'Termodinamica', 29887458),
(1554, 'Analisis 1', 21154267),
(1569, 'Analisis 2', 18153645),
(2036, 'Fisica 2', 29887458),
(3697, 'Fisica 1', 23614741),
(5012, 'Algebra', 26114871);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `prof_dni` int(11) NOT NULL,
  `prof_nombre` varchar(45) DEFAULT NULL,
  `prof_apellido` varchar(45) DEFAULT NULL,
  `prof_fec_nac` varchar(45) DEFAULT NULL,
  `prof_domicilio` varchar(45) DEFAULT NULL,
  `prof_telefono` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`prof_dni`, `prof_nombre`, `prof_apellido`, `prof_fec_nac`, `prof_domicilio`, `prof_telefono`) VALUES
(18153645, 'Graciela', 'Pizzi', '2023-06-01', 'Mendoza', '152613598744789'),
(18741536, 'Lucas', 'Patanian', '2023-06-17', 'San Luis', '15263458978415'),
(21154267, 'Rodolfo', 'Matterazzi', '2023-06-09', 'San Juan', '15263487197815'),
(23614741, 'Alejandro', 'Paredes', '2023-06-08', 'Mendoza', '15261478945621'),
(26114871, 'Matias', 'Vasquez', '2023-06-18', 'Mendoza', '15261487959825'),
(29887458, 'Javier', 'Echeverria', '2023-06-30', 'Mendoza', '15261874956878'),
(31546812, 'Juan Fernando', 'Tonali', '2023-06-05', 'Capital Federal', '15263478914566');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`alu_dni`),
  ADD UNIQUE KEY `alu_dni_UNIQUE` (`alu_dni`),
  ADD KEY `alu_dni` (`alu_dni`);

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`car_cod`);

--
-- Indices de la tabla `cursado`
--
ALTER TABLE `cursado`
  ADD PRIMARY KEY (`cur_alu_dni`,`cur_mat_cod`),
  ADD KEY `FK_materia_idx` (`cur_mat_cod`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`insc_cod`),
  ADD KEY `FK_carrera_idx` (`insc_car_cod`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`mat_cod`),
  ADD KEY `FK_profesor_idx` (`mat_prof_dni`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`prof_dni`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `FK_inscripcion` FOREIGN KEY (`alu_insc_cod`) REFERENCES `inscripcion` (`insc_cod`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Filtros para la tabla `cursado`
--
ALTER TABLE `cursado`
  ADD CONSTRAINT `FK_cursado` FOREIGN KEY (`cur_mat_cod`) REFERENCES `materia` (`mat_cod`),
  ADD CONSTRAINT `FK_cursado_1` FOREIGN KEY (`cur_alu_dni`) REFERENCES `alumno` (`alu_dni`);

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD CONSTRAINT `FK_carrera` FOREIGN KEY (`insc_car_cod`) REFERENCES `carrera` (`car_cod`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Filtros para la tabla `materia`
--
ALTER TABLE `materia`
  ADD CONSTRAINT `FK_profesor` FOREIGN KEY (`mat_prof_dni`) REFERENCES `profesor` (`prof_dni`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
