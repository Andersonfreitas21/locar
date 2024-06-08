create table modelos
(
    id serial primary key,
    nome varchar not null,
    created_at timestamp,
    updated_at timestamp
);