package kaf22.codezilla.finapi.models.mainDocument;

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
public class ReasonForOGD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortReason;
    private String fullReason;

    @OneToMany(mappedBy = "reasonForOGD", cascade = CascadeType.ALL)
    private List<MainDocument> mainDocumentList;
}
