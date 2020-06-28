package repozitorijumi;

import modeli.Oglas;
import modeli.SacuvaniOglas;
import modeli.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SacuvaniOglasRepository extends JpaRepository<SacuvaniOglas, Long> {
    List<SacuvaniOglas> findByUser(User user);
    List<SacuvaniOglas> findByUserAndOglas_Aktivan(User user, boolean aktivan);
}
