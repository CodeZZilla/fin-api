package kaf22.codezilla.finapi.models.mainDocument.property;

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
public class NPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortName;
    private String fullName;

    @OneToMany(mappedBy = "npa", cascade = CascadeType.ALL)
    private List<Property> propertyList;
}
