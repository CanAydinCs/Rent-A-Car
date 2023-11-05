package kodlama.io.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{
    private BrandRepository brandRepository;

    public BrandManager(BrandRepository _brandRepository){
        brandRepository = _brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        //iş kuralları
        return brandRepository.findAll();
    }
}