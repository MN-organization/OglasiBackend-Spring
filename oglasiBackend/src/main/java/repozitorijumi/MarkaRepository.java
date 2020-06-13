package repozitorijumi;

import modeli.Marka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkaRepository extends JpaRepository<Marka, Long> {
}
