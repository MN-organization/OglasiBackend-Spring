package controller;

import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AuthService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest + " - usao");
        authService.signup(registerRequest);
        return new ResponseEntity<>("Uspesna registracija", HttpStatus.OK);
    }

    @GetMapping("/proba")
    public ResponseEntity<String> vrati() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
