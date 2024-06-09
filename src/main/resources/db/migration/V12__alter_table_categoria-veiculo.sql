ALTER TABLE if EXISTS veiculos
    ADD COLUMN cat VARCHAR(1) CHECK (cat IN ('A','B','C','D'));

UPDATE veiculos SET cat = 'A' WHERE id IN (1, 2, 3, 5);

