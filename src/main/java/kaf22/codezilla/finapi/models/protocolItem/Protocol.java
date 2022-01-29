package kaf22.codezilla.finapi.models.protocolItem;

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
public class Protocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private LocalDate date;

    @OneToMany(mappedBy = "protocol", cascade = CascadeType.ALL)
    private List<ProtocolItem> protocolItemList;
}
