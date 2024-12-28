package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetByIdModelResponse;
import kodlama.io.rentACar.business.rules.ModelBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAcess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;



    @Override
    public List<GetAllModelsResponse> getAll() {
            List<Model> models = modelRepository.findAll();

            List<GetAllModelsResponse> modelsResponses = models
                    .stream()
                    .map(model -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
                    .collect(Collectors.toList());
            return modelsResponses;
    }

    @Override
    public GetByIdModelResponse getById(int id) {
        Model model= this.modelRepository.findById(id).orElseThrow();

        GetByIdModelResponse response=this.modelMapperService.forResponse().map(model, GetByIdModelResponse.class);

        return response;
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        this.modelBusinessRules.checkIfNameExists(createModelRequest.getName());

        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        model.setId(0);
        this.modelRepository.save(model);
    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
        this.modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        this.modelRepository.deleteById(id);
    }
}
