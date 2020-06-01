package repozitorijumi;

import modeli.Oglas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OglasiRepository extends JpaRepository<Oglas, Long> {
}
