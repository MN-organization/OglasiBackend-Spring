package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MarkaModelService;

@RestController
@RequestMapping("/api/markaModel")
@AllArgsConstructor
public class MarkaModelController {

    private final MarkaModelService markaModelService;

    @GetMapping
    public ResponseEntity<ResponseDto> vratiMarke() {
        ResponseDto response = ResponseDto.builder().marke(markaModelService.vratiMarke()).poruka("vracene marke").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idMarke}")
    public ResponseEntity<ResponseDto> vratiModeleZaMarku(@PathVariable Long idMarke) {
        ResponseDto response = ResponseDto.builder().modeli(markaModelService.vratiModeleZaMarku(idMarke)).poruka("vraceni modeli za marku").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
