package kaf22.codezilla.finapi.models.mainDocument.property;

import kaf22.codezilla.finapi.models.Person;
import kaf22.codezilla.finapi.models.mainDocument.MainDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfDecision;
    private String numberOfDecision;
    private Double sumOGD;
    private String note;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<MainDocument> mainDocumentList;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeOGD typeOGD;

    @ManyToOne(fetch = FetchType.EAGER)
    private NPA npa;

    @ManyToOne(fetch = FetchType.EAGER)
    private StageOfElaboration stageOfElaboration;

    @ManyToOne(fetch = FetchType.EAGER)
    private Decision decision;
}
