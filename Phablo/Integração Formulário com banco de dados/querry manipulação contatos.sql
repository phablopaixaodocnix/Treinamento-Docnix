drop table contatos;
create table contatos(
	id integer,
	idContato serial,
	nomeContato varchar(50),
	emailContato varchar (50),
	telefoneContato char(11),
	primary key (idContato),
	foreign key (id) references formularios(id)
);
insert into contatos(id,nomeContato,emailContato,telefoneContato) values (1,'João','joão@joão','62999035665');
select * from contatos;


