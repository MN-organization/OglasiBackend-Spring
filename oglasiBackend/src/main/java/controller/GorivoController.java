package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GorivoService;

@RestController
@RequestMapping("/api/goriva")
@AllArgsConstructor
public class GorivoController {
    private final GorivoService gorivoService;

    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        ResponseDto response = ResponseDto.builder().goriva(gorivoService.vratiGoriva()).poruka("Uspesno vracena goriva").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
