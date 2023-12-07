-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-12-2023 a las 15:45:51
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `connu_bd`
--
CREATE DATABASE IF NOT EXISTS `connu_bd` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `connu_bd`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `registrarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarUsuario` (IN `nom` VARCHAR(100), IN `cont` VARCHAR(30), IN `mail` VARCHAR(90), IN `sex` INT(2), IN `tUsuario` INT(2))   BEGIN

    INSERT INTO usuario (nombre, contrasena, correo, sexo_idsexo, tipo_idtipo, registro)
        VALUES (nom, cont, mail, sex,tUsuario, NOW());

end$$

DROP PROCEDURE IF EXISTS `ver_sexo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_sexo` ()   BEGIN
    SELECT * FROM tiposexo order by nombre DESC;
end$$

DROP PROCEDURE IF EXISTS `ver_tipoPublicacion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_tipoPublicacion` ()   BEGIN
    SELECT * FROM tipopublicacion order by nombre DESC;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
CREATE TABLE `publicacion` (
  `idpublicacion` int(4) NOT NULL,
  `contenido` varchar(250) NOT NULL,
  `img1` varchar(150) NOT NULL,
  `usuario_idusuario` int(3) NOT NULL,
  `tipo_idtipo` int(2) NOT NULL,
  `registro` datetime NOT NULL,
  `likes` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`idpublicacion`, `contenido`, `img1`, `usuario_idusuario`, `tipo_idtipo`, `registro`, `likes`) VALUES
(3, 'Contenido del post', '', 1, 1, '2023-12-06 11:01:49', 0),
(5, 'agregado', '', 4, 1, '2023-12-06 13:14:08', 0),
(7, 'ohno', '', 4, 1, '2023-12-06 15:56:45', 0),
(9, 'Tambien busco', '', 11, 1, '2023-12-06 17:12:31', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipopublicacion`
--

DROP TABLE IF EXISTS `tipopublicacion`;
CREATE TABLE `tipopublicacion` (
  `idtipopublicacion` int(2) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `st` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `tipopublicacion`
--

INSERT INTO `tipopublicacion` (`idtipopublicacion`, `nombre`, `st`) VALUES
(1, 'Proveedor de servicios', 1),
(2, 'Buscador de servicios', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposexo`
--

DROP TABLE IF EXISTS `tiposexo`;
CREATE TABLE `tiposexo` (
  `idtiposexo` int(2) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `st` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `tiposexo`
--

INSERT INTO `tiposexo` (`idtiposexo`, `nombre`, `st`) VALUES
(1, 'Femenino', 1),
(2, 'Masculino', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
CREATE TABLE `tipousuario` (
  `idtipousuario` int(2) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `st` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`idtipousuario`, `nombre`, `st`) VALUES
(1, 'General', 1),
(2, 'Prestador', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idusuario` int(3) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contrasena` varchar(30) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `sexo_idsexo` int(2) NOT NULL,
  `tipo_idtipo` int(2) NOT NULL,
  `registro` date NOT NULL,
  `st` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombre`, `contrasena`, `correo`, `sexo_idsexo`, `tipo_idtipo`, `registro`, `st`) VALUES
(1, 'Polar', '123', 'ana@live.com', 1, 1, '2023-12-04', 1),
(4, 'prueba1', '123', 'a@live.com', 2, 1, '2023-12-04', 1),
(5, 'evelyn', '222', 'eve@live.com', 1, 1, '2023-12-04', 1),
(6, 'esmeralda', '4321', 'esme@live.com', 1, 1, '2023-12-04', 1),
(9, 'prueba proveedor', '123', 'prb@ucc.mx', 2, 2, '2023-12-05', 1),
(10, 'norton', '12345678', 'norton@ucc.mx', 2, 2, '2023-12-06', 1),
(11, 'sacnicte cruz', '12345678', 'sacnic8@gmail.com', 1, 1, '2023-12-06', 1),
(12, 'lola', '12345678', 'lola@ucc.mx', 1, 2, '2023-12-06', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD PRIMARY KEY (`idpublicacion`),
  ADD KEY `idusuario` (`usuario_idusuario`),
  ADD KEY `idtipo` (`tipo_idtipo`);

--
-- Indices de la tabla `tipopublicacion`
--
ALTER TABLE `tipopublicacion`
  ADD PRIMARY KEY (`idtipopublicacion`);

--
-- Indices de la tabla `tiposexo`
--
ALTER TABLE `tiposexo`
  ADD PRIMARY KEY (`idtiposexo`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`idtipousuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `sexo_idsexo` (`sexo_idsexo`),
  ADD KEY `tipo_idtipo` (`tipo_idtipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `idpublicacion` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tipopublicacion`
--
ALTER TABLE `tipopublicacion`
  MODIFY `idtipopublicacion` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tiposexo`
--
ALTER TABLE `tiposexo`
  MODIFY `idtiposexo` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `idtipousuario` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD CONSTRAINT `publicacion_ibfk_1` FOREIGN KEY (`tipo_idtipo`) REFERENCES `tipopublicacion` (`idtipopublicacion`),
  ADD CONSTRAINT `publicacion_ibfk_2` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`sexo_idsexo`) REFERENCES `tiposexo` (`idtiposexo`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`tipo_idtipo`) REFERENCES `tipousuario` (`idtipousuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
