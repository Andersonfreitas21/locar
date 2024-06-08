CREATE TABLE veiculos (
    id SERIAL PRIMARY KEY,
    chassi VARCHAR(255) NOT NULL,
    placa VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    ano_fabricacao VARCHAR(4),
    km_atual INTEGER,
    id_marca INTEGER NOT NULL,
    id_modelo INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
