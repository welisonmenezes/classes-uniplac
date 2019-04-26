# a) usei 50 pq cadastrei com escala de 0-100
SELECT MAT FROM historico WHERE ano = 2015 AND nota < 50 AND COD_DISC = 'BD';

# b)
SELECT MAT AS 'matrícula', 
(SELECT AVG(nota) FROM historico WHERE COD_DISC = 'POO' AND ano = 2015) AS 'média da turma' 
FROM historico WHERE COD_DISC = 'POO'  AND ano = 2015;

# c) usei 60 pq cadastrei com escala de 0-100
SELECT MAT AS 'matrícula', 
(SELECT AVG(nota) FROM historico WHERE COD_DISC = 'POO' AND ano = 2015) AS 'média da turma' 
FROM historico 
WHERE COD_DISC = 'POO'  
AND ano = 2015 AND (SELECT AVG(nota) FROM historico WHERE COD_DISC = 'POO' AND ano = 2015) > 60;

# d)
SELECT COUNT(MAT) FROM `alunos` WHERE cidade != 'Natal';