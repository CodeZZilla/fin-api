package kaf22.codezilla.finapi.models.mainDocument;

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
public class MilitaryRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rank;
    private LocalDate date;

    @OneToMany(mappedBy = "militaryRank", cascade = CascadeType.ALL)
    private List<MainDocument> mainDocumentList;
}
