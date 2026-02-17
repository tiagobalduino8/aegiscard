-- Criação da tabela de cartões
CREATE TABLE IF NOT EXISTS cards (
    id CHAR(36) NOT NULL,
    number VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_number UNIQUE (number)
);

INSERT INTO cards (id, number) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'MTIzNDU2Nzg5MDEyMzQ1Ng=='),
('660e8400-e29b-41d4-a716-446655440111', 'NDMyMTIzNDU2Nzg5MDEyMw==');

-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users (username, password) VALUES
('admin', '$2a$10$5AOcLa4vOlv5QC5Y29iT9emiUVhPgtjq1ashsrZqCC8tL14thSadO');
