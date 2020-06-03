package controller;

import dto.AuthenticationResponse;
import dto.LoginRequest;
import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import modeli.Verifikacija;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repozitorijumi.VerifikacijaRepository;
import service.AuthService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final VerifikacijaRepository verifikacijaRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws IOException {
        System.out.println(registerRequest + " - usao");
        authService.signup(registerRequest);
        return new ResponseEntity<>("Uspesna registracija", HttpStatus.OK);
    }

    @GetMapping("/proba")
    public ResponseEntity<String> vrati() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @GetMapping("/verifikacija/{token}")
    public ResponseEntity<String> verifyUser(@PathVariable String token) {
        Optional<Verifikacija> ver = verifikacijaRepository.findByToken(token);
        if(ver.isPresent()) {
            Verifikacija verifikacija = ver.get();
            verifikacija.getUser().setVerifikovan(true);
            verifikacijaRepository.save(verifikacija);
            return new ResponseEntity<>("Uspesna verifikacija", HttpStatus.OK);
        }
        return new ResponseEntity<>("Neuspesna verifikacija - token nije dobar", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws IOException {
        System.out.println(loginRequest + " - usao");
        return authService.login(loginRequest);
    }


}
