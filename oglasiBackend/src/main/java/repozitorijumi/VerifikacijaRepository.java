package repozitorijumi;

import modeli.Verifikacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifikacijaRepository extends JpaRepository<Verifikacija, Long> {
}
