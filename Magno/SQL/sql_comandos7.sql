use sucos;

/*alter table tbvendedor add column DATA_ADMISSAO date;

alter table tbvendedor add column DE_FERIAS binary;

insert into tbvendedor (
NOME, MATRICULA, COMISSAO, DATA_ADMISSAO, DE_FERIAS) values(
'Márcio Almeida Silva', 00235, 8, '2014-08-15', 0);
insert into tbvendedor(
NOME, MATRICULA, COMISSAO, DATA_ADMISSAO, DE_FERIAS) values (
'Cláudia Morais', 00235, 8, '2013-09-17', 1);
insert into tbvendedor(
NOME, MATRICULA, COMISSAO, DATA_ADMISSAO, DE_FERIAS) values(
'Roberta Martins', 00237, 11, '2017-03-18', 1);
insert into tbvendedor(
NOME, MATRICULA, COMISSAO, DATA_ADMISSAO, DE_FERIAS) values(
'Péricles Alves', 00238, 11, '2016-08-21', 0);*/

select NOME, MATRICULA from tbvendedor LIMIT 3;

select * from tbvendedor where MATRICULA = '00235';

select * from tbcliente;

