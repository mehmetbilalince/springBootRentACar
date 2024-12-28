package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAcess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandsResponse> brandResponse = brands
                .stream()
                .map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
                .collect(Collectors.toList());
        return brandResponse;

        /** ESKI YÖNTEM

         List<GetAllBrandsResponse> brandResponse = new ArrayList<GetAllBrandsResponse>();
         List<Brand> brands = brandRepository.findAll();
         for (Brand brand : brands) {
         GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
         responseItem.setId(brand.getId());
         responseItem.setName(brand.getName());

         brandResponse.add(responseItem);
         }

         */
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        Brand brand= this.brandRepository.findById(id).orElseThrow();

        GetByIdBrandResponse response=this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);

        return response;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class); //Gelen kaynagi (source=request) --> Brand'e cevir

        this.brandRepository.save(brand);

        //Model Mapper kullanmadan önce tüm nesneler bu sekilde manuel set ediliyordu. Kac tane field varsa.
//        Brand brand = new Brand();
//        brand.setName(createBrandRequest.getName());

    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandRepository.save(brand); //Buradaki save'in add den farki id de oldugu icin insert degil update islemi yapar UpdateBrandRequest sayesinde
    }

    @Override
    public void delete(int id) {
        this.brandRepository.deleteById(id);
    }
}
