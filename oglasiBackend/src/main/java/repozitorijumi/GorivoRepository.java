package repozitorijumi;

import modeli.Gorivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GorivoRepository extends JpaRepository<Gorivo, Long> {
}
