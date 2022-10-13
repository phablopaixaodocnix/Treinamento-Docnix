drop table endereços;
create table endereços(
	id serial,
	idFormulario integer,
	uf varchar(2),
	cidade varchar(50),
	bairro varchar(50),
	rua varchar(50),
	quadra integer,
	casa integer,
	lote integer,
	numero integer,
	cep varchar(8),
	primary key (id),
	foreign key (idFormulario) references formularios(id)
	ON DELETE CASCADE
);
select * from endereços;

insert into endereços(uf,cidade,bairro,rua,quadra,casa,lote,numero,cep) values('GO','Goiânia','Jardimasas Maria Inês', 'Rua 1',61,01,0,0,'74914180');
insert into endereços(uf,cidade,bairro,rua,quadra,casa,lote,numero,cep) values('ES','Vitória','Jardimfasdfas Maria Inês', 'Rua 1',61,01,0,0,'74914180');
insert into endereços(uf,cidade,bairro,rua,quadra,casa,lote,numero,cep) values('SP','São Paulo','Jardim Maria Inês', 'Rua 1',61,01,0,0,'74914180');
insert into endereços(uf,cidade,bairro,rua,quadra,casa,lote,numero,cep) values('GO','Anápolis','Jardidfasdfasdfm Maria Inês', 'Rua 1',61,01,0,0,'74914180');
select * from endereços;

delete from endereços where uf = 'GO';
select * from endereços;

delete from endereços where id <10000;
select * from endereços;

