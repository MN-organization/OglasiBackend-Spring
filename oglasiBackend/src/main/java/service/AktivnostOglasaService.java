package service;

import lombok.AllArgsConstructor;
import modeli.AktivnostOglasa;
import modeli.Oglas;
import modeli.User;
import modeli.Verifikacija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        List<AktivnostOglasa> listaAkt = aktivnostOglasaRepository.findAllByVremeIsticanjaBefore(new Date());
        System.out.println(listaAkt);
        listaAkt.forEach(aktivnostOglasa -> {
            Optional<Oglas> oglas = oglasiRepository.findByAktivnostOglasa(aktivnostOglasa);
            if(oglas.isPresent()) {
                Oglas oglas1 = oglas.get();
                if(oglas1.isAktivan()) {
                    oglas1.setAktivan(false);
                    oglasiRepository.save(oglas1);

                    sendEmail(oglas1, oglas1.getUser());
                }
            }
        });
    }

    @Async
    void sendEmail(Oglas oglas, User user) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Obavestenje o isticanju oglasa");
        msg.setText("Postovani korisnice \nObavestavamo Vas da je vas oglas broj " + oglas.getId() +
                " upravo istekao. \nOglas mozete obnoviti sa sledece stranice: \n " +
                "http://localhost:8100/moji_oglasi \n\n");

        javaMailSender.send(msg);

    }

}
