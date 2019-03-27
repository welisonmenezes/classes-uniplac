-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 27-Mar-2019 às 19:43
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

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE `fornecedores` (
  `Id` int(11) NOT NULL,
  `Cnpj` varchar(18) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `fornecedores`
--

INSERT INTO `fornecedores` (`Id`, `Cnpj`, `Nome`, `Telefone`, `Status`, `Created`) VALUES
(1, '11111111111', 'Casa do papel', '9911991199', 'Publish', '2019-03-27 00:00:00'),
(2, '22222222222', 'Escritórios SA', '333222333', 'Publish', '2019-03-27 00:00:00'),
(3, '33333333333', 'Papelaria Rosário', '4343434334', 'Publish', '2019-03-27 00:00:00'),
(4, '44444444444', 'Depositoffice Ltda', '44445555', 'Publish', '2019-03-27 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `notasfiscais`
--

CREATE TABLE `notasfiscais` (
  `Id` int(11) NOT NULL,
  `Numero` bigint(20) NOT NULL,
  `Serie` int(11) NOT NULL,
  `Valor` double NOT NULL,
  `Data` datetime NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL,
  `FornecedorId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `notasfiscaisprodutos`
--

CREATE TABLE `notasfiscaisprodutos` (
  `NotaFiscalId` int(11) NOT NULL,
  `ProdutoId` int(11) NOT NULL,
  `Valor` double NOT NULL,
  `Quantidade` int(11) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `Id` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL,
  `UsuarioId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(3, 'Lápis 6b', 'Unidade', 'Lápis 6b verde', 'Publish', '2019-03-26 00:00:00'),
(4, 'Grampos de papel', 'Caixa', 'Grampos de papel', 'Publish', '2019-03-26 00:00:00'),
(5, 'Fita Adesiva', 'Unidade', 'Fita adesiva 12mm x 30m', 'Publish', '2019-03-27 00:00:00'),
(6, 'Clips Coloridos', 'Caixa', 'Clips coloridos jumbo caixa com 200 unidades', 'Publish', '2019-03-27 00:00:00'),
(7, 'Post-It Amarelo', 'Pacote', 'Post-It 4 bocos amarelo', 'Publish', '2019-03-27 00:00:00'),
(8, 'Corretivo 18ml', 'Unidade', 'Líquido corretivo office blanc', 'Publish', '2019-03-27 00:00:00'),
(9, 'fafa', 'Quilo', 'faa', 'Deleted', '2019-03-27 00:00:00'),
(10, 'afa', 'Quilo', 'fa', 'Deleted', '2019-03-27 00:00:00'),
(11, 'Pasta Ofício', 'Unidade', 'Pasta AZ ofício economic', 'Publish', '2019-03-27 00:00:00'),
(12, 'Borracha Plástica', 'Unidade', 'Borracha plástica stapless', 'Publish', '2019-03-27 00:00:00'),
(13, 'Marca Texto', 'Unidade', 'Marca texto azul stapless', 'Publish', '2019-03-27 00:00:00'),
(14, 'Marcatexto Azull', 'Unidade', 'Marcatexto azul stapless', 'Publish', '2019-03-27 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `Id` int(11) NOT NULL,
  `CPF` varchar(14) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Sexo` varchar(10) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Celular` varchar(45) DEFAULT NULL,
  `DataNascimenot` datetime NOT NULL,
  `Setor` varchar(45) NOT NULL,
  `Permissao` varchar(45) NOT NULL,
  `Login` varchar(45) NOT NULL,
  `Senha` varchar(100) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estoque`
--
ALTER TABLE `estoque`
  ADD KEY `fk_Estoque_Produtos1_idx` (`ProdutoId`);

--
-- Indexes for table `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`Id`);

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
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `notasfiscais`
--
ALTER TABLE `notasfiscais`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedidosprodutos`
--
ALTER TABLE `pedidosprodutos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

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
