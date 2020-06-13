package repozitorijumi;

import modeli.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findAllByMarkaId(Long markaId);
}
