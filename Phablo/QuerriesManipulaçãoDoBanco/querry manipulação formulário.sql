drop table formularios cascade;
CREATE TABLE formularios(
	id serial,
	nome varchar(50),
	email varchar(50),
	cpf char(11),
	escolaridade varchar(50),
	primary key(id)
);
select * from formularios;

insert into formularios(nome,email,cpf,escolaridade) values ('phablo','phablo.tavares1@gmail.com','70778358127','ensino superior');
insert into formularios(nome,email,cpf,escolaridade) values ('lucas','phablo.tavares1@gmail.com','70778358127','ensino superior');
insert into formularios(nome,email,cpf,escolaridade) values ('pedro','phablo.tavares1@gmail.com','70778358127','ensino superior');
insert into formularios(nome,email,cpf,escolaridade) values ('julio','phablo.tavares1@gmail.com','70778358127','ensino superior');

select * from formularios;
	
alter table formularios add column endereço_id integer;
alter table formularios add column contato1_id integer;
alter table formularios add column contato2_id integer;
alter table formularios add column contato3_id integer;
alter table formularios add column contato4_id integer;
alter table formularios add column contato5_id integer;

alter table formularios add foreign key (endereço_id) references endereços(id);
alter table formularios add foreign key (contato1_id) references contatos(id);
alter table formularios add foreign key (contato2_id) references contatos(id);
alter table formularios add foreign key (contato3_id) references contatos(id);
alter table formularios add foreign key (contato4_id) references contatos(id);
alter table formularios add foreign key (contato5_id) references contatos(id);

select * from
formularios
join
endereços on endereços.id = formularios.id;

delete from formularios where id < 10000;
delete from endereços where id <10000;
delete from contatos where id <10000;

select * from 
formularios join
endereços on endereços.idformulario = formularios.id right join
contatos on contatos.idformulario = formularios.id;

select * from formularios;
