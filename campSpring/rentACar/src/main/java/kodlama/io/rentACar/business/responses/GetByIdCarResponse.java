package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCarResponse {

    private int id;
    private int modelYear;
    private String modelName;
    private Double dailyPrice;
    private String plate;
}
