-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 26-Abr-2019 às 17:03
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aula`
--

CREATE TABLE `aula` (
  `ID_NF` int(11) NOT NULL,
  `ID_ITEM` int(11) NOT NULL,
  `COD_PROD` int(11) NOT NULL,
  `VALOR_UNIT` double NOT NULL,
  `QUANTIDADE` int(11) NOT NULL,
  `DESCONTO` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aula`
--

INSERT INTO `aula` (`ID_NF`, `ID_ITEM`, `COD_PROD`, `VALOR_UNIT`, `QUANTIDADE`, `DESCONTO`) VALUES
(1, 1, 1, 25, 10, 5),
(1, 2, 2, 13.5, 3, 0),
(1, 3, 3, 15, 2, 0),
(1, 4, 4, 10, 1, 0),
(1, 5, 5, 30, 1, 0),
(2, 2, 4, 10, 4, 0),
(2, 3, 5, 30, 7, 0),
(3, 1, 1, 25, 5, 0),
(3, 2, 4, 10, 4, 0),
(3, 3, 5, 30, 5, 0),
(3, 4, 2, 13.5, 7, 0),
(4, 1, 5, 30, 10, 15),
(4, 2, 4, 10, 12, 5),
(4, 3, 1, 25, 13, 5),
(4, 4, 2, 13.5, 15, 5),
(5, 1, 3, 15, 3, 0),
(5, 2, 5, 30, 6, 0),
(6, 1, 1, 25, 22, 15),
(6, 2, 3, 15, 25, 20),
(7, 1, 1, 25, 10, 3),
(7, 2, 2, 13.5, 10, 4),
(7, 3, 3, 15, 10, 4),
(7, 4, 5, 30, 10, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
