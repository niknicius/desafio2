package tech.dock.paymentapi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.dock.paymentapi.core.base.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Account extends BaseModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ToString.Exclude
    private Person person;

    @Column
    private BigDecimal balance;

    @Column(name = "withdraw_limit")
    private BigDecimal withdrawLimit;

    @Column(name = "account_type")
    private Integer accountType;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;



}