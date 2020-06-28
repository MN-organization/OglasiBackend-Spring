package service;

import lombok.AllArgsConstructor;
import modeli.AktivnostOglasa;
import modeli.Oglas;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import repozitorijumi.AktivnostOglasaRepository;
import repozitorijumi.OglasiRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AktivnostOglasaService {

    private final AktivnostOglasaRepository aktivnostOglasaRepository;
    private final OglasiRepository oglasiRepository;

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        List<AktivnostOglasa> listaAkt = aktivnostOglasaRepository.findAllByVremeIsticanjaBefore(new Date());
        System.out.println(listaAkt);
        listaAkt.forEach(aktivnostOglasa -> {
            Optional<Oglas> oglas = oglasiRepository.findByAktivnostOglasa(aktivnostOglasa);
            if(oglas.isPresent()) {
                oglas.get().setAktivan(false);
                oglasiRepository.save(oglas.get());
            }
        });
    }

}
