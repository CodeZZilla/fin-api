package kaf22.codezilla.finapi.models.inputDocument;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class InputDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateInput;
    private Long numberInput;

    private LocalDateTime dateOutput;
    private Long numberOutput;


    @ManyToOne(fetch = FetchType.EAGER)
    private Sender sender;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeDocument typeDocument;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

}
