package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import modeli.Marka;
import modeli.Model;
import modeli.Oglas;
import modeli.Slika;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private List<Oglas> oglasi;
    private String poruka;
    private Oglas oglas;
    private String hes;
    private Slika slika;
    private List<Marka> marke;
    private List<Model> modeli;
}
