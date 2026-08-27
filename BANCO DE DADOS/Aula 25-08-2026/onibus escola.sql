CREATE DATABASE onibusescola;
USE onibusescola;

CREATE TABLE motorista (
	nome_motorista VARCHAR(100) PRIMARY KEY,
	horário INT,
	mud_onibus INT,
	rota VARCHAR(200);
	loacalizacao VARCHAR(100);	
)

CREATE TABLE escola (
	loacalizacao VARCHAR(100) PRIMARY KEY,
	quantidade_aluno INT,
	horário INT,
	publico VARCHAR(1);	
) 

CREATE TABLE pais (
	nome_responsáveis VARCHAR(100) PRIMARY KEY,
	aluno_filiado VARCHAR(100),
	numero_celular INT,
	filho VARCHAR(100),
	endereço responsável,	
	horario_em_casa_responsavel VARCHAR(100);
)	

CREATE TABLE aluno(
	nome VARCHAR(100) PRIMARY KEY,
	horário INT,
	escola VARCHAR(100),
	nome_responsáveis VARCHAR(100);
)

CREATE TABLE endereços(
	horario_em_casa_responsavel INT PRIMARY KEY,
	cep INT,
	rua VARCHAR(50),
	numero casa INT,
	turno_aluno VARCHAR(100), 
	descrição VARCHAR(100),
	loacalizacao VARCHAR(100);
)
