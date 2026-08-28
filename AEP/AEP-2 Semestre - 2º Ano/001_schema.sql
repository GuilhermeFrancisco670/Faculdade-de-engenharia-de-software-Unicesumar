-- ReUse+ | AEP Engenharia de Software
-- SGBD: MySQL 8.x
-- Parte 1: modelo inicial para implementação no 2º bimestre

CREATE DATABASE IF NOT EXISTS reuse_plus
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE reuse_plus;

CREATE TABLE usuario (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(160) NOT NULL,
    telefone VARCHAR(25),
    tipo_usuario ENUM('DOADOR', 'VOLUNTARIO', 'INSTITUICAO', 'ADMINISTRADOR') NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuario_email (email)
) ENGINE = InnoDB;

CREATE TABLE categoria (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    descricao VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY uk_categoria_nome (nome)
) ENGINE = InnoDB;

CREATE TABLE item (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    categoria_id BIGINT UNSIGNED NOT NULL,
    nome VARCHAR(120) NOT NULL,
    descricao TEXT NOT NULL,
    condicao ENUM('NOVO', 'BOM', 'USADO') NOT NULL,
    quantidade INT UNSIGNED NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    CONSTRAINT ck_item_quantidade CHECK (quantidade > 0),
    CONSTRAINT fk_item_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
) ENGINE = InnoDB;

CREATE TABLE doacao (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    doador_id BIGINT UNSIGNED NOT NULL,
    item_id BIGINT UNSIGNED NOT NULL,
    status ENUM('DISPONIVEL', 'EM_TRIAGEM', 'RESERVADO', 'ENTREGUE', 'CANCELADA') NOT NULL DEFAULT 'DISPONIVEL',
    data_cadastro DATE NOT NULL DEFAULT (CURRENT_DATE),
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_doacao_doador FOREIGN KEY (doador_id) REFERENCES usuario(id),
    CONSTRAINT fk_doacao_item FOREIGN KEY (item_id) REFERENCES item(id)
) ENGINE = InnoDB;

CREATE TABLE solicitacao (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    instituicao_id BIGINT UNSIGNED NOT NULL,
    doacao_id BIGINT UNSIGNED NOT NULL,
    quantidade INT UNSIGNED NOT NULL,
    justificativa TEXT NOT NULL,
    status ENUM('PENDENTE', 'APROVADA', 'RECUSADA', 'CANCELADA') NOT NULL DEFAULT 'PENDENTE',
    data_solicitacao DATE NOT NULL DEFAULT (CURRENT_DATE),
    aprovado_por BIGINT UNSIGNED,
    PRIMARY KEY (id),
    CONSTRAINT ck_solicitacao_quantidade CHECK (quantidade > 0),
    CONSTRAINT fk_solicitacao_instituicao FOREIGN KEY (instituicao_id) REFERENCES usuario(id),
    CONSTRAINT fk_solicitacao_doacao FOREIGN KEY (doacao_id) REFERENCES doacao(id),
    CONSTRAINT fk_solicitacao_aprovador FOREIGN KEY (aprovado_por) REFERENCES usuario(id)
) ENGINE = InnoDB;

CREATE TABLE entrega (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    solicitacao_id BIGINT UNSIGNED NOT NULL,
    responsavel_id BIGINT UNSIGNED NOT NULL,
    data_entrega DATE NOT NULL DEFAULT (CURRENT_DATE),
    quantidade_entregue INT UNSIGNED NOT NULL,
    observacoes TEXT,
    PRIMARY KEY (id),
    UNIQUE KEY uk_entrega_solicitacao (solicitacao_id),
    CONSTRAINT ck_entrega_quantidade CHECK (quantidade_entregue > 0),
    CONSTRAINT fk_entrega_solicitacao FOREIGN KEY (solicitacao_id) REFERENCES solicitacao(id),
    CONSTRAINT fk_entrega_responsavel FOREIGN KEY (responsavel_id) REFERENCES usuario(id)
) ENGINE = InnoDB;

CREATE TABLE historico_status (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    doacao_id BIGINT UNSIGNED NOT NULL,
    usuario_id BIGINT UNSIGNED NOT NULL,
    status_anterior VARCHAR(20),
    status_novo VARCHAR(20) NOT NULL,
    alterado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT,
    PRIMARY KEY (id),
    CONSTRAINT fk_historico_doacao FOREIGN KEY (doacao_id) REFERENCES doacao(id),
    CONSTRAINT fk_historico_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE = InnoDB;

CREATE INDEX idx_item_categoria ON item(categoria_id);
CREATE INDEX idx_doacao_doador ON doacao(doador_id);
CREATE INDEX idx_doacao_item ON doacao(item_id);
CREATE INDEX idx_doacao_status ON doacao(status);
CREATE INDEX idx_solicitacao_instituicao ON solicitacao(instituicao_id);
CREATE INDEX idx_solicitacao_doacao_status ON solicitacao(doacao_id, status);
CREATE INDEX idx_solicitacao_status ON solicitacao(status);
CREATE INDEX idx_historico_doacao ON historico_status(doacao_id, alterado_em);

-- Regra de negócio: uma doação deve ter no máximo uma solicitação aprovada.
-- O controle é reforçado na camada de serviço com transação e bloqueio da doação.
-- Essa abordagem mantém a validação da regra na camada de serviço, com transação e bloqueio da doação.

INSERT INTO categoria (nome, descricao) VALUES
    ('Roupas', 'Vestuário adulto e infantil em boas condições'),
    ('Alimentos', 'Alimentos não perecíveis dentro da validade'),
    ('Materiais escolares', 'Cadernos, livros e materiais de apoio'),
    ('Móveis', 'Móveis reutilizáveis para residências ou instituições');
