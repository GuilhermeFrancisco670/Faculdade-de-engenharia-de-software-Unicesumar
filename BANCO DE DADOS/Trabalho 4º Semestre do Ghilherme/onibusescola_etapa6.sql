DROP DATABASE IF EXISTS onibusescola;
CREATE DATABASE onibusescola
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE onibusescola;


CREATE TABLE endereco (
  id_endereco  INT           AUTO_INCREMENT PRIMARY KEY,
  cep          CHAR(8)       NOT NULL,
  rua          VARCHAR(100)  NOT NULL,
  numero_casa  VARCHAR(10)   NOT NULL,
  bairro       VARCHAR(60)   NULL,
  cidade       VARCHAR(60)   NOT NULL,
  uf           CHAR(2)       NOT NULL,
  descricao    VARCHAR(150)  NULL,
  CONSTRAINT chk_endereco_cep CHECK (CHAR_LENGTH(cep) = 8)
) ENGINE = InnoDB;

CREATE TABLE escola (
  id_escola        INT          AUTO_INCREMENT PRIMARY KEY,
  nome             VARCHAR(100) NOT NULL,
  id_endereco      INT          NOT NULL,
  quantidade_aluno INT          NOT NULL DEFAULT 0,
  turno            ENUM('MANHA','TARDE','NOITE','INTEGRAL') NOT NULL,
  horario_entrada  TIME         NOT NULL,
  horario_saida    TIME         NOT NULL,
  publico          CHAR(1)      NOT NULL DEFAULT 'S',
  CONSTRAINT chk_escola_publico  CHECK (publico IN ('S','N')),
  CONSTRAINT chk_escola_qtd      CHECK (quantidade_aluno >= 0),
  CONSTRAINT chk_escola_horario  CHECK (horario_saida > horario_entrada)
) ENGINE = InnoDB;

CREATE TABLE responsavel (
  id_responsavel  INT          AUTO_INCREMENT PRIMARY KEY,
  nome            VARCHAR(100) NOT NULL,
  numero_celular  VARCHAR(15)  NOT NULL,
  id_endereco     INT          NOT NULL,
  horario_em_casa TIME         NULL,
  CONSTRAINT uq_responsavel_celular UNIQUE (numero_celular)
) ENGINE = InnoDB;

CREATE TABLE onibus (
  id_onibus  INT     AUTO_INCREMENT PRIMARY KEY,
  placa      CHAR(7) NOT NULL,
  capacidade INT     NOT NULL,
  CONSTRAINT uq_onibus_placa   UNIQUE (placa),
  CONSTRAINT chk_onibus_cap    CHECK (capacidade > 0)
) ENGINE = InnoDB;

/* mud_onibus do modelo original virou a FK id_onibus:
   trocar o motorista de onibus = atualizar essa coluna     */
CREATE TABLE motorista (
  id_motorista INT          AUTO_INCREMENT PRIMARY KEY,
  nome         VARCHAR(100) NOT NULL,
  cnh          CHAR(11)     NOT NULL,
  telefone     VARCHAR(15)  NULL,
  id_onibus    INT          NULL,
  CONSTRAINT uq_motorista_cnh UNIQUE (cnh)
) ENGINE = InnoDB;

CREATE TABLE rota (
  id_rota       INT          AUTO_INCREMENT PRIMARY KEY,
  descricao     VARCHAR(150) NOT NULL,
  turno         ENUM('MANHA','TARDE','NOITE') NOT NULL,
  horario_saida TIME         NOT NULL,
  id_motorista  INT          NOT NULL,
  id_escola     INT          NOT NULL
) ENGINE = InnoDB;

CREATE TABLE aluno (
  id_aluno        INT          AUTO_INCREMENT PRIMARY KEY,
  nome            VARCHAR(100) NOT NULL,
  data_nascimento DATE         NULL,
  turno           ENUM('MANHA','TARDE','NOITE') NOT NULL,
  id_escola       INT          NOT NULL,
  id_responsavel  INT          NOT NULL,
  id_endereco     INT          NOT NULL
) ENGINE = InnoDB;

/* aluno x rota e' N:N -> tabela associativa */
CREATE TABLE aluno_rota (
  id_aluno       INT NOT NULL,
  id_rota        INT NOT NULL,
  ordem_embarque INT NULL,
  PRIMARY KEY (id_aluno, id_rota)
) ENGINE = InnoDB;

