package repozitorijumi;

import modeli.SacuvaniOglas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacuvaniOglasRepository extends JpaRepository<SacuvaniOglas, Long> {
}
