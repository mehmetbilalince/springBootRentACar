package kodlama.io.rentACar.dataAcess.abstracts;

import kodlama.io.rentACar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByName(String name);
}
