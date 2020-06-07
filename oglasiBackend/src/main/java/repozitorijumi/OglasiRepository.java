package repozitorijumi;

import modeli.Oglas;
import modeli.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface OglasiRepository extends JpaRepository<Oglas, Long> , JpaSpecificationExecutor<Oglas> {
    List<Oglas> findByUser(User user);
}
