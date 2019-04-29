# Fornecedores
SELECT * FROM fornecedores WHERE Status != 'Deleted' ORDER BY Id DESC;

# Produtos
SELECT * FROM produtos WHERE Status != 'Deleted' ORDER BY Id DESC;

# Usuários
SELECT * FROM usuarios WHERE Status != 'Deleted' ORDER BY Id DESC;

# Todas as Notas Fiscais
SELECT notasfiscais.Id AS nfId,
notasfiscais.Numero AS nfNum,
notasfiscais.Serie AS nfSerie,
notasfiscais.Valor AS nfValor,
notasfiscaisprodutos.Valor AS prodValor,
notasfiscaisprodutos.Quantidade AS prodQtd,
produtos.Id AS prodId,
produtos.Nome AS prodNome,
produtos.Unidade AS prodUnidade
FROM notasfiscais 
INNER JOIN notasfiscaisprodutos ON notasfiscaisprodutos.NotaFiscalId = notasfiscais.Id
INNER JOIN produtos ON produtos.Id = notasfiscaisprodutos.ProdutoId
ORDER BY notasfiscais.Id DESC;

# Nota Fiscal específica
SELECT notasfiscaisprodutos.Valor AS prodValor,
notasfiscaisprodutos.Quantidade AS prodQtd,
produtos.Id AS prodId,
produtos.Nome AS prodNome,
produtos.Unidade AS prodUnidade
FROM notasfiscais 
INNER JOIN notasfiscaisprodutos ON notasfiscaisprodutos.NotaFiscalId = notasfiscais.Id
INNER JOIN produtos ON produtos.Id = notasfiscaisprodutos.ProdutoId
WHERE notasfiscais.Id = 1
ORDER BY notasfiscais.Id DESC;

# Todos os pedidos
SELECT pedidos.Id as pId,
pedidosprodutos.QuantidadeSolicitada as pQtd,
pedidosprodutos.QuantidadeAprovada AS pQtdAprovada,
produtos.Id AS prodId,
produtos.Nome AS prodNome,
produtos.Unidade AS prodUnidade,
usuarios.Id AS userId,
usuarios.Nome AS userNome
FROM pedidos
INNER JOIN pedidosprodutos ON pedidosprodutos.PedidoId = pedidos.Id
INNER JOIN produtos ON produtos.Id = pedidosprodutos.ProdutoId
INNER JOIN usuarios ON usuarios.Id = pedidos.UsuarioId
ORDER BY pedidos.Id DESC;

# Pedido específico
SELECT pedidosprodutos.QuantidadeSolicitada as pQtd,
pedidosprodutos.QuantidadeAprovada AS pQtdAprovada,
produtos.Id AS prodId,
produtos.Nome AS prodNome,
produtos.Unidade AS prodUnidade,
usuarios.Id AS userId,
usuarios.Nome AS userNome
FROM pedidos
INNER JOIN pedidosprodutos ON pedidosprodutos.PedidoId = pedidos.Id
INNER JOIN produtos ON produtos.Id = pedidosprodutos.ProdutoId
INNER JOIN usuarios ON usuarios.Id = pedidos.UsuarioId
WHERE pedidos.Id = 1
ORDER BY pedidos.Id DESC;