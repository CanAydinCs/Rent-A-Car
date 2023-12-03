package kodlama.io.rentACar.business.responses;

import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelByIdResponse {
    private int id;
    private Brand brand;
    private String name;
}
