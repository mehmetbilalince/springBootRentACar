package kodlama.io.rentACar.dataAcess.abstracts;

import kodlama.io.rentACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name); //spring jpa keywords. Bu kodda bir tabloya kayıtlı bir ismin var olup olmadığını kontrol eder.
}
