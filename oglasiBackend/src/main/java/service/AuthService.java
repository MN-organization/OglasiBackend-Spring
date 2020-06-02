package service;

import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import modeli.User;
import modeli.Verifikacija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repozitorijumi.UserRepository;
import repozitorijumi.VerifikacijaRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerifikacijaRepository verifikacijaRepository;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        System.out.println(registerRequest.getEmail());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setVerifikovan(false);
        userRepository.save(user);

        String token = generateVerificationToken(user);
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, String.class);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        Verifikacija verifikacija = new Verifikacija();
        verifikacija.setToken(token);
        verifikacija.setUser(user);

        verifikacijaRepository.save(verifikacija);
        return token;
    }

}
