CREATE DATABASE trabalhoJava; 
USE trabalhoJava;

CREATE TABLE endereco(
id int(11) unsigned not null auto_increment,
cidade varchar(20) not null,
pais varchar(15) not null,
estado varchar(20) not null,
cep varchar(20) not null,
PRIMARY KEY(id)
);

CREATE TABLE cliente(
id int(11)unsigned not null auto_increment,
nome varchar(20) not null,
email varchar(20) not null,
cpf varchar(20) not null,
telefone varchar(15),
idEndereco int(11)unsigned not null,
PRIMARY KEY(id),
KEY fk_cliente_endereco (idEndereco),
CONSTRAINT fk_cliente_endereco FOREIGN KEY (idEndereco) REFERENCES endereco(id)
);

CREATE TABLE vendedor(
id int(11)unsigned not null auto_increment,
nome varchar(20) not null,
idade int(11) not null,
email varchar(20) not null,
salario float,
PRIMARY KEY(id)
);

CREATE TABLE game(
id int(11)unsigned not null auto_increment,
nome varchar(20) not null,
preco float,
categoria varchar(20), 
idCliente int(11)unsigned not null,
idVendedor int(11)unsigned not null,
PRIMARY KEY(id),
KEY fk_game_cliente (idCliente),
KEY fk_game_vendedor (idVendedor),
CONSTRAINT fk_game_cliente FOREIGN KEY(idCliente) REFERENCES cliente(id),
CONSTRAINT fk_game_vendedor FOREIGN KEY(idVendedor) REFERENCES vendedor(id)
);
CREATE TABLE produtor(
 id int(11) unsigned NOT NULL auto_increment,
 nome varchar(20) not null,
 cnpj varchar(20) not null,
 PRIMARY KEY(id)
);

CREATE TABLE produtor_game(
	idGame int(11) unsigned NOT NULL,
	idProdutor int(11) unsigned NOT NULL,
	PRIMARY KEY(idGame, idProdutor),
	KEY fk_game (idGame),
	KEY fk_produtor (idProdutor),
	CONSTRAINT fk_game FOREIGN KEY(idGame) REFERENCES game(id),
	CONSTRAINT fk_produtor FOREIGN KEY(idProdutor) REFERENCES produtor(id)
);






