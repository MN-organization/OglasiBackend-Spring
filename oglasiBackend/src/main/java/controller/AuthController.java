package controller;

import dto.*;
import lombok.AllArgsConstructor;
import modeli.Verifikacija;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repozitorijumi.VerifikacijaRepository;
import service.AuthService;
import service.RefreshTokenService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final VerifikacijaRepository verifikacijaRepository;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody RegisterRequest registerRequest) throws IOException {
        System.out.println(registerRequest + " - usao");
        String porukaOregistraciji = authService.signup(registerRequest);
        ResponseDto response = ResponseDto.builder().poruka(porukaOregistraciji).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verifikacija/{token}")
    public ResponseEntity<String> verifyUser(@PathVariable String token) {
        Optional<Verifikacija> ver = verifikacijaRepository.findByToken(token);
        if(ver.isPresent()) {
            Verifikacija verifikacija = ver.get();
            verifikacija.getUser().setVerifikovan(true);
            verifikacijaRepository.save(verifikacija);
            return new ResponseEntity<>("<head><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\"></head><body><div class=\"container-fluid\">\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col-md-12\">\n" +
                    "            <div class=\"jumbotron\">\n" +
                    "                <h2 class=\"text-center mb-4\">\n" +
                    "                    Verifikacija Uspesna!\n" +
                    "                </h2>\n" +
                    "                <p>\n" +
                    "                    <a class=\"btn btn-primary btn-lg btn-block\" href=\"http://localhost:8100/moji_oglasi\">PRIJAVITE SE</a>\n" +
                    "                </p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div></body>", HttpStatus.OK);
        }
        return new ResponseEntity<>("Neuspesna verifikacija - token nije dobar", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) throws IOException {
        System.out.println(loginRequest + " - usao");
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/refresh/token/{refreshToken}")
    public AuthenticationResponse refreshTokens(@PathVariable String refreshToken) {
        System.out.println("get za refresh token");
        return authService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token uspesno izbrisan!");
    }

}
