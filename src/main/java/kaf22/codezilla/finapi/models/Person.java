package kaf22.codezilla.finapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kaf22.codezilla.finapi.models.inputDocument.InputDocument;
import kaf22.codezilla.finapi.models.judgeOGD.JudgeOGD;
import kaf22.codezilla.finapi.models.mainDocument.MainDocument;
import kaf22.codezilla.finapi.models.mainDocument.property.Property;
import kaf22.codezilla.finapi.models.paymentsOGD.PaymentsOGD;
import kaf22.codezilla.finapi.models.protocolItem.ProtocolItem;
import kaf22.codezilla.finapi.models.recipientOGD.RecipientOGD;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String uniqueCode;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String surName;
    private String firstNameCase;
    private String lastNameCase;
    private String surNameCase;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"dateInput", "numberInput", "dateOutput", "numberOutput", "sender", "typeDocument", "person"})
    @JsonIgnore
    private List<InputDocument> inputDocuments;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"lastName", "name", "surName", "dateOfBirth", "shareOfPayment", "familyCode",
//            "recipientStatus", "person"})
    @JsonIgnore
    private List<RecipientOGD> recipientOGDList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"dateOfDecision", "numberOfDecision", "sumOGD", "note", "protocol", "decisionStatus", "person"})
    @JsonIgnore
    private List<ProtocolItem> protocolItemList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"dateOfDecision", "numberOfDecision", "sumOGD", "note", "mainDocumentList", "typeOGD",
//            "person", "npa", "stageOfElaboration", "decision"})
    @JsonIgnore
    private List<Property> propertyList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"date", "series", "number", "group", "sizeGZ", "dateOfEvent", "releaseDate",
//            "dateDeterminedOGD", "dateOfDecision", "numberOfDecision", "subOGD", "text", "reasonForOGD",
//            "signsOfRelease", "typeOfMilitaryService",
//    "militaryRank", "msecReview", "objectDecision", "property", "person"})
    @JsonIgnore
    private List<MainDocument> mainDocumentList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"dateOfPayment", "number", "assignedAmount", "paidAmount", "person", "paymentType", "authorizedBody"})
    @JsonIgnore
    private List<PaymentsOGD> paymentsOGDList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"numberAct", "note", "courtCode", "typeAct", "person"})
    @JsonIgnore
    private List<JudgeOGD> judgeOGDList;

//    @Override
//    public String toString() {
//        return getClass().getSimpleName() + "(" +
//                "id = " + id + ", " +
//                "uniqueCode = " + uniqueCode + ", " +
//                "firstName = " + firstName + ", " +
//                "lastName = " + lastName + ", " +
//                "surName = " + surName + ", " +
//                "firstNameCase = " + firstNameCase + ", " +
//                "lastNameCase = " + lastNameCase + ", " +
//                "surNameCase = " + surNameCase + ", " +
//                "dateOfBirth = " + dateOfBirth + ")";
//    }
}
