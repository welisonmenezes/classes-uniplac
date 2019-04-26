# EXERCÍCIO 1

# a)
SELECT ID_NF, ID_ITEM, COD_PROD, VALOR_UNIT FROM aula WHERE DESCONTO IS NULL;

# b)
SELECT ID_NF, ID_ITEM, COD_PROD, VALOR_UNIT, (VALOR_UNIT - (VALOR_UNIT * (DESCONTO / 100))) AS 'VALOR_VENDIDO' FROM aula WHERE DESCONTO IS NOT NULL;

# c)
UPDATE aula SET DESCONTO = 0 WHERE DESCONTO IS NULL;

# d)
SELECT ID_NF, ID_ITEM, COD_PROD, VALOR_UNIT, (VALOR_UNIT * QUANTIDADE) AS 'VALOR_TOTAL', DESCONTO, (VALOR_UNIT - (VALOR_UNIT * (DESCONTO / 100))) AS 'VALOR_VENDIDO' FROM aula;

# e)
SELECT ID_NF, (VALOR_UNIT * QUANTIDADE) AS 'VALOR_TOTAL' FROM aula ORDER BY VALOR_TOTAL DESC;

# f)
SELECT ID_NF, (VALOR_UNIT - (VALOR_UNIT * (DESCONTO / 100))) AS 'VALOR_VENDIDO' FROM aula ORDER BY (VALOR_UNIT * QUANTIDADE)  DESC;

# g)
SELECT COD_PROD, SUM(QUANTIDADE) AS QUANTIDADE FROM aula GROUP BY COD_PROD ORDER BY QUANTIDADE DESC LIMIT 1;

# h)
SELECT ID_NF, COD_PROD, QUANTIDADE FROM aula WHERE QUANTIDADE > 10;

# i)
SELECT ID_NF, (VALOR_UNIT * QUANTIDADE) AS VALOR_TOTAL FROM aula WHERE (VALOR_UNIT * QUANTIDADE) > 500;

# j)
SELECT COD_PROD, AVG(DESCONTO) AS MEDIA FROM aula GROUP BY COD_PROD;

# k)
SELECT COD_PROD, MAX(DESCONTO) AS MAIOR, MIN(DESCONTO) AS MENOR, AVG(DESCONTO) AS MEDIA FROM aula;

# l)
SELECT ID_NF, COUNT(COD_PROD) AS QTD_ITENS FROM aula WHERE QUANTIDADE > 3 GROUP BY ID_NF;