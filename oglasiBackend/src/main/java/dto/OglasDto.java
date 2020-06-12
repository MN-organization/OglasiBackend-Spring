package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import modeli.Oglas;
import modeli.Slika;
import modeli.User;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OglasDto {
    private Long id;
    private String naslov;
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
    private User user;
    private List<String> slike;

//    public static OglasDto oglasToDto(Oglas oglas) {
//        OglasDto oglasDto = new OglasDto();
//        oglasDto.setId(oglas.getId());
//        oglasDto.setNaslov(oglas.getNaslov());
//        oglasDto.setOpis(oglas.getOpis());
//        oglasDto.setCena(oglas.getCena());
//        oglasDto.setMarka(oglas.getMarka());
//        oglasDto.setModel(oglas.getModel());
//        oglasDto.setGodiste(oglas.getGodiste());
//        oglasDto.setKilometraza(oglas.getKilometraza());
//        oglasDto.setGorivo(oglas.getGorivo());
//        oglasDto.setSnaga(oglas.getSnaga());
//        oglasDto.setKubikaza(oglas.getKubikaza());
//        oglasDto.setMenjac(oglas.getMenjac());
//        oglasDto.setUser(oglas.getUser());
//
//        return oglasDto;
//    }
//
//    public static List<OglasDto> oglasiToDto(List<Oglas> oglasi) {
//        List<OglasDto> oglasiDto = new ArrayList<>();
//
//
//
//        return oglasiDto;
//    }

    public static Oglas dtoToOglas(OglasDto oglasDto) {
        Oglas o = new Oglas();
        o.setId(oglasDto.getId());
        o.setNaslov(oglasDto.getNaslov());
        o.setOpis(oglasDto.getOpis());
        o.setCena(oglasDto.getCena());
        o.setMarka(oglasDto.getMarka());
        o.setModel(oglasDto.getModel());
        o.setGodiste(oglasDto.getGodiste());
        o.setKilometraza(oglasDto.getKilometraza());
        o.setGorivo(oglasDto.getGorivo());
        o.setSnaga(oglasDto.getSnaga());
        o.setKubikaza(oglasDto.getKubikaza());
        o.setMenjac(oglasDto.getMenjac());
        o.setUser(oglasDto.getUser());

        return o;
    }

}
