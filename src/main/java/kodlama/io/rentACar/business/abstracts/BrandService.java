package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.DeleteBrandByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetBrandByIdResponse;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandByIdResponse getBrandById(GetBrandByIdResponse response);
    void add(CreateBrandRequest request);
    void update(UpdateBrandRequest request);
    void delete(DeleteBrandByIdRequest request);
}
