package modeli;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String marka;
    private String model;
    private int godiste;
    private int kilometraza;
    private String gorivo;
    private int snaga;
    private int kubikaza;
    private String menjac;
    private String slika;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}
