create table marcas
(
    id serial primary key,
    nome varchar not null,
    created_at timestamp,
    updated_at timestamp
);

INSERT INTO marcas(nome, created_at) VALUES ('CHEVROLET', CURRENT_TIMESTAMP);