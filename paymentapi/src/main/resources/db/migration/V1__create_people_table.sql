CREATE TABLE IF NOT EXISTS `person` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` TEXT,
    `cpf` VARCHAR(14),
    `birth_date` DATETIME
);

INSERT INTO person (name, cpf, birth_date) VALUES ('Arya Stark', '710.543.934-35', '2000-04-01 00:00:00');
