package tech.dock.paymentapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.dock.paymentapi.core.base.BaseDTO;
import tech.dock.paymentapi.models.Account;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String cpf;
    private Date birthDate;
    private List<Account> accounts;

    public PersonDTO(String name, String cpf, Date birthDate) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }
}
