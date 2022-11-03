CREATE TABLE IF NOT EXISTS `transaction` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `accountID` INT NOT NULL,
    `amount` DECIMAL(10,2),
    `tansactionDate` DATETIME,
    CONSTRAINT transaction_account_FK FOREIGN KEY (accountID) REFERENCES `account` (id)
);
