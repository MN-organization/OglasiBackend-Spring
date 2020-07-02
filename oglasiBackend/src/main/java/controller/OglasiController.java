package controller;

import com.sipios.springsearch.anotation.SearchSpec;
import dto.ResponseDto;
import lombok.AllArgsConstructor;
import modeli.AktivnostOglasa;
import modeli.Oglas;
import modeli.Slika;
import modeli.SlikaBafer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import service.OglasiService;

import java.util.*;

@RestController
@RequestMapping("/api/oglasi")
@AllArgsConstructor
public class OglasiController {

    private final OglasiService oglasiService;

    Map<String, SlikaBafer> slikeMapa;

    @PostMapping("/slike")
        public ResponseEntity<ResponseDto> postaviSlike(@RequestBody String slika) {
            System.out.println(slika);
            String hes = UUID.randomUUID().toString();
            slikeMapa.put(hes, new SlikaBafer(slika, new Date()));
            ResponseDto responseDto = ResponseDto.builder().hes(hes).build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/slike/obrisi/{hes}")
    public ResponseEntity<ResponseDto> obrisiSliku(@PathVariable String hes) {
        slikeMapa.remove(hes);
        System.out.println(slikeMapa);
        ResponseDto responseDto = ResponseDto.builder().poruka("obrisana slika").build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @PostMapping("/slike/edit/{idOglas}")
//    public ResponseEntity<ResponseDto> dodajSlikuEdit(@PathVariable Long idOglas, @RequestBody String slika) {
//        Oglas o = oglasiService.vratiOglas(idOglas);
//        Slika slika1 = new Slika();
//        slika1.setSlika(slika);
//        o.getSlike().add(slika1);
//        oglasiService.save(o);
//        System.out.println(slika1);
//        ResponseDto responseDto = ResponseDto.builder().slika(slika1).poruka("uspesno dodata slika").build();
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }

    @DeleteMapping("/slike/obrisi/{idOglas}/{idSlike}")
    public ResponseEntity<ResponseDto> obrisiSlikuEdit(@PathVariable Long idOglas, @PathVariable Long idSlike) {
        Oglas o = oglasiService.vratiOglas(idOglas);
        Slika slik = new Slika();
        for (Slika slika : o.getSlike()){
            if(slika.getId() == idSlike) {
                slik = slika;
            }
        };
        o.getSlike().remove(slik);
        oglasiService.save(o);
        ResponseDto responseDto = ResponseDto.builder().poruka("obrisana slika edit").build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> vratiOglas(@PathVariable Long id) {
        ResponseDto response = ResponseDto.builder().oglas(oglasiService.vratiOglas(id)).poruka("Uspesno vracen oglas").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> izbrisiOglas(@PathVariable Long id) {
        ResponseDto response = ResponseDto.builder().poruka(oglasiService.izbrisiOglas(id)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto> izmeniOglas(@PathVariable Long id, @RequestBody Oglas oglas) {
        ResponseDto response = ResponseDto.builder().oglas(oglasiService.izmeniOglas(oglas)).poruka("Uspesno izmenjen oglas").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        ResponseDto response = ResponseDto.builder().oglasi(oglasiService.vratiSve()).poruka("Uspesno vraceno sve").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/dodaj/{orderID}")
    public ResponseEntity<ResponseDto> postaviOglas(@RequestBody Oglas oglas,@PathVariable String orderID) {
//        Oglas oglas = OglasDto.dtoToOglas(oglasDto);
        List<Slika> listaSlika = new ArrayList<>();
        for (Slika slika : oglas.getSlike()) {
            Slika slicica = new Slika();
            if(slikeMapa.get(slika.getSlika()) != null) {
                slicica.setSlika(slikeMapa.get(slika.getSlika()).getSlika());
            }
            listaSlika.add(slicica);
        }
        oglas.setSlike(listaSlika);
        System.out.println("Oglas koji nam treba:");
        System.out.println(oglas);
        ResponseDto response = ResponseDto.builder().oglas(oglasiService.saveSaOrderID(oglas,orderID)).poruka("Uspesno Postavljen oglas").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/moji")
    public ResponseEntity<ResponseDto> vratiMojeOglase() {
        ResponseDto response = ResponseDto.builder().oglasi(oglasiService.vratiMojeOglase()).poruka("Uspesno vraceni moji oglasi").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/sacuvani")
    public ResponseEntity<ResponseDto> vratiSacuvaneOglase() {
        ResponseDto response = ResponseDto.builder().oglasi(oglasiService.vratiSacuvaneOglase()).poruka("Uspesno vraceni sacuvani oglasi").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/sacuvaj/{id}")
    public ResponseEntity<ResponseDto> sacuvajOglas(@PathVariable String id) {
        ResponseDto response = ResponseDto.builder().poruka(oglasiService.sacuvaj(Long.parseLong(id))).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/izbrisiSacuvan/{id}")
    public ResponseEntity<ResponseDto> izbrisiSacuvanOglas(@PathVariable String id) {
        ResponseDto response = ResponseDto.builder().poruka(oglasiService.izbrisiSacuvan(Long.parseLong(id))).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pretraga")
    public ResponseEntity<ResponseDto> pretraga(@SearchSpec Specification<Oglas> kriterijumi) {
        ResponseDto response = ResponseDto.builder().oglasi(oglasiService.pretraga(kriterijumi)).poruka("Vraceni rezultati pretrage").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/obnova/{orderID}")
    public ResponseEntity<ResponseDto> obnovaOglasa(@PathVariable String orderID, @RequestBody Oglas oglas) {
        ResponseDto response = ResponseDto.builder().poruka(oglasiService.obnoviOglas(oglas, orderID)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        slikeMapa.forEach((s, slikaBafer) -> {
            if(slikaBafer.getVremePostavljanja().getTime()+1800000 < new Date().getTime()){
                slikeMapa.remove(s);
                System.out.println("//////////////////////////////////////////////////////izbrisana slika istekla");
            }
        });
    }
}
