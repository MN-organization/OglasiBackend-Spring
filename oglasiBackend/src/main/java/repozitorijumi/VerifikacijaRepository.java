package repozitorijumi;

import modeli.Verifikacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifikacijaRepository extends JpaRepository<Verifikacija, Long> {
    Optional<Verifikacija> findByToken(String token);
}
