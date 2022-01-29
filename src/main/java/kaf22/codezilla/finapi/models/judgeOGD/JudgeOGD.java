package kaf22.codezilla.finapi.models.judgeOGD;

import kaf22.codezilla.finapi.models.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JudgeOGD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberAct;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private CourtCode courtCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAct typeAct;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;
}
