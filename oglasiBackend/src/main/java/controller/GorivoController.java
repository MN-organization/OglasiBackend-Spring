package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import modeli.Gorivo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GorivoService;

import java.util.List;

@RestController
@RequestMapping("/api/goriva")
@AllArgsConstructor
public class GorivoController {
    private final GorivoService gorivoService;

    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        List<Gorivo> gorivo = gorivoService.vratiGoriva();
        String poruka = "Uspesno vraceno goriva";
        if(gorivo.isEmpty()) {
            poruka = "Goriva nisu pronadjena";
        }
        ResponseDto response = ResponseDto.builder().goriva(gorivo).poruka(poruka).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
