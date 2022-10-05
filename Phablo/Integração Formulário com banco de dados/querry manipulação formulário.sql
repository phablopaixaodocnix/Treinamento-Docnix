CREATE TYPE escolaridade AS ENUM('ensino fundamental','ensino médio','ensino superior' );

drop table formularios;
CREATE TABLE formularios(
	id serial,
	nome varchar(50),
	email varchar(50),
	cpf char(11),
	escolaridade escolaridade,
	primary key(id)
);
select * from formularios;

insert into formularios(nome,email,cpf,escolaridade) values ('phablo','phablo.tavares1@gmail.com','70778358127','ensino superior');
select * from formularios;


select formularios.nome as "Nome", endereços.rua as "Rua" , contatos.nomecontato as "Nome do Contato" from
	formularios
	join
	endereços on endereços.id = formularios.id
	join
	contatos on contatos.id = formularios.id;
	
alter table formularios add column endereço_id integer;
alter table formularios add column contato1_id integer;
alter table formularios add column contato2_id integer;
alter table formularios add column contato3_id integer;
alter table formularios add column contato4_id integer;
alter table formularios add column contato5_id integer;