CREATE TABLE IF NOT EXISTS `transaction` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `account_id` INT NOT NULL,
    `amount` DECIMAL(10,2),
    `tansaction_date` DATETIME,
    CONSTRAINT transaction_account_FK FOREIGN KEY (account_id) REFERENCES `account` (id)
);
