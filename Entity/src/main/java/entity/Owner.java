package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner {
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "owner_id")
//    @JsonBackReference
//    private Set<Cat> cats;

    @Transient
    private List<CatDTO> catDTOSet;

    public Owner(String name, Date date) {
        this.name = name;
        this.date = date;
    }
}

