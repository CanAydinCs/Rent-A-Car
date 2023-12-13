package kodlama.io.rentACar.userInterfaces;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.DeleteModelByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.webApi.Controllers.BrandsController;
import kodlama.io.rentACar.webApi.Controllers.ModelsController;

@Route("/model")
public class modelPage extends Tools {
    @Autowired
    private BrandsController brandController;
    @Autowired
    private ModelsController modelController;

    private Component baseLine;

    @PostConstruct
    public void init(){
        baseLine = BaseLineCreator();
        add(baseLine);
    }

    private Component BaseLineCreator(){
        TextArea allModelsOutput = new TextArea();
        allModelsOutput.setReadOnly(true);
        Button getAllButton = new Button("Get All Models", event -> {
            allModelsOutput.setValue("");
            StringBuilder modelOutputBuilder = new StringBuilder(allModelsOutput.getValue());
            for (GetAllModelsResponse response : modelController.getAll()) {
                modelOutputBuilder.append("\n").append(response.toString());
            }
            allModelsOutput.setValue(modelOutputBuilder.toString());
        });

        VerticalLayout l1 = DefVer(allModelsOutput, getAllButton);

        TextField createModelInput = new TextField("Enter Model Name");
        ComboBox<String> createModelBrand = new ComboBox<>("Select Brand");
        createModelBrand.setItems(brandController.getAll().stream().map(GetAllBrandsResponse::getName).collect(Collectors.toList()));
        Button createModelButton = new Button("Create Model", event -> {
            CreateModelRequest request = new CreateModelRequest(createModelInput.getValue(),Integer.parseInt(createModelBrand.getValue()));
            modelController.add(request);
            Notification.show("Model created");
        });

        VerticalLayout l2 = DefVer(createModelInput, createModelBrand, createModelButton);

        TextField deleteModelId = new TextField("Enter Model ID");
        Button deleteModelByIdButton = new Button("Delete", event -> {
            modelController.delete(new DeleteModelByIdRequest(Integer.parseInt(deleteModelId.getValue())));
            Notification.show("Model Deleted");
        });

        VerticalLayout l3 = DefVer(deleteModelId, deleteModelByIdButton);

        TextField updateModelId = new TextField("Enter ID");
        TextField updateModelName = new TextField("Enter New Name");
        ComboBox<String> updateModelBrand = new ComboBox<>("Select New Brand");
        updateModelBrand.setItems(brandController.getAll().stream().map(GetAllBrandsResponse::getName).collect(Collectors.toList()));
        Button updateModelByIdButton = new Button("Update", event -> {
            UpdateModelRequest request = new UpdateModelRequest(
                Integer.parseInt(updateModelId.getValue()), 
                updateModelName.getValue(),
                Integer.parseInt(updateModelBrand.getValue()));

            modelController.update(request);

            Notification.show("Model succesfuly updated");
        });

        VerticalLayout l4 = DefVer(updateModelId, updateModelName, updateModelBrand, updateModelByIdButton);

        HorizontalLayout lMain = DefHor(l1, l2, l3, l4);

        Button btnGoToMain = BtnDirector("Go Back", mainPage.class);

        Component layout = DefVer(lMain, btnGoToMain);
        return layout;
    }
}
