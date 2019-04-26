INSERT INTO `alunos` (`MAT`, `nome`, `endereco`, `cidade`) VALUES ('2015010101', 'JOSE DE ALENCAR', 'RUA DAS ALMAS', 'NATAL');
INSERT INTO `alunos` (`MAT`, `nome`, `endereco`, `cidade`) VALUES ('2015010102', 'JOÃO JOSÉ', 'AVENIDA RUY CARNEIRO', 'JOÃO PESSOA');
INSERT INTO `alunos` (`MAT`, `nome`, `endereco`, `cidade`) VALUES ('2015010103', 'MARIA JOAQUINA', 'RUA CARROSSEL', 'RECIFE');
INSERT INTO `alunos` (`MAT`, `nome`, `endereco`, `cidade`) VALUES ('2015010104', 'MARIA DAS DORES', 'RUA DAS LADEIRAS', 'FORTALEZA');
INSERT INTO `alunos` (`MAT`, `nome`, `endereco`, `cidade`) VALUES ('2015010105', 'JOSUÉ CLAUDINO DOS SANTOS', 'CENTRO', 'NATAL');
INSERT INTO `alunos` (`MAT`, `nome`, `endereco`, `cidade`) VALUES ('2015010106', 'JOSUÉLISSON CLAUDINO DOS SANTOS', 'CENTRO', 'NATAL');



INSERT INTO `disciplinas` (`COD_DISC`, `nome_disc`, `carga_hor`) VALUES ('BD', 'BANCO DE DADOS', '100');
INSERT INTO `disciplinas` (`COD_DISC`, `nome_disc`, `carga_hor`) VALUES ('POO', 'PROGRAMAÇÃO COM ACESSO A BANCO DE DADOS', '100');
INSERT INTO `disciplinas` (`COD_DISC`, `nome_disc`, `carga_hor`) VALUES ('WEB', 'AUTORIA WEB', '50');
INSERT INTO `disciplinas` (`COD_DISC`, `nome_disc`, `carga_hor`) VALUES ('ENG', 'ENGENHARIA DE SOFTWARE', '80');



INSERT INTO `professores` (`COD_PROF`, `nome`, `endereco`, `cidade`) VALUES ('212131', 'NICKERSON FERREIRA', 'RUA MANAÍRA', 'JOÃO PESSOA');
INSERT INTO `professores` (`COD_PROF`, `nome`, `endereco`, `cidade`) VALUES ('122135', 'ADORILSON BEZERRA', 'AVENIDA SALGADO FILHO', 'NATAL');
INSERT INTO `professores` (`COD_PROF`, `nome`, `endereco`, `cidade`) VALUES ('192011', 'DIEGO OLIVEIRA', 'AVENIDA ROBERTO FREIRE', 'NATAL');



INSERT INTO `turma` (`COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `horario`) VALUES ('BD', '1', '212131', '2015', '11H-12H');
INSERT INTO `turma` (`COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `horario`) VALUES ('BD', '2', '212131', '2015', '13H-14H');
INSERT INTO `turma` (`COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `horario`) VALUES ('POO', '1', '192011', '2015', '08H-09H');
INSERT INTO `turma` (`COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `horario`) VALUES ('WEB', '1', '192011', '2015', '07H-08H');
INSERT INTO `turma` (`COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `horario`) VALUES ('ENG', '1', '122135', '2015', '10H-11H');



INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010101', 'BD', '2', '212131', '2015', '90', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010101', 'POO', '1', '192011', '2015', '75', '65');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010101', 'WEB', '1', '192011', '2015', '100', '45');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010101', 'ENG', '1', '122135', '2015', '90', '90');

INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010102', 'BD', '1', '212131', '2015', '70', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010102', 'POO', '1', '192011', '2015', '75', '75');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010102', 'WEB', '1', '192011', '2015', '60', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010102', 'ENG', '1', '122135', '2015', '96', '57');

INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010103', 'BD', '1', '212131', '2015', '40', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010103', 'POO', '1', '192011', '2015', '75', '55');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010103', 'WEB', '1', '192011', '2015', '50', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010103', 'ENG', '1', '122135', '2015', '87', '57');

INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010104', 'BD', '2', '212131', '2015', '80', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010104', 'POO', '1', '192011', '2015', '85', '85');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010104', 'WEB', '1', '192011', '2015', '80', '75');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010104', 'ENG', '1', '122135', '2015', '77', '77');

INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010105', 'BD', '2', '212131', '2015', '30', '35');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010105', 'POO', '1', '192011', '2015', '95', '100');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010105', 'WEB', '1', '192011', '2015', '80', '100');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010105', 'ENG', '1', '122135', '2015', '87', '97');

INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010106', 'BD', '1', '212131', '2015', '70', '91');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010106', 'POO', '1', '192011', '2015', '93', '50');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010106', 'WEB', '1', '192011', '2015', '40', '45');
INSERT INTO `historico` (`MAT`, `COD_DISC`, `COD_TURMA`, `COD_PROF`, `ano`, `frequencia`, `nota`) VALUES ('2015010106', 'ENG', '1', '122135', '2015', '83', '87');