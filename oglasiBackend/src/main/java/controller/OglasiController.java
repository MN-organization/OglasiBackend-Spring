package controller;

import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import modeli.Oglas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OglasiService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/oglasi")
@AllArgsConstructor
public class OglasiController {

    private final OglasiService oglasiService;

    @GetMapping("/{id}")
    public ResponseEntity<Oglas> vratiOglas(@PathVariable Long id) {
        return new ResponseEntity<>(oglasiService.vratiOglas(id), HttpStatus.OK);
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
    public ResponseEntity<List<Oglas>> vratiSve() {
        return new ResponseEntity<>(oglasiService.vratiSve(), HttpStatus.OK);
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
