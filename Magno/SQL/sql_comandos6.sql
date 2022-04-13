USE sucos;

/*ALTER TABLE tbcliente ADD PRIMARY KEY (CPF);*/

/*ALTER TABLE tbcliente ADD column (DATA_NASCIMENTO DATE);*/

INSERT INTO tbcliente (
CPF, NOME, ENDERECO1, ENDERECO2, BAIRRO, CIDADE, ESTADO, CEP,
SEXO, LIMITE_CREDITO, VOLUME_COMPRA, PRIMEIRA_COMPRA,
DATA_NASCIMENTO) VALUES (
'2301928321', 'João Silva', 'Avenida dos Moradores 123', '', 'João São',
'Bahiânia', 'Bahea', '12381723', 'M', 1000.00, 200, 0, '1989-09-30');

select * from tbcliente;