package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.core.utilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAcess.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CarBusinessRules {

    private CarRepository carRepository;

    public void checkIfCarPlate(String plate) {

        if (this.carRepository.existsByPlate(plate)) {
            throw new BusinessException("Car plate already exists!");
        }
    }
}