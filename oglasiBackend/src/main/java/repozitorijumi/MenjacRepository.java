package repozitorijumi;

import modeli.Menjac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenjacRepository extends JpaRepository<Menjac, Long> {
}
