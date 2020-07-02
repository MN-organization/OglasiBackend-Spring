package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import modeli.RefreshToken;
import modeli.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repozitorijumi.RefreshTokenRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken refreshRefreshToken(String rt){
        Optional<RefreshToken> token = refreshTokenRepository.findByToken(rt);
        if(token.isPresent()){
            RefreshToken refreshToken = token.get();
            refreshToken.setCreatedDate(Instant.now());
            refreshToken.setToken(UUID.randomUUID().toString());
            return refreshTokenRepository.save(refreshToken);
        }
        throw new RuntimeException("Refresh token nije pronadjen");
    }

    RefreshToken validateRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
