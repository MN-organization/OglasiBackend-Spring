package service;

import lombok.AllArgsConstructor;
import modeli.Marka;
import modeli.Model;
import org.springframework.stereotype.Service;
import repozitorijumi.MarkaRepository;
import repozitorijumi.ModelRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MarkaModelService {

    private final MarkaRepository markaRepository;
    private final ModelRepository modelRepository;

    public List<Marka> vratiMarke() {
        List<Marka> marke = markaRepository.findAll();
        return marke;
    }

    public List<Model> vratiModeleZaMarku(Long idMarke) {
        List<Model> modeli = modelRepository.findAllByMarkaId(idMarke);
        return modeli;
    }
}
