package kaf22.codezilla.finapi.models.recipientOGD;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String familyCode;

    @OneToMany(mappedBy = "familyCode", cascade = CascadeType.ALL)
    private List<RecipientOGD> recipientOGDList;
}
