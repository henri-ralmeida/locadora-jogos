-- V1__create-table-pizza.sql

CREATE TABLE IF NOT EXISTS jogo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE PRECISION NOT NULL,
    genero VARCHAR(50) NOT NULL,
    tipo_midia VARCHAR(50) NOT NULL,
    disponivel BOOLEAN NOT NULL,

    PRIMARY KEY(id)
);