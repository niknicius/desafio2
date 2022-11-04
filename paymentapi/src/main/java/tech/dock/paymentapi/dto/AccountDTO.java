package tech.dock.paymentapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.dock.paymentapi.core.base.BaseDTO;
import tech.dock.paymentapi.models.Person;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AccountDTO extends BaseDTO{
    private Integer id;
    @JsonIgnoreProperties(value = {"accounts"})
    private Person person;
    private Boolean active = true;
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal withdrawLimit;
    private Integer accountType;
    private Date creationDate;
}
