create database databaseHair;

use databaseHair;

create table usuario(
	codigo int primary key auto_increment,
    nome varchar(50),
    email varchar(50),
    senha varchar(50)
);

select * from usuario;

create table salao(
	codigo int primary key auto_increment,
    nome varchar(50),
    cnpj varchar(14),
    codigo_usuario int,
    foreign key(codigo_usuario) references usuario(codigo)
);

select * from salao;

