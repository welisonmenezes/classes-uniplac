# Fornecedores

INSERT INTO `fornecedores` (`Id`, `Cnpj`, `Nome`, `Telefone`, `Status`, `Created`) 
VALUES (NULL, '10121444444555', 'Plast House', '21212323', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `fornecedores` (`Id`, `Cnpj`, `Nome`, `Telefone`, `Status`, `Created`) 
VALUES (NULL, '99121444444555', 'Casa do Papel', '33212323', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `fornecedores` (`Id`, `Cnpj`, `Nome`, `Telefone`, `Status`, `Created`) 
VALUES (NULL, '65651444444555', 'Esc Inventory', '99882323', 'Publish', '2019-04-29 00:00:00');



# Produtos

INSERT INTO `produtos` (`Id`, `Nome`, `Unidade`, `Descricao`, `Status`, `Created`) 
VALUES (NULL, 'Prancheta', 'Unidade', 'Prancheta madeira A4', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `produtos` (`Id`, `Nome`, `Unidade`, `Descricao`, `Status`, `Created`) 
VALUES (NULL, 'Folha A4', 'Pacote', 'Folha A4 100 unidades', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `produtos` (`Id`, `Nome`, `Unidade`, `Descricao`, `Status`, `Created`) 
VALUES (NULL, 'Caneta Azul', 'Unidade', 'Caneta Azul BIC', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `produtos` (`Id`, `Nome`, `Unidade`, `Descricao`, `Status`, `Created`) 
VALUES (NULL, 'L�pis 6B', 'Unidade', 'L�pis 6B Compacta', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `produtos` (`Id`, `Nome`, `Unidade`, `Descricao`, `Status`, `Created`) 
VALUES (NULL, 'Grampos', 'Caixa', 'Grampos pequenos bronze', 'Publish', '2019-04-29 00:00:00');



# Usu�rios

INSERT INTO `usuarios` (`Id`, `CPF`, `Nome`, `Sexo`, `Email`, `Telefone`, `Celular`, `DataNascimento`, `Setor`, `Permissao`, `Login`, `Senha`, `Status`, `Created`) 
VALUES (NULL, '55442322234', 'Luiz Silva', 'Masculino', 'luiz@mail.com', '77322211', '99999897', '2000-01-23', 'Almoxarifado', 'Almoxarife', 'luiz', '123456', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `usuarios` (`Id`, `CPF`, `Nome`, `Sexo`, `Email`, `Telefone`, `Celular`, `DataNascimento`, `Setor`, `Permissao`, `Login`, `Senha`, `Status`, `Created`) 
VALUES (NULL, '12222322234', 'Jhonny Bravo', 'Masculino', 'jb@mail.com', '32322211', '98979897', '2000-04-29', 'Recursos Humanos', 'Usu�rio', 'bravo', '123456', 'Publish', '2019-04-29 00:00:00');

INSERT INTO `usuarios` (`Id`, `CPF`, `Nome`, `Sexo`, `Email`, `Telefone`, `Celular`, `DataNascimento`, `Setor`, `Permissao`, `Login`, `Senha`, `Status`, `Created`) 
VALUES (NULL, '44222322234', 'Maria Ferreira', 'Feminino', 'maria@mail.com', '32325456', '98943597', '1998-04-29', 'Administra��o', 'Admnistrador', 'maria', '123456', 'Publish', '2019-04-29 00:00:00');



# Notas Fiscais

# nota 1

INSERT INTO `notasfiscais` (`Id`, `Numero`, `Serie`, `Valor`, `Data`, `Status`, `Created`, `FornecedorId`) 
VALUES (NULL, '65676567', '6765', '50.6', '2019-04-29', 'Publish', '2019-04-29 00:00:00', '1');

# produtos nota 1

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('1', '1', '3', '30', '2019-04-29 00:00:00');

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('1', '2', '2.5', '20', '2019-04-29 00:00:00');

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('1', '3', '1.99', '5', '2019-04-29 00:00:00');

# nota 2

INSERT INTO `notasfiscais` (`Id`, `Numero`, `Serie`, `Valor`, `Data`, `Status`, `Created`, `FornecedorId`) 
VALUES (NULL, '99996567', '6995', '70.67', '2019-04-29', 'Publish', '2019-04-29 00:00:00', '2');

# produtos nota 2

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('2', '5', '2', '30', '2019-04-29 00:00:00');

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('2', '2', '2.4', '20', '2019-04-29 00:00:00');

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('2', '3', '1.79', '15', '2019-04-29 00:00:00');

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('2', '4', '5.79', '5', '2019-04-29 00:00:00');

INSERT INTO `notasfiscaisprodutos` (`NotaFiscalId`, `ProdutoId`, `Valor`, `Quantidade`, `Created`) 
VALUES ('2', '1', '2.3', '18', '2019-04-29 00:00:00');



# Pedidos

# pedido 1

INSERT INTO `pedidos` (`Id`, `Status`, `Created`, `UsuarioId`, `AlmoxarifeId`) 
VALUES (NULL, 'Pendente', '2019-04-29 00:00:00', '1', NULL);

# produtos pedido 1

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) 
VALUES (NULL, '1', '4', '4', NULL);

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) 
VALUES (NULL, '1', '2', '2', NULL);

# pedido 2

INSERT INTO `pedidos` (`Id`, `Status`, `Created`, `UsuarioId`, `AlmoxarifeId`) 
VALUES (NULL, 'Pendente', '2019-04-29 00:00:00', '2', NULL);

# produtos pedido 2

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) 
VALUES (NULL, '2', '3', '3', NULL);

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) 
VALUES (NULL, '2', '2', '1', NULL);

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) 
VALUES (NULL, '2', '1', '1', NULL);

INSERT INTO `pedidosprodutos` (`Id`, `PedidoId`, `ProdutoId`, `QuantidadeSolicitada`, `QuantidadeAprovada`) 
VALUES (NULL, '2', '5', '3', NULL);