package kodlama.io.rentACar.business.abstracts;

import java.util.List;

import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.DeleteModelByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetModelByIdResponse;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelByIdResponse getModelByIdResponse(GetModelByIdResponse response);
    void add(CreateModelRequest createModelRequest);
    void update(UpdateModelRequest request);
    void delete(DeleteModelByIdRequest request);
}
