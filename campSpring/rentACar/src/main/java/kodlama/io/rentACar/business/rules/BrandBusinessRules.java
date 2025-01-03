package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.core.utilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAcess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name) { //Is kurallari statik yapilmaz, DI kullanilir ve class Singleton kullanilir

        if (this.brandRepository.existsByName(name)){
            throw new BusinessException("Brand name already exists!"); //Java Exception Types --> business rules ile ilgili kendi hata kodlarimizi(Runtime) yazmamis gerekli
        }
    }
}
