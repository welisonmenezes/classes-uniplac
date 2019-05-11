-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11-Maio-2019 às 19:04
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
-- Database: `projetozika`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE `estoque` (
  `ProdutoId` int(11) NOT NULL,
  `Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `estoque`
--

INSERT INTO `estoque` (`ProdutoId`, `Total`) VALUES
(1, 55),
(2, 80),
(3, 11),
(4, 56),
(5, 21),
(6, 65),
(7, 35),
(8, 76),
(9, 12),
(10, 61),
(11, 20),
(12, 7),
(13, 10),
(14, 21),
(15, 44),
(16, 12),
(17, 25),
(18, 43),
(19, 13),
(20, 70),
(21, 76),
(22, 27),
(23, 10),
(24, 20),
(25, 22),
(26, 15),
(27, 11),
(28, 10),
(29, 50);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE `fornecedores` (
  `Id` int(11) NOT NULL,
  `Cnpj` varchar(14) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `fornecedores`
--

INSERT INTO `fornecedores` (`Id`, `Cnpj`, `Nome`, `Telefone`, `Status`, `Created`) VALUES
(1, '11111111115667', 'Oficina do papel', '9911991199', 'Publish', '2019-03-27 00:00:00'),
(2, '22222222233444', 'Escritórios SA', '333222333', 'Publish', '2019-03-27 00:00:00'),
(3, '33333333333333', 'Papelaria Rosário', '4343434334', 'Publish', '2019-03-27 00:00:00'),
(4, '44444444444555', 'Depositoffice Ltda', '4444555566', 'Publish', '2019-03-27 00:00:00'),
(5, '55555555555333', 'Casa do Papel', '33224455', 'Publish', '2019-03-30 00:00:00'),
(6, '66666666663333', 'Staples.com', '333333333', 'Publish', '2019-03-31 00:00:00'),
(7, '77777777777333', 'Venda ESC SA', '23234545', 'Publish', '2019-04-02 00:00:00'),
(8, '99999999999999', 'VendaESCOnline', '222222222', 'Publish', '2019-04-18 00:00:00'),
(9, '88888888888888', 'Escolar.com', '32323232', 'Publish', '2019-04-20 00:00:00'),
(10, '10121444444555', 'Plast House', '21212323', 'Publish', '2019-04-29 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `notasfiscais`
--

CREATE TABLE `notasfiscais` (
  `Id` int(11) NOT NULL,
  `Numero` bigint(20) NOT NULL,
  `Serie` int(11) NOT NULL,
  `Valor` double(11,2) NOT NULL,
  `Data` date NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL,
  `FornecedorId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `notasfiscais`
--

INSERT INTO `notasfiscais` (`Id`, `Numero`, `Serie`, `Valor`, `Data`, `Status`, `Created`, `FornecedorId`) VALUES
(2, 222222, 22, 3.00, '2019-03-06', 'Publish', '2019-03-30 00:00:00', 2),
(3, 43443, 663, 45.00, '2018-03-07', 'Publish', '2019-03-30 00:00:00', 4),
(4, 76765554, 3221, 50.56, '2018-03-05', 'Publish', '2019-03-30 00:00:00', 3),
(5, 5645436, 3222, 32.00, '2019-03-04', 'Publish', '2019-03-31 00:00:00', 5),
(6, 533253, 323, 21.89, '2019-03-31', 'Publish', '2019-03-31 00:00:00', 6),
(7, 787878, 3234, 23.90, '2019-04-03', 'Publish', '2019-04-02 00:00:00', 7),
(8, 33333333, 2323, 33.33, '2019-04-03', 'Deleted', '2019-04-07 00:00:00', 1),
(9, 345798977, 54566, 4.00, '2019-04-08', 'Publish', '2019-04-08 00:00:00', 2),
(10, 333333333335557, 43556990, 22.22, '2019-04-09', 'Publish', '2019-04-09 00:00:00', 7),
(11, 88878677878, 325534, 33.33, '2019-04-03', 'Publish', '2019-04-09 00:00:00', 5),
(12, 4343434343, 43434434, 43.44, '2019-04-02', 'Publish', '2019-04-20 00:00:00', 5),
(13, 32322332323332, 33434, 44.55, '2019-04-23', 'Publish', '2019-04-23 00:00:00', 2),
(14, 65676567, 6765, 30.00, '2019-04-29', 'Publish', '2019-04-29 00:00:00', 1),
(15, 3333333333444433, 2423323, 55.55, '2019-05-01', 'Publish', '2019-05-02 00:00:00', 6),
(16, 44333444333, 34343, 44.44, '2019-05-10', 'Publish', '2019-05-11 00:00:00', 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notasfiscaisprodutos`
--

CREATE TABLE `notasfiscaisprodutos` (
  `NotaFiscalId` int(11) NOT NULL,
  `ProdutoId` int(11) NOT NULL,
  `Valor` double(11,2) NOT NULL,
  `Quantidade` int(11) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `notasfiscaisprodutos`
--

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) VALUES
(2, 2, 3.44, 3, '2019-04-09 00:00:00'),
(2, 13, 2.00, 2, '2019-04-09 00:00:00'),
(3, 3, 6.00, 4, '2019-03-31 00:00:00'),
(3, 6, 2.50, 1, '2019-03-31 00:00:00'),
(3, 7, 1.00, 3, '2019-03-31 00:00:00'),
(3, 11, 8.90, 5, '2019-03-31 00:00:00'),
(4, 1, 2.00, 2, '2019-03-31 00:00:00'),
(4, 5, 3.50, 5, '2019-03-31 00:00:00'),
(4, 11, 1.00, 1, '2019-03-31 00:00:00'),
(4, 14, 2.00, 2, '2019-03-31 00:00:00'),
(5, 4, 2.00, 3, '2019-03-31 00:00:00'),
(5, 16, 4.90, 1, '2019-03-31 00:00:00'),
(5, 17, 4.50, 1, '2019-03-31 00:00:00'),
(6, 1, 2.00, 2, '2019-04-09 00:00:00'),
(6, 4, 1.00, 1, '2019-04-09 00:00:00'),
(6, 5, 1.00, 1, '2019-04-09 00:00:00'),
(6, 12, 1.70, 1, '2019-04-09 00:00:00'),
(6, 14, 0.97, 1, '2019-04-09 00:00:00'),
(6, 15, 0.90, 2, '2019-04-09 00:00:00'),
(6, 17, 4.00, 1, '2019-04-09 00:00:00'),
(6, 18, 2.00, 2, '2019-04-09 00:00:00'),
(7, 2, 3.33, 3, '2019-04-09 00:00:00'),
(7, 22, 2.22, 2, '2019-04-09 00:00:00'),
(8, 22, 1.11, 1, '2019-04-07 00:00:00'),
(9, 2, 2.22, 41, '2019-04-09 00:00:00'),
(9, 17, 2.22, 20, '2019-04-09 00:00:00'),
(9, 22, 1.22, 22, '2019-04-09 00:00:00'),
(10, 2, 0.99, 30, '2019-04-10 00:00:00'),
(10, 4, 1.11, 12, '2019-04-10 00:00:00'),
(10, 23, 10.00, 10, '2019-04-10 00:00:00'),
(11, 24, 3.99, 20, '2019-04-20 00:00:00'),
(12, 4, 1.11, 11, '2019-04-20 00:00:00'),
(12, 25, 2.22, 22, '2019-04-20 00:00:00'),
(13, 26, 4.44, 20, '2019-04-23 00:00:00'),
(14, 24, 3.00, 30, '2019-04-29 00:00:00'),
(15, 7, 1.22, 20, '2019-05-02 00:00:00'),
(15, 29, 1.11, 50, '2019-05-02 00:00:00'),
(16, 1, 13.33, 10, '2019-05-11 00:00:00'),
(16, 10, 33.33, 30, '2019-05-11 00:00:00'),
(16, 17, 2.22, 5, '2019-05-11 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `Id` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL,
  `UsuarioId` int(11) NOT NULL,
  `AlmoxarifeId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pedidos`
--

INSERT INTO `pedidos` (`Id`, `Status`, `Created`, `UsuarioId`, `AlmoxarifeId`) VALUES
(6, 'Negado', '2019-03-08 00:00:00', 5, NULL),
(7, 'Pendente', '2019-04-01 00:00:00', 6, NULL),
(8, 'Aguardando entrega', '2019-02-18 00:00:00', 8, 5),
(9, 'Aguardando entrega', '2019-04-01 00:00:00', 8, 5),
(10, 'Aguardando entrega', '2019-04-02 00:00:00', 7, NULL),
(11, 'Finalizado', '2019-04-02 00:00:00', 5, 5),
(12, 'Finalizado', '2019-02-07 00:00:00', 5, NULL),
(13, 'Finalizado', '2019-02-18 00:00:00', 5, NULL),
(14, 'Negado', '2018-04-03 00:00:00', 5, NULL),
(15, 'Aguardando entrega', '2019-04-02 00:00:00', 5, NULL),
(16, 'Aguardando entrega', '2019-04-05 00:00:00', 5, 5),
(17, 'Deleted', '2019-04-08 00:00:00', 5, NULL),
(18, 'Pendente', '2019-04-09 00:00:00', 7, NULL),
(19, 'Pendente', '2019-04-10 00:00:00', 9, 5),
(20, 'Aguardando entrega', '2019-04-19 00:00:00', 5, 5),
(21, 'Pendente', '2019-04-20 00:00:00', 7, NULL),
(22, 'Pendente', '2019-04-22 00:00:00', 9, NULL),
(23, 'Pendente', '2019-04-22 00:00:00', 6, NULL),
(24, 'Aguardando entrega', '2019-04-23 00:00:00', 6, 5),
(25, 'Negado', '2019-04-25 00:00:00', 10, 5),
(26, 'Finalizado', '2019-04-25 00:00:00', 10, 9),
(27, 'Pendente', '2019-04-25 00:00:00', 11, NULL),
(28, 'Pendente', '2019-04-29 00:00:00', 11, NULL),
(29, 'Pendente', '2019-05-11 00:00:00', 10, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidosprodutos`
--

CREATE TABLE `pedidosprodutos` (
  `Id` int(11) NOT NULL,
  `PedidoId` int(11) NOT NULL,
  `ProdutoId` int(11) NOT NULL,
  `QuantidadeSolicitada` int(11) NOT NULL,
  `QuantidadeAprovada` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pedidosprodutos`
--

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) VALUES
(5, 6, 1, 3, 3),
(6, 6, 7, 2, 2),
(7, 6, 6, 1, 1),
(8, 7, 4, 1, 1),
(9, 7, 14, 4, 4),
(10, 8, 2, 2, 2),
(11, 8, 4, 3, 3),
(14, 10, 17, 4, 1),
(15, 10, 12, 1, 1),
(16, 10, 11, 2, 2),
(17, 10, 1, 2, 2),
(18, 10, 4, 4, 0),
(19, 11, 21, 2, 0),
(20, 11, 20, 3, 0),
(21, 11, 2, 1, 1),
(22, 12, 5, 3, 3),
(23, 12, 1, 1, 1),
(24, 12, 15, 1, 1),
(25, 13, 21, 1, 1),
(26, 13, 7, 3, 3),
(27, 14, 3, 4, 0),
(28, 14, 4, 1, 0),
(29, 14, 12, 2, 0),
(30, 14, 11, 1, 0),
(40, 15, 1, 4, 1),
(41, 15, 5, 4, 2),
(42, 15, 2, 4, 3),
(43, 9, 3, 2, 2),
(44, 9, 11, 1, 1),
(45, 9, 8, 1, 1),
(46, 16, 17, 14, 5),
(47, 16, 2, 1, 1),
(48, 17, 20, 4, 4),
(49, 17, 5, 2, 2),
(50, 17, 12, 1, 1),
(51, 17, 22, 6, 6),
(52, 17, 15, 1, 1),
(53, 17, 2, 1, 1),
(58, 18, 15, 1, 1),
(59, 18, 12, 2, 2),
(60, 18, 18, 1, 1),
(61, 18, 13, 1, 1),
(62, 18, 4, 3, 3),
(63, 19, 5, 3, 3),
(64, 19, 23, 1, 1),
(65, 19, 10, 1, 1),
(66, 20, 3, 1, 10),
(67, 21, 25, 4, 4),
(68, 21, 15, 2, 2),
(69, 8, 12, 3, 3),
(70, 9, 25, 1, 1),
(71, 22, 25, 4, 4),
(72, 22, 2, 1, 1),
(73, 22, 10, 2, 2),
(74, 23, 4, 1, 1),
(75, 23, 9, 1, 1),
(76, 23, 18, 5, 5),
(77, 23, 21, 1, 1),
(78, 24, 26, 6, 5),
(79, 25, 7, 1, 0),
(80, 25, 9, 3, 0),
(81, 25, 13, 4, 0),
(82, 26, 11, 1, 1),
(83, 26, 12, 4, 4),
(84, 27, 1, 5, 5),
(85, 27, 9, 1, 1),
(86, 27, 6, 3, 3),
(87, 27, 23, 1, 1),
(88, 27, 19, 1, 1),
(89, 27, 18, 1, 1),
(90, 27, 24, 1, 1),
(91, 27, 25, 3, 3),
(92, 28, 24, 4, NULL),
(93, 29, 24, 1, 1),
(94, 29, 29, 3, 3),
(95, 29, 9, 1, 1),
(96, 29, 17, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Unidade` varchar(45) NOT NULL,
  `Descricao` varchar(255) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`Id`, `Nome`, `Unidade`, `Descricao`, `Status`, `Created`) VALUES
(1, 'Caneta Bic Preta', 'Unidade', 'Caneta Bic preta esferográfica', 'Publish', '2019-03-26 00:00:00'),
(2, 'Papel Ofício A4', 'Pacote', 'Papel ofício A4 pct 100 unidades', 'Publish', '2019-03-26 00:00:00'),
(3, 'Lápis 6b', 'Unidade', 'Lápis 6b verde', 'Publish', '2018-11-13 00:00:00'),
(4, 'Grampos de papel', 'Caixa', 'Grampos de papel', 'Publish', '2019-02-06 00:00:00'),
(5, 'Fita Adesiva', 'Unidade', 'Fita adesiva 12mm x 30m', 'Publish', '2018-12-11 00:00:00'),
(6, 'Clips Coloridos', 'Caixa', 'Clips coloridos jumbo caixa com 200 unidades', 'Publish', '2019-03-27 00:00:00'),
(7, 'Post-It Amarelo', 'Pacote', 'Post-It 4 bocos amarelo', 'Publish', '2019-03-27 00:00:00'),
(8, 'Corretivo 18ml', 'Unidade', 'Líquido corretivo office blanc', 'Publish', '2018-12-17 00:00:00'),
(9, 'Tinta Carimbo', 'Litro', 'Tinta para carimbo litro', 'Publish', '2019-03-27 00:00:00'),
(10, 'Lapiseira', 'Pacote', 'Lapiseira pacote 3 unidades', 'Publish', '2019-03-27 00:00:00'),
(11, 'Pasta Ofício', 'Unidade', 'Pasta AZ ofício economic', 'Publish', '2019-03-27 00:00:00'),
(12, 'Borracha Plástica', 'Unidade', 'Borracha plástica stapless', 'Publish', '2018-12-10 00:00:00'),
(13, 'Marcatexto Verde', 'Unidade', 'Marca texto verde stapless', 'Publish', '2019-03-27 00:00:00'),
(14, 'Marcatexto Azul', 'Unidade', 'Marcatexto azul stapless', 'Publish', '2019-03-27 00:00:00'),
(15, 'Régua 30cm', 'Unidade', 'Régua 30cm transparente', 'Publish', '2018-12-20 00:00:00'),
(16, 'Alfinete Mapa', 'Pacote', 'Alfinetes para mapa amarelo', 'Publish', '2019-03-30 00:00:00'),
(17, 'Porta Canetas', 'Unidade', 'Porta lápis e caneta simples', 'Publish', '2018-11-12 00:00:00'),
(18, 'Saco Plástico Ofício', 'Pacote', 'Saco plástico ofício sem furos. Pacote com 100 unidades', 'Publish', '2019-02-11 00:00:00'),
(19, 'Mouse Dell', 'Unidade', 'Mouse sem fio Dell', 'Publish', '2019-04-02 00:00:00'),
(20, 'Caneta Compact Azul', 'Unidade', 'Caneta Compact azul ponta fina', 'Publish', '2019-01-08 00:00:00'),
(21, 'Cola 1kg Tenaz', 'Quilo', 'Colar Tenaz 1kg', 'Publish', '2019-03-11 00:00:00'),
(22, 'Tesoura Tramontina', 'Unidade', 'Tesoura Tramontina inox', 'Publish', '2019-04-07 00:00:00'),
(23, 'Calculadora de Mesa', 'Unidade', 'Calculadora de mesa Maxprint', 'Publish', '2019-04-09 00:00:00'),
(24, 'Agenda 2019', 'Unidade', 'Agenda diária 2019', 'Publish', '2018-12-24 00:00:00'),
(25, 'Pilha AA', 'Pacote', 'Pilha AA pequena', 'Publish', '2019-04-20 00:00:00'),
(26, 'Phone de Ouvido', 'Unidade', 'Phone de ouvido Sansumg', 'Publish', '2019-04-23 00:00:00'),
(27, 'Prancheta', 'Unidade', 'Prancheta madeira A4', 'Publish', '2019-04-29 00:00:00'),
(28, 'Porta Moeda', 'Unidade', 'Porta moeda importado', 'Publish', '2019-05-02 00:00:00'),
(29, 'Cartolina Branca', 'Unidade', 'Cartolina branca folha grossa', 'Publish', '2019-05-02 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `Id` int(11) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Sexo` varchar(10) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Celular` varchar(45) DEFAULT NULL,
  `DataNascimento` date NOT NULL,
  `Setor` varchar(45) NOT NULL,
  `Permissao` varchar(45) NOT NULL,
  `Login` varchar(45) NOT NULL,
  `Senha` varchar(100) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`Id`, `CPF`, `Nome`, `Sexo`, `Email`, `Telefone`, `Celular`, `DataNascimento`, `Setor`, `Permissao`, `Login`, `Senha`, `Status`, `Created`) VALUES
(5, '11111111111', 'Welison Menezes', 'Masculino', 'welison@email.com', '333333332222', '99999999', '1987-01-06', 'Administração', 'Administrador', 'welison', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-03-28 00:00:00'),
(6, '22222222222', 'José', 'Masculino', 'js@email.com', '33333333', '99999999', '1999-03-06', 'Recursos Humanos', 'Usuário', 'jose', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-03-28 00:00:00'),
(7, '33333333333', 'Maria Clara', 'Feminino', 'maria@luiza.com', '22111133', '99889988', '2000-06-05', 'Recursos Humanos', 'Almoxarife', 'maria', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-03-28 00:00:00'),
(8, '44444444444', 'Bruce Banner', 'Masculino', 'batman@email', '22333344', '99009900', '1999-07-06', 'Contabilidade', 'Usuário', 'batman', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-03-29 00:00:00'),
(9, '55555555555', 'Tony Stark', 'Masculino', 'iron@man.com', '22222233323', '77766776677', '2000-04-04', 'Almoxarifado', 'Almoxarife', 'tony', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-04-10 00:00:00'),
(10, '32223333333', 'Luiza Canadá', 'Feminino', 'luiza@canada.com', '21212324', '98989897', '2013-04-27', 'Administração', 'Administrador', 'luiza', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-04-25 00:00:00'),
(11, '43434454345', 'Carol Castro', 'Feminino', 'carol@mail.com', '4343434343', '5454565454', '1998-04-03', 'Recursos Humanos', 'Usuário', 'carol', 'f8cdb04495ded47615258f9dc6a3f4707fd2405434fefc3cbf4ef4e6', 'Publish', '2019-04-25 00:00:00'),
(12, '12222322234', 'Jhonny Bravo', 'Masculino', 'jb@mail.com', '32322211', '98979897', '2000-04-29', 'Recursos Humanos', 'Usuário', 'bravo', '123456', 'Publish', '2019-04-29 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estoque`
--
ALTER TABLE `estoque`
  ADD UNIQUE KEY `ProdutoId` (`ProdutoId`),
  ADD KEY `fk_Estoque_Produtos1_idx` (`ProdutoId`);

--
-- Indexes for table `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Cnpj` (`Cnpj`);

--
-- Indexes for table `notasfiscais`
--
ALTER TABLE `notasfiscais`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_NotasFiscais_Fornecedores_idx` (`FornecedorId`);

--
-- Indexes for table `notasfiscaisprodutos`
--
ALTER TABLE `notasfiscaisprodutos`
  ADD PRIMARY KEY (`NotaFiscalId`,`ProdutoId`),
  ADD KEY `fk_NotasFiscais_has_Produtos_Produtos1_idx` (`ProdutoId`),
  ADD KEY `fk_NotasFiscais_has_Produtos_NotasFiscais1_idx` (`NotaFiscalId`);

--
-- Indexes for table `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Pedidos_Usuarios1_idx` (`UsuarioId`);

--
-- Indexes for table `pedidosprodutos`
--
ALTER TABLE `pedidosprodutos`
  ADD PRIMARY KEY (`Id`,`PedidoId`,`ProdutoId`),
  ADD KEY `fk_Pedidos_has_Produtos_Produtos1_idx` (`ProdutoId`),
  ADD KEY `fk_Pedidos_has_Produtos_Pedidos1_idx` (`PedidoId`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `CPF_UNIQUE` (`CPF`),
  ADD UNIQUE KEY `Login_UNIQUE` (`Login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fornecedores`
--
ALTER TABLE `fornecedores`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `notasfiscais`
--
ALTER TABLE `notasfiscais`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `pedidosprodutos`
--
ALTER TABLE `pedidosprodutos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `fk_Estoque_Produtos1` FOREIGN KEY (`ProdutoId`) REFERENCES `produtos` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `notasfiscais`
--
ALTER TABLE `notasfiscais`
  ADD CONSTRAINT `fk_NotasFiscais_Fornecedores` FOREIGN KEY (`FornecedorId`) REFERENCES `fornecedores` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `notasfiscaisprodutos`
--
ALTER TABLE `notasfiscaisprodutos`
  ADD CONSTRAINT `fk_NotasFiscais_has_Produtos_NotasFiscais1` FOREIGN KEY (`NotaFiscalId`) REFERENCES `notasfiscais` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_NotasFiscais_has_Produtos_Produtos1` FOREIGN KEY (`ProdutoId`) REFERENCES `produtos` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_Pedidos_Usuarios1` FOREIGN KEY (`UsuarioId`) REFERENCES `usuarios` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `pedidosprodutos`
--
ALTER TABLE `pedidosprodutos`
  ADD CONSTRAINT `fk_Pedidos_has_Produtos_Pedidos1` FOREIGN KEY (`PedidoId`) REFERENCES `pedidos` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Pedidos_has_Produtos_Produtos1` FOREIGN KEY (`ProdutoId`) REFERENCES `produtos` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
