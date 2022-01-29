package kaf22.codezilla.finapi.models.mainDocument;

import kaf22.codezilla.finapi.models.Person;
import kaf22.codezilla.finapi.models.mainDocument.property.Property;
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
public class MainDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String series;
    private Integer number;

    @Column(name = "group_gr")
    private Integer group;
    private Integer sizeGZ;
    private LocalDate dateOfEvent;
    private LocalDate releaseDate;
    private LocalDate dateDeterminedOGD;
    private LocalDate dateOfDecision;
    private String numberOfDecision;
    private Double subOGD;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private ReasonForOGD reasonForOGD;

    @ManyToOne(fetch = FetchType.EAGER)
    private SignsOfRelease signsOfRelease;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeOfMilitaryService typeOfMilitaryService;

    @ManyToOne(fetch = FetchType.EAGER)
    private MilitaryRank militaryRank;

    @ManyToOne(fetch = FetchType.EAGER)
    private MSECReview msecReview;

    @ManyToOne(fetch = FetchType.EAGER)
    private ObjectDecision objectDecision;

    @ManyToOne(fetch = FetchType.EAGER)
    private Property property;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;
}
