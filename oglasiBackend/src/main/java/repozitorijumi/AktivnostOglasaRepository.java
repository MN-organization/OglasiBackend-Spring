package repozitorijumi;

import modeli.AktivnostOglasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AktivnostOglasaRepository extends JpaRepository<AktivnostOglasa, Long> {
    List<AktivnostOglasa> findAllByVremeIsticanjaBefore(Date danasnji);
}
