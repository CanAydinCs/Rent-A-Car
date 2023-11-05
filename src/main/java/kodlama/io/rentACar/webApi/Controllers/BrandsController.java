package kodlama.io.rentACar.webApi.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.entities.concretes.Brand;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private BrandService brandService;

    public BrandsController(BrandService _brandService){
        brandService = _brandService;
    }

    @GetMapping("/getall")
    public List<Brand> getAll(){
        return brandService.getAll();
    }
}
