package service;

import lombok.AllArgsConstructor;
import modeli.Oglas;
import modeli.SacuvaniOglas;
import modeli.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
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
        List<Oglas> oglasi = oglasiRepository.findAll();
        if (authService.getCurrentUser() != null) {
            List<SacuvaniOglas> sacuvaniOglasi = sacuvaniOglasRepository.findByUser(authService.getCurrentUser());
            oglasi.forEach(oglas -> {
                sacuvaniOglasi.forEach(sacuvaniOglas -> {
                    if (oglas.getId() == sacuvaniOglas.getOglas().getId()) {
                        oglas.setSacuvan(true);
                    }
                });
            });
        }
        return oglasi;
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
            } else {
                throw new RuntimeException("Moguce je brisati samo sopstvene oglase");
            }
        } else {
            throw new RuntimeException("Nije moguce brisati nepostojeci oglas");
        }
        return "Oglas je uspesno izbrisan";
    }

    @Transactional
    public Oglas izmeniOglas(Oglas oglas) {
        Optional<Oglas> og = oglasiRepository.findById(oglas.getId());
        if (og.isPresent()) {
            if (og.get().getUser().getId() == authService.getCurrentUser().getId()) {
                Oglas o = og.get();
                o.setNaslov(oglas.getNaslov());
                o.setOpis(oglas.getOpis());
                o.setCena(oglas.getCena());
//                o.setMarka(oglas.getMarka());
                o.setModel(oglas.getModel());
                o.setGodiste(oglas.getGodiste());
                o.setKilometraza(oglas.getKilometraza());
                o.setGorivo(oglas.getGorivo());
                o.setSnaga(oglas.getSnaga());
                o.setKubikaza(oglas.getKubikaza());
                o.setMenjac(oglas.getMenjac());
                return oglasiRepository.save(o);
            } else {
                throw new RuntimeException("Moguce je menjati samo sopstvene oglase");
            }
        } else {
            throw new RuntimeException("Nije moguce izmeniti nepostojeci oglas");
        }
    }

    @Transactional(readOnly = true)
    public List<Oglas> vratiMojeOglase() {
        System.out.println(oglasiRepository.findByUser(authService.getCurrentUser()));
        return oglasiRepository.findByUser(authService.getCurrentUser());
    }

    @Transactional(readOnly = true)
    public List<Oglas> vratiSacuvaneOglase() {
        List<SacuvaniOglas> sacuvaniOglasi = sacuvaniOglasRepository.findByUser(authService.getCurrentUser());
        ArrayList<Oglas> oglasi = new ArrayList<>();
        sacuvaniOglasi.forEach(sacuvaniOglas -> oglasi.add(sacuvaniOglas.getOglas()));
        return oglasi;
    }

    @Transactional
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

    @Transactional
    public String izbrisiSacuvan(Long id) {

        SacuvaniOglas so = new SacuvaniOglas();
        so.setUser(authService.getCurrentUser());
        Optional<Oglas> o = oglasiRepository.findById(id);
        if (o.isPresent()) {
            so.setOglas(o.get());
            Example<SacuvaniOglas> example = Example.of(so);
            Optional<SacuvaniOglas> s = sacuvaniOglasRepository.findOne(example);// radi ali moze verovatno bez example
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

    @Transactional(readOnly = true)
    public List<Oglas> pretraga(Specification<Oglas> kriterijumi) {
        List<Oglas> oglasi = oglasiRepository.findAll(Specification.where(kriterijumi));
        if (authService.getCurrentUser() != null) {
            List<SacuvaniOglas> sacuvaniOglasi = sacuvaniOglasRepository.findByUser(authService.getCurrentUser());
            oglasi.forEach(oglas -> {
                sacuvaniOglasi.forEach(sacuvaniOglas -> {
                    if (oglas.getId() == sacuvaniOglas.getOglas().getId()) {
                        oglas.setSacuvan(true);
                    }
                });
            });
        }
        return oglasi;
    }
}
