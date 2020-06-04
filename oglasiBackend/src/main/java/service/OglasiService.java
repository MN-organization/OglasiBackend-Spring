package service;

import lombok.AllArgsConstructor;
import modeli.Oglas;
import modeli.SacuvaniOglas;
import modeli.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repozitorijumi.OglasiRepository;
import repozitorijumi.SacuvaniOglasRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OglasiService {

    private final OglasiRepository oglasiRepository;
    private final SacuvaniOglasRepository sacuvaniOglasRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<Oglas> vratiSve() {
        return oglasiRepository.findAll();
    }

    @Transactional
    public Oglas save(Oglas oglas) {
        oglas.setUser(authService.getCurrentUser());
        return oglasiRepository.save(oglas);
    }

    @Transactional(readOnly = true)
    public Oglas vratiOglas(Long id) {
        Optional<Oglas> o = oglasiRepository.findById(id);
        return o.get();
    }

    @Transactional
    public String izbrisiOglas(Long id) {
        Optional<Oglas> o = oglasiRepository.findById(id);
        if (o.isPresent()) {
            if (o.get().getUser().getId() == authService.getCurrentUser().getId()) {
                oglasiRepository.deleteById(id);
                ;
            } else {
                throw new RuntimeException("Moguce je brisati samo sopstvene oglase");
            }
        } else {
            throw new RuntimeException("Nije moguce brisati nepostojeci oglas");
        }
        return "Oglas je uspesno izbrisan";
    }

    @Transactional
    public Oglas izmeniOglas(Long id) {
        Optional<Oglas> o = oglasiRepository.findById(id);
        if (o.isPresent()) {
            if (o.get().getUser().getId() == authService.getCurrentUser().getId()) {
                return oglasiRepository.save(o.get());
            } else {
                throw new RuntimeException("Moguce je menjati samo sopstvene oglase");
            }
        } else {
            throw new RuntimeException("Nije moguce izmeniti nepostojeci oglas");
        }
    }


    public List<Oglas> vratiMojeOglase() {
        Oglas og = new Oglas();
        og.setUser(authService.getCurrentUser());
        Example<Oglas> example = Example.of(og);
        return oglasiRepository.findAll(example);
    }//proveriti


    public List<Oglas> vratiSacuvaneOglase() {
        SacuvaniOglas so = new SacuvaniOglas();
        so.setUser(authService.getCurrentUser());
        Example<SacuvaniOglas> example = Example.of(so);
        List<Oglas> oglasi = new ArrayList<>();
        sacuvaniOglasRepository.findAll(example).forEach(sacuvaniOglas -> oglasi.add(sacuvaniOglas.getOglas()));
        return oglasi;/// videti da li moze bolji upit preko join
    }


    public String sacuvaj(Long id) {
        Optional<Oglas> o = oglasiRepository.findById(id);
        if (o.isPresent()) {
            SacuvaniOglas so = new SacuvaniOglas(null, authService.getCurrentUser(), o.get());
            sacuvaniOglasRepository.save(so);
            return "Oglas uspesno sacuvan";
        } else {
            return "Oglas ne postoji";
        }
    }


    public String izbrisiSacuvan(Long id) {

        SacuvaniOglas so = new SacuvaniOglas();
        so.setUser(authService.getCurrentUser());
        Optional<Oglas> o = oglasiRepository.findById(id);
        if (o.isPresent()) {
            so.setOglas(o.get());
            Example<SacuvaniOglas> example = Example.of(so);
            Optional<SacuvaniOglas> s = sacuvaniOglasRepository.findOne(example);
            if (s.isPresent()) {
                sacuvaniOglasRepository.delete(s.get());
            } else {
                return "Neuspesno brisanje";
            }
            return "Oglas je uspesno izbrisan iz sacuvanih";
        } else {
            return "Greska-Oglas ne postoji";
        }
    }
}
