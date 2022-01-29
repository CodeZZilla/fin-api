package kaf22.codezilla.finapi.models.paymentsOGD;

import kaf22.codezilla.finapi.models.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentsOGD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfPayment;
    private String number;
    private Double assignedAmount;
    private Double paidAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.EAGER)
    private AuthorizedBody authorizedBody;
}
