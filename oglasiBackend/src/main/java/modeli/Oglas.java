package modeli;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Oglas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naslov;
    @Nullable
    private String opis;
    private double cena;
//    private String marka;
//    private String model;
    @OneToOne
    private Model model;
    private int godiste;
    private int kilometraza;
    @ManyToOne
    private Gorivo gorivo;
    private int snaga;
    private int kubikaza;
    @ManyToOne
    private Menjac menjac;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Slika> slike;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    @Transient
    private boolean sacuvan;
    @OneToOne(cascade = CascadeType.ALL)
    private AktivnostOglasa aktivnostOglasa;
    private boolean aktivan;
}