CREATE TABLE log_matricula (
  id_log     INT          AUTO_INCREMENT PRIMARY KEY,
  id_aluno   INT          NOT NULL,
  id_escola  INT          NOT NULL,
  acao       VARCHAR(20)  NOT NULL,
  data_hora  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;


ALTER TABLE escola
  ADD CONSTRAINT fk_escola_endereco
      FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE responsavel
  ADD CONSTRAINT fk_responsavel_endereco
      FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE motorista
  ADD CONSTRAINT fk_motorista_onibus
      FOREIGN KEY (id_onibus) REFERENCES onibus (id_onibus)
      ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE rota
  ADD CONSTRAINT fk_rota_motorista
      FOREIGN KEY (id_motorista) REFERENCES motorista (id_motorista)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  ADD CONSTRAINT fk_rota_escola
      FOREIGN KEY (id_escola) REFERENCES escola (id_escola)
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE aluno
  ADD CONSTRAINT fk_aluno_escola
      FOREIGN KEY (id_escola) REFERENCES escola (id_escola)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  ADD CONSTRAINT fk_aluno_responsavel
      FOREIGN KEY (id_responsavel) REFERENCES responsavel (id_responsavel)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  ADD CONSTRAINT fk_aluno_endereco
      FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE aluno_rota
  ADD CONSTRAINT fk_alunorota_aluno
      FOREIGN KEY (id_aluno) REFERENCES aluno (id_aluno)
      ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT fk_alunorota_rota
      FOREIGN KEY (id_rota) REFERENCES rota (id_rota)
      ON UPDATE CASCADE ON DELETE CASCADE;

/* indices de apoio */
CREATE INDEX idx_aluno_nome  ON aluno (nome);
CREATE INDEX idx_aluno_turno ON aluno (turno);
CREATE INDEX idx_rota_turno  ON rota (turno);


INSERT INTO endereco (cep, rua, numero_casa, bairro, cidade, uf, descricao) VALUES
('87020150','Avenida Brasil','1200','Centro','Maringa','PR','Escola municipal'),
('87030220','Rua Neo Alves Martins','340','Zona 04','Maringa','PR','Escola estadual'),
('87045110','Avenida Colombo','980','Zona 07','Maringa','PR','Colegio particular'),
('87050310','Rua Piratininga','45','Zona 05','Maringa','PR','Casa de esquina'),
('87050320','Rua Piratininga','88','Zona 05','Maringa','PR','Sobrado azul'),
('87060400','Rua Pioneiro Jose Ferreira','512','Zona 06','Maringa','PR','Portao branco'),
('87070500','Rua Guaira','77','Zona 08','Maringa','PR','Apartamento 12'),
('87080600','Rua Bento Munhoz','230','Vila Morangueira','Maringa','PR','Casa dos fundos');

INSERT INTO escola (nome, id_endereco, turno, horario_entrada, horario_saida, publico) VALUES
('EM Padre Manoel', 1, 'MANHA',    '07:30:00','11:45:00','S'),
('CE Silvio Barros', 2, 'TARDE',    '13:00:00','17:20:00','S'),
('Colegio Horizonte',3, 'INTEGRAL','07:45:00','17:00:00','N');

INSERT INTO responsavel (nome, numero_celular, id_endereco, horario_em_casa) VALUES
('Marcia Ferreira',  '44991110001', 4, '18:00:00'),
('Joao Batista Reis','44991110002', 5, '17:30:00'),
('Cleide Souza',     '44991110003', 6, '19:00:00'),
('Rodrigo Almeida',  '44991110004', 7, '18:30:00'),
('Patricia Nunes',   '44991110005', 8, '16:45:00');

INSERT INTO onibus (placa, capacidade) VALUES
('AAA1B23', 30),
('BBB2C34', 24),
('CCC3D45', 40);

INSERT INTO motorista (nome, cnh, telefone, id_onibus) VALUES
('Antonio Carlos Lima','01234567890','44992220001', 1),
('Sebastiao Ribeiro',  '01234567891','44992220002', 2),
('Marta Oliveira',     '01234567892','44992220003', 3);

INSERT INTO rota (descricao, turno, horario_saida, id_motorista, id_escola) VALUES
('Zona 05 -> EM Padre Manoel',        'MANHA','06:30:00', 1, 1),
('Zona 06/07 -> CE Silvio Barros',    'TARDE','12:00:00', 2, 2),
('Vila Morangueira -> Col. Horizonte','MANHA','06:50:00', 3, 3);

INSERT INTO aluno (nome, data_nascimento, turno, id_escola, id_responsavel, id_endereco) VALUES
('Lucas Ferreira',  '2014-03-12','MANHA', 1, 1, 4),
('Ana Ferreira',    '2016-07-25','MANHA', 1, 1, 4),
('Pedro Reis',      '2013-11-02','MANHA', 1, 2, 5),
('Julia Souza',     '2012-05-19','TARDE', 2, 3, 6),
('Gabriel Souza',   '2015-01-30','TARDE', 2, 3, 6),
('Bianca Almeida',  '2013-09-08','TARDE', 2, 4, 7),
('Enzo Nunes',      '2011-04-14','MANHA', 3, 5, 8),
('Helena Nunes',    '2017-02-27','MANHA', 3, 5, 8);

INSERT INTO aluno_rota (id_aluno, id_rota, ordem_embarque) VALUES
(1,1,1),(2,1,2),(3,1,3),
(4,2,1),(5,2,2),(6,2,3),
(7,3,1),(8,3,2);


-- 4.1 SELECTs simples ------------------------------------
SELECT * FROM aluno;

SELECT nome, turno FROM aluno WHERE turno = 'MANHA' ORDER BY nome;

SELECT placa, capacidade FROM onibus WHERE capacidade >= 30;

SELECT nome, numero_celular, horario_em_casa
FROM   responsavel
WHERE  horario_em_casa <= '18:00:00';

SELECT COUNT(*) AS total_alunos FROM aluno;


-- aluno + escola + responsavel
SELECT a.nome        AS aluno,
       e.nome        AS escola,
       r.nome        AS responsavel,
       r.numero_celular
FROM   aluno a
JOIN   escola e      ON e.id_escola      = a.id_escola
JOIN   responsavel r ON r.id_responsavel = a.id_responsavel
ORDER  BY e.nome, a.nome;

-- aluno + endereco (onde o onibus busca cada aluno)
SELECT a.nome AS aluno,
       CONCAT(en.rua,', ',en.numero_casa,' - ',en.bairro) AS ponto_de_embarque,
       en.cep
FROM   aluno a
JOIN   endereco en ON en.id_endereco = a.id_endereco
ORDER  BY en.rua, en.numero_casa;

-- rota + motorista + onibus + escola
SELECT r.descricao       AS rota,
       r.horario_saida,
       m.nome            AS motorista,
       o.placa,
       o.capacidade,
       e.nome            AS escola
FROM   rota r
JOIN   motorista m ON m.id_motorista = r.id_motorista
LEFT   JOIN onibus o ON o.id_onibus  = m.id_onibus
JOIN   escola e    ON e.id_escola    = r.id_escola;

-- ocupacao de cada rota (5 tabelas + agregacao)
SELECT r.descricao          AS rota,
       o.placa,
       o.capacidade,
       COUNT(ar.id_aluno)   AS alunos,
       o.capacidade - COUNT(ar.id_aluno) AS vagas
FROM   rota r
JOIN   motorista m  ON m.id_motorista = r.id_motorista
JOIN   onibus o     ON o.id_onibus    = m.id_onibus
LEFT   JOIN aluno_rota ar ON ar.id_rota = r.id_rota
GROUP  BY r.id_rota, r.descricao, o.placa, o.capacidade
ORDER  BY vagas;

-- alunos sem rota atribuida
SELECT a.nome AS aluno, e.nome AS escola
FROM   aluno a
JOIN   escola e ON e.id_escola = a.id_escola
LEFT   JOIN aluno_rota ar ON ar.id_aluno = a.id_aluno
WHERE  ar.id_aluno IS NULL;

-- irmaos: responsaveis com mais de um filho
SELECT r.nome AS responsavel,
       COUNT(a.id_aluno) AS filhos,
       GROUP_CONCAT(a.nome ORDER BY a.nome) AS nomes
FROM   responsavel r
JOIN   aluno a ON a.id_responsavel = r.id_responsavel
GROUP  BY r.id_responsavel, r.nome
HAVING COUNT(a.id_aluno) > 1;


DELIMITER $$

-- 5.1 mantem escola.quantidade_aluno sempre correto
CREATE TRIGGER trg_aluno_ai
AFTER INSERT ON aluno
FOR EACH ROW
BEGIN
  UPDATE escola
     SET quantidade_aluno = quantidade_aluno + 1
   WHERE id_escola = NEW.id_escola;

  INSERT INTO log_matricula (id_aluno, id_escola, acao)
  VALUES (NEW.id_aluno, NEW.id_escola, 'MATRICULA');
END$$

CREATE TRIGGER trg_aluno_ad
AFTER DELETE ON aluno
FOR EACH ROW
BEGIN
  UPDATE escola
     SET quantidade_aluno = quantidade_aluno - 1
   WHERE id_escola = OLD.id_escola;

  INSERT INTO log_matricula (id_aluno, id_escola, acao)
  VALUES (OLD.id_aluno, OLD.id_escola, 'CANCELAMENTO');
END$$

-- 5.2 transferencia de escola: ajusta os dois contadores
CREATE TRIGGER trg_aluno_au
AFTER UPDATE ON aluno
FOR EACH ROW
BEGIN
  IF NEW.id_escola <> OLD.id_escola THEN
    UPDATE escola SET quantidade_aluno = quantidade_aluno - 1
      WHERE id_escola = OLD.id_escola;
    UPDATE escola SET quantidade_aluno = quantidade_aluno + 1
      WHERE id_escola = NEW.id_escola;

    INSERT INTO log_matricula (id_aluno, id_escola, acao)
    VALUES (NEW.id_aluno, NEW.id_escola, 'TRANSFERENCIA');
  END IF;
END$$

-- 5.3 impede embarcar aluno acima da capacidade do onibus
CREATE TRIGGER trg_aluno_rota_bi
BEFORE INSERT ON aluno_rota
FOR EACH ROW
BEGIN
  DECLARE v_capacidade INT DEFAULT 0;
  DECLARE v_ocupados   INT DEFAULT 0;

  SELECT IFNULL(o.capacidade, 0) INTO v_capacidade
  FROM   rota r
  JOIN   motorista m ON m.id_motorista = r.id_motorista
  LEFT   JOIN onibus o ON o.id_onibus  = m.id_onibus
  WHERE  r.id_rota = NEW.id_rota;

  SELECT COUNT(*) INTO v_ocupados
  FROM   aluno_rota
  WHERE  id_rota = NEW.id_rota;

  IF v_ocupados >= v_capacidade THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Onibus lotado: capacidade da rota atingida.';
  END IF;
END$$

DELIMITER ;

/* como os alunos foram inseridos antes das triggers existirem,
   sincroniza o contador uma unica vez                        */
UPDATE escola e
   SET e.quantidade_aluno = (SELECT COUNT(*) FROM aluno a
                              WHERE a.id_escola = e.id_escola);


DELIMITER $$

-- 6.1 matricula o aluno e ja o coloca em uma rota
CREATE PROCEDURE sp_matricular_aluno (
  IN  p_nome           VARCHAR(100),
  IN  p_nascimento     DATE,
  IN  p_turno          VARCHAR(10),
  IN  p_id_escola      INT,
  IN  p_id_responsavel INT,
  IN  p_id_endereco    INT,
  IN  p_id_rota        INT,
  OUT p_id_aluno       INT
)
BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    RESIGNAL;
  END;

  IF NOT EXISTS (SELECT 1 FROM escola WHERE id_escola = p_id_escola) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Escola inexistente.';
  END IF;

  IF NOT EXISTS (SELECT 1 FROM responsavel
                  WHERE id_responsavel = p_id_responsavel) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Responsavel inexistente.';
  END IF;

  IF p_id_rota IS NOT NULL
     AND NOT EXISTS (SELECT 1 FROM rota WHERE id_rota = p_id_rota) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Rota inexistente.';
  END IF;

  START TRANSACTION;

    INSERT INTO aluno (nome, data_nascimento, turno,
                       id_escola, id_responsavel, id_endereco)
    VALUES (p_nome, p_nascimento, p_turno,
            p_id_escola, p_id_responsavel, p_id_endereco);

    SET p_id_aluno = LAST_INSERT_ID();

    IF p_id_rota IS NOT NULL THEN
      INSERT INTO aluno_rota (id_aluno, id_rota, ordem_embarque)
      VALUES (p_id_aluno, p_id_rota,
              (SELECT IFNULL(MAX(ordem_embarque),0) + 1
                 FROM aluno_rota WHERE id_rota = p_id_rota));
    END IF;

  COMMIT;
END$$

-- 6.2 lista de embarque de uma rota (para o motorista)
CREATE PROCEDURE sp_lista_embarque (IN p_id_rota INT)
BEGIN
  SELECT ar.ordem_embarque       AS ordem,
         a.nome                  AS aluno,
         CONCAT(en.rua,', ',en.numero_casa) AS endereco,
         en.bairro,
         r.nome                  AS responsavel,
         r.numero_celular,
         e.nome                  AS escola,
         ro.horario_saida
  FROM   aluno_rota ar
  JOIN   rota  ro      ON ro.id_rota       = ar.id_rota
  JOIN   aluno a       ON a.id_aluno       = ar.id_aluno
  JOIN   endereco en   ON en.id_endereco   = a.id_endereco
  JOIN   responsavel r ON r.id_responsavel = a.id_responsavel
  JOIN   escola e      ON e.id_escola      = a.id_escola
  WHERE  ar.id_rota = p_id_rota
  ORDER  BY ar.ordem_embarque;
END$$

-- 6.3 troca o motorista de onibus (o antigo mud_onibus)
-- O mesmo onibus pode ser compartilhado por varios motoristas.
CREATE PROCEDURE sp_trocar_onibus (
  IN p_id_motorista INT,
  IN p_id_onibus    INT
)
BEGIN
  IF NOT EXISTS (SELECT 1 FROM onibus WHERE id_onibus = p_id_onibus) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Onibus inexistente.';
  END IF;

  UPDATE motorista
     SET id_onibus = p_id_onibus
   WHERE id_motorista = p_id_motorista;
END$$

DELIMITER ;

