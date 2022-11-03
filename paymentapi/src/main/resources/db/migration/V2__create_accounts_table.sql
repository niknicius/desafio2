CREATE TABLE IF NOT EXISTS `account` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `person_id` INT NOT NULL,
    `balance` DECIMAL(10,2),
    `withdrawal_limit` DECIMAL(10,2),
    `active` BOOLEAN,
    `account_type` INT,
    `creation_date` DATETIME,
    CONSTRAINT account_person_FK FOREIGN KEY (person_id) REFERENCES `person` (id)
);
