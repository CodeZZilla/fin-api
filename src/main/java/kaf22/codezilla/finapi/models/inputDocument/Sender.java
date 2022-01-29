package kaf22.codezilla.finapi.models.inputDocument;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<InputDocument> inputDocuments;
}
