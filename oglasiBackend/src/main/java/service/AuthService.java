package service;

import dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import modeli.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repozitorijumi.UserRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
    }
}
