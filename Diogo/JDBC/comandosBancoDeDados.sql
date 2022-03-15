create database loja_virtual;

use loja_virtual;

create table produto (id int auto_increment, nome varchar(50) not null, descricao varchar(255), primary key (id)) engine = InnoDB;

insert into produto (nome, descricao) values ('notebook','notebook sansung');
insert into produto (nome, descricao) values ('geladeira','geladeira azul');

select * from produto;