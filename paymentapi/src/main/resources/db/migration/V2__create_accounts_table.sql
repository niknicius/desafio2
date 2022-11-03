CREATE TABLE IF NOT EXISTS `account` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `personID` INT NOT NULL,
    `balance` DECIMAL(10,2),
    `withdrawalLimit` DECIMAL(10,2),
    `active` BOOLEAN,
    `accountType` INT,
    `creationDate` DATETIME,
    CONSTRAINT account_person_FK FOREIGN KEY (personID) REFERENCES `person` (id)
);
