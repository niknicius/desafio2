package tech.dock.paymentapi.models;

import lombok.*;
import tech.dock.paymentapi.core.base.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction Model Class
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction extends BaseModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private BigDecimal amount;

    @Column
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

}
