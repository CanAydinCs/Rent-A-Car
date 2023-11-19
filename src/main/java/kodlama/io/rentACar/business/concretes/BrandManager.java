package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.DeleteBrandByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetBrandByIdResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService{
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        
        List<GetAllBrandsResponse> brandsResponses = brands.stream().map(
            brand -> modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)
            ).collect(Collectors.toList());
        
        //iş kuralları
        return brandsResponses;
    }

    @Override
    public void add(CreateBrandRequest request) {
        brandBusinessRules.checkIfBrandNameExists(request.getName());
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest request) {
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        brandRepository.save(brand);
    }

    @Override
    public GetBrandByIdResponse getBrandById(GetBrandByIdResponse response) {
        Brand brand = brandRepository.findById(response.getId()).orElseThrow();
        return modelMapperService.forResponse().map(brand, GetBrandByIdResponse.class);
    }

    @Override
    public void delete(DeleteBrandByIdRequest request) {
        brandRepository.deleteById(request.getId());
    }
}
