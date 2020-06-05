package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import modeli.Oglas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OglasiService;

import java.util.List;

@RestController
@RequestMapping("/api/oglasi")
@AllArgsConstructor
public class OglasiController {

    private final OglasiService oglasiService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> vratiOglas(@PathVariable Long id) {
        ResponseDto response = ResponseDto.builder().oglas(oglasiService.vratiOglas(id)).poruka("Uspesno vracen oglas").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> izbrisiOglas(@PathVariable Long id) {
        return new ResponseEntity<>(oglasiService.izbrisiOglas(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Oglas> izmeniOglas(@PathVariable Long id) {
        return new ResponseEntity<>(oglasiService.izmeniOglas(id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        ResponseDto response = ResponseDto.builder().oglasi(oglasiService.vratiSve()).poruka("Uspesno vraceno sve").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<Oglas> postaviOglas(@RequestBody Oglas oglas) {
        return new ResponseEntity<>(oglasiService.save(oglas), HttpStatus.OK);
    }

    @GetMapping("/moji")
    public ResponseEntity<List<Oglas>> vratiMojeOglase() {
        return new ResponseEntity<>(oglasiService.vratiMojeOglase(), HttpStatus.OK);
    }

    @GetMapping("/sacuvani")
    public ResponseEntity<List<Oglas>> vratiSacuvaneOglase() {
        return new ResponseEntity<>(oglasiService.vratiSacuvaneOglase(), HttpStatus.OK);
    }


    @GetMapping("/sacuvaj/{id}")
    public ResponseEntity<String> sacuvajOglas(@PathVariable Long id) {
        return new ResponseEntity<>(oglasiService.sacuvaj(id), HttpStatus.OK);
    }

    @GetMapping("/izbrisiSacuvan/{id}")
    public ResponseEntity<String> izbrisiSacuvanOglas(@PathVariable Long id) {
        return new ResponseEntity<>(oglasiService.izbrisiSacuvan(id), HttpStatus.OK);
    }


}
