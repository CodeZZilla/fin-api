package kaf22.codezilla.finapi.models.protocolItem;

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
public class ProtocolItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfDecision;
    private String numberOfDecision;
    private Double sumOGD;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private Protocol protocol;

    @ManyToOne(fetch = FetchType.EAGER)
    private DecisionStatus decisionStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;
}
