package controller;

import dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MenjacService;

@RestController
@RequestMapping("/api/menjaci")
@AllArgsConstructor
public class MenjacController {
    private final MenjacService menjacService;

    @GetMapping
    public ResponseEntity<ResponseDto> vratiSve() {
        ResponseDto response = ResponseDto.builder().menjaci(menjacService.vratiMenjace()).poruka("Uspesno vraceni menjaci").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
