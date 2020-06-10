package controller;

import com.sipios.springsearch.anotation.SearchSpec;
import dto.ResponseDto;
import lombok.AllArgsConstructor;
import modeli.Oglas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paypal.CreateOrder;
import service.OglasiService;

import java.util.List;

@RestController
@RequestMapping("/api/oglasi")
@AllArgsConstructor
public class OglasiController {

    @Autowired
    private CreateOrder createOrder;

    private final OglasiService oglasiService;

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
    public ResponseEntity<ResponseDto> izmeniOglas(@PathVariable Long id) {
        ResponseDto response = ResponseDto.builder().oglas(oglasiService.izmeniOglas(id)).poruka("Uspesno izmenjen oglas").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        ResponseDto response = ResponseDto.builder().oglasi(oglasiService.vratiSve()).poruka("Uspesno vraceno sve").build();
        createOrder.nesto();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<ResponseDto> postaviOglas(@RequestBody Oglas oglas) {
        ResponseDto response = ResponseDto.builder().oglas(oglasiService.save(oglas)).poruka("Uspesno Postavljen oglas").build();
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

}
