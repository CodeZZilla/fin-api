package kaf22.codezilla.finapi.models;

import kaf22.codezilla.finapi.models.inputDocument.InputDocument;
import kaf22.codezilla.finapi.models.judgeOGD.JudgeOGD;
import kaf22.codezilla.finapi.models.mainDocument.MainDocument;
import kaf22.codezilla.finapi.models.mainDocument.property.Property;
import kaf22.codezilla.finapi.models.paymentsOGD.PaymentsOGD;
import kaf22.codezilla.finapi.models.protocolItem.ProtocolItem;
import kaf22.codezilla.finapi.models.recipientOGD.RecipientOGD;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;
    private String firstName;
    private String lastName;
    private String surName;
    private String firstNameCase;
    private String lastNameCase;
    private String surNameCase;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<InputDocument> inputDocuments;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<RecipientOGD> recipientOGDList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<ProtocolItem> protocolItemList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Property> propertyList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<MainDocument> mainDocumentList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PaymentsOGD> paymentsOGDList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<JudgeOGD> judgeOGDList;
}
