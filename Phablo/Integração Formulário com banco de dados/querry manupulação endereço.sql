drop table endereços;
create table endereços(
	id integer,
	uf varchar(2),
	cidade varchar(50),
	bairro varchar(50),
	rua varchar(50),
	quadra integer,
	casa integer,
	lote integer,
	numero integer,
	cep char(8),
	primary key (id),
	foreign key(id) references formularios(id)
);

select * from endereços;

insert into endereços(id,uf,cidade,bairro,rua,quadra,casa,lote,numero,cep) values(1,'GO','Goiânia','Jardim Maria Inês', 'Rua 1',61,01,0,0,74914180);