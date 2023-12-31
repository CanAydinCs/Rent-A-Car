package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.DeleteModelByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetModelByIdResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();

        List<GetAllModelsResponse> modelsResponses = models.stream().map(
            model -> modelMapperService.forResponse().map(model, GetAllModelsResponse.class)
        ).collect(Collectors.toList());

        return modelsResponses;
    }

    @Override
    public void add(CreateModelRequest request) {
        Model model = modelMapperService.forRequest().map(request, Model.class);

        modelRepository.save(model);
    }

    @Override
    public GetModelByIdResponse getModelByIdResponse(GetModelByIdResponse response) {
        Model model = modelRepository.findById(response.getId()).orElseThrow();
        return modelMapperService.forResponse().map(model, GetModelByIdResponse.class);
    }

    @Override
    public void update(UpdateModelRequest request) {
       Model model = modelMapperService.forRequest().map(request, Model.class);
       modelRepository.save(model);
    }

    @Override
    public void delete(DeleteModelByIdRequest request) {
        modelRepository.deleteById(request.getId());
    }
}
