CREATE TABLE IF NOT EXISTS `person` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` TEXT NOT NULL,
    `cpf` VARCHAR(14) NOT NULL ,
    `birth_date` DATETIME NOT NULL
);

INSERT INTO person (name, cpf, birth_date) VALUES ('Arya Stark', '710.543.934-35', '2000-04-01 00:00:00');
