# 3
INSERT INTO `banco` (`Codigo`, `Nome`) VALUES (NULL, 'Banco do Brasil'), (NULL, 'CEF');

INSERT INTO `agencia` (`Numero`, `Endereco`, `BancoCodigo`) VALUES ('0562', 'Rua Joaquim Texeira Alves, 1555', '2'), ('3152', 'Av. Marcelino Pires, 735', '1');

INSERT INTO `conta` (`Numero`, `Saldo`, `TipoConta`, `AgenciaNumero`) VALUES ('863402', '763.05', '2', '3152'), ('235847', '3879.12', '1', '562');

INSERT INTO `cliente` (`Cpf`, `Nome`, `Sexo`, `Endereco`) VALUES ('11122233344', 'Jenifer B. Souza', 'F', 'Rua Cuiabá, 155');
INSERT INTO `cliente` (`Cpf`, `Nome`, `Sexo`, `Endereco`) VALUES ('66677788899', 'Caetano K Lima', 'M', 'Rua Ivinhema, 879');
INSERT INTO `cliente` (`Cpf`, `Nome`, `Sexo`, `Endereco`) VALUES ('55544477733', 'Silvia Macedo', 'F', 'Rua Estados Unidos, 735');


INSERT INTO `historico` (`ClienteCpf`, `ContaNumero`, `DataInicio`) VALUES ('11122233344', '235847', '1997-02-17 00:00:00'), ('66677788899', '235847', '1997-02-17 00:00:00');
INSERT INTO `historico` (`ClienteCpf`, `ContaNumero`, `DataInicio`) VALUES ('55544477733', '863402', '2010-11-29 00:00:00');


INSERT INTO `telefonecliente` (`Telefone`, `ClienteCpf`) VALUES ('(67) 3422-7788', '11122233344');
INSERT INTO `telefonecliente` (`Telefone`, `ClienteCpf`) VALUES ('(67) 3423-9900', '66677788899');
INSERT INTO `telefonecliente` (`Telefone`, `ClienteCpf`) VALUES ('(67) 8121-8833', '66677788899');