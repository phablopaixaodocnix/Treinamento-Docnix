drop table contatos;
create table contatos(
	id serial,
	idFormulario integer,
	nomeContato varchar(50),
	emailContato varchar (50),
	telefoneContato char(11),
	primary key (id),
	foreign key (idFormulario) references formularios(id)
	ON DELETE CASCADE
);
select * from contatos;
insert into contatos(nomeContato,emailContato,telefoneContato) values ('prdro','joão@joão','62999035665');
select * from contatos;

delete from contatos where id < 10000;
	select * from contatos;
