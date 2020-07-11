package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import modeli.Marka;
import modeli.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MarkaModelService;

import java.util.List;

@RestController
@RequestMapping("/api/markaModel")
@AllArgsConstructor
public class MarkaModelController {

    private final MarkaModelService markaModelService;

    @GetMapping
    public ResponseEntity<ResponseDto> vratiMarke() {
        List<Marka> marke = markaModelService.vratiMarke();
        String poruka = "Uspesno vracene marke";
        if(marke.isEmpty()) {
            poruka = "Marke nisu pronadjene";
        }
        ResponseDto response = ResponseDto.builder().marke(marke).poruka(poruka).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idMarke}")
    public ResponseEntity<ResponseDto> vratiModeleZaMarku(@PathVariable Long idMarke) {
        List<Model> modeli = markaModelService.vratiModeleZaMarku(idMarke);
        String poruka = "Uspesno vraceni modeli za marku";
        if(modeli.isEmpty()) {
            poruka = "Modeli nisu pronadjeni";
        }
        ResponseDto response = ResponseDto.builder().modeli(modeli).poruka(poruka).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
