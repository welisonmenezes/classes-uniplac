# 4
ALTER TABLE `cliente` ADD `Email` VARCHAR(100) NOT NULL AFTER `Endereco`;

# 5
SELECT Cpf, Endereco FROM `cliente` WHERE Nome = "Paulo A Lima";

# 6
SELECT Numero, Endereco FROM `agencia` WHERE BancoCodigo = 1

# 7
SELECT cliente.Nome as Cliente, conta.Numero as Conta, agencia.Numero as Agencia FROM cliente 
	LEFT JOIN historico ON historico.ClienteCpf = cliente.Cpf 
    LEFT JOIN conta ON historico.ContaNumero = conta.Numero 
    LEFT JOIN agencia ON conta.AgenciaNumero = agencia.Numero

# 8
SELECT * FROM `cliente` WHERE sexo = "M"

# 9
SELECT * FROM agencia LEFT JOIN banco on agencia.BancoCodigo = banco.Codigo WHERE agencia.Numero = 6342

# 10
# dá erro pq o ON DELETE da chave estrangeira está setada como NO ACTION
#DELETE FROM `conta` WHERE `conta`.`Numero` = 863402

# 11
# dá erro pq o ON UPDATE da chave estrangeira está setada com NO ACTION
# UPDATE `agencia` SET `Numero` = '6342' WHERE `agencia`.`Numero` = 562

# 12
UPDATE `cliente` SET `Email` = 'caetandolima@gmail.com' WHERE `cliente`.`Cpf` = 66677788899;

# 13
UPDATE `conta` SET `saldo` = (saldo+saldo*0.1) WHERE `conta`.`Numero` = 235847