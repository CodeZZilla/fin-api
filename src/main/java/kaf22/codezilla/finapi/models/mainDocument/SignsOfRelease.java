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
public class SignsOfRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String indication;

    @OneToMany(mappedBy = "signsOfRelease", cascade = CascadeType.ALL)
    private List<MainDocument> mainDocumentList;
}
