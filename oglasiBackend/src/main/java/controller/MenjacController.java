package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import modeli.Menjac;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MenjacService;

import java.util.List;

@RestController
@RequestMapping("/api/menjaci")
@AllArgsConstructor
public class MenjacController {
    private final MenjacService menjacService;

    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        List<Menjac> menjaci = menjacService.vratiMenjace();
        String poruka = "Uspesno vraceni menjaci";
        if(menjaci.isEmpty()) {
            poruka = "Menjaci nisu pronadjeni";
        }
        ResponseDto response = ResponseDto.builder().menjaci(menjaci).poruka(poruka).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
