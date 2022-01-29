package kaf22.codezilla.finapi.models.recipientOGD;

import kaf22.codezilla.finapi.models.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipientOGD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String name;
    private String surName;
    private LocalDate dateOfBirth;
    private Integer shareOfPayment;

    @ManyToOne(fetch = FetchType.EAGER)
    private FamilyCode familyCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private RecipientStatus recipientStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

}
