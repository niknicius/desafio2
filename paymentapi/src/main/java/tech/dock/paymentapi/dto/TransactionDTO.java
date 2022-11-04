package tech.dock.paymentapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionDTO {
    private Integer id;
    private BigDecimal amount;
    private Date transactionDate;
}
