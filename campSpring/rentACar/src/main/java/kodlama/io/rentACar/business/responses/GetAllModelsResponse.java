package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllModelsResponse {
    private int id;
    private String name;
    private String brandName; //Burada mapper çalışma mantığı kullandık. içinde ki  brand'in Name'i. brand'in içinde ki name olarak gelir. Joinleşmiş olarak

}
