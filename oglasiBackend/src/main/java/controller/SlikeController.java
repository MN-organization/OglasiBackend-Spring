//package controller;
//
//import dto.ResponseDto;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/slike")
//@AllArgsConstructor
//public class SlikeController {
////
////    Map<String, String> slikeMapa;
////
////    @PostMapping
////    public ResponseEntity<ResponseDto> postaviSlike(@RequestBody String slika) {
////        System.out.println(slika);
////        String hes = UUID.randomUUID().toString();
////        slikeMapa.put(hes, slika);
////        ResponseDto responseDto = ResponseDto.builder().hes(hes).build();
////        return new ResponseEntity<>(responseDto, HttpStatus.OK);
////    }
////
////    @DeleteMapping("/obrisi/{hes}")
////    public ResponseEntity<ResponseDto> obrisiSliku(@PathVariable String hes) {
////        slikeMapa.remove(hes);
////        System.out.println(slikeMapa);
////        ResponseDto responseDto = ResponseDto.builder().poruka("obrisana slika").build();
////        return new ResponseEntity<>(responseDto, HttpStatus.OK);
////    }
////
//
//}
