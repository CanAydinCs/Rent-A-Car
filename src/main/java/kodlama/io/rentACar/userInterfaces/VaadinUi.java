package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.DeleteBrandByIdRequest;
import kodlama.io.rentACar.business.requests.DeleteModelByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.webApi.Controllers.BrandsController;
import kodlama.io.rentACar.webApi.Controllers.ModelsController;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Route("/old")
public class VaadinUi extends Tools {
    private HorizontalLayout baseLine;

    @Autowired
    private BrandsController brandController;
    @Autowired
    private ModelsController modelController;

    public VaadinUi() {
        baseLine = BaseLineCreator();
        
        add(baseLine);
    }

    private HorizontalLayout BaseLineCreator(){
        Button btnGoToTest = BtnDirector("Go To Test Page", testUi.class);
        Button btnGoToMain = BtnDirector("Go To Main Page", mainPage.class);

        Button btnTestShower = new Button("Show Test Ui", event -> {
            AddTempLayout(TestUi());
        }); 

        Button brandsButton = new Button("Brands Section", event -> {
            AddTempLayout(BrandsUi());
        });

        Button modelsButton = new Button("Models Section", event -> {
            AddTempLayout(ModelsUi());
        });
        
        Button btnHideAll = new Button("Hide All", event -> {
            HideAll();
        });

        HorizontalLayout layout = DefHor(btnGoToTest, btnGoToMain, btnTestShower, brandsButton, modelsButton, btnHideAll);

        return layout;
    }

    private Component ModelsUi(){
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
        TextField createModelBrand = new TextField("Enter Brand ID");
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
        TextField updateModelBrand = new TextField("Enter New Brand ID");
        Button updateModelByIdButton = new Button("Update", event -> {
            UpdateModelRequest request = new UpdateModelRequest(
                Integer.parseInt(updateModelId.getValue()), 
                updateModelName.getValue(),
                Integer.parseInt(updateModelBrand.getValue()));

            modelController.update(request);

            Notification.show("Model succesfuly updated");
        });

        VerticalLayout l4 = new VerticalLayout(updateModelId, updateModelName, updateModelBrand, updateModelByIdButton);



        HorizontalLayout layout = DefHor(l1, l2, l3, l4);

        return layout;
    }

    private Component BrandsUi(){
        TextArea allBrandOutput = new TextArea();
        allBrandOutput.setReadOnly(true);
        Button getAllButton = new Button("Get All Brands", event -> {
            allBrandOutput.setValue("");
            StringBuilder brandOutputBuilder = new StringBuilder(allBrandOutput.getValue());
            for (GetAllBrandsResponse response : brandController.getAll()) {
                brandOutputBuilder.append("\n").append(response.getString());
            }
            allBrandOutput.setValue(brandOutputBuilder.toString());
        });

        VerticalLayout l1 = DefVer(allBrandOutput, getAllButton);

        TextField createBrandInput = new TextField("Enter Brand Name");
        Button createBrandButton = new Button("Create Brand", event -> {
            brandController.add(new CreateBrandRequest(createBrandInput.getValue()));
            Notification.show("Brand created");
        });

        VerticalLayout l2 = DefVer(createBrandInput, createBrandButton);

        TextField deleteBrandId = new TextField("Enter Brand ID");
        Button deleteBrandByIdButton = new Button("Delete", event -> {
            brandController.delete(new DeleteBrandByIdRequest(Integer.parseInt(deleteBrandId.getValue())));
            Notification.show("Brand Deleted");
        });

        VerticalLayout l3 = DefVer(deleteBrandId, deleteBrandByIdButton);

        TextField updateBrandId = new TextField("Enter ID");
        TextField updateBrandName = new TextField("Enter New Name");
        Button updateBrandByIdButton = new Button("Update", event -> {
            UpdateBrandRequest request = new UpdateBrandRequest(Integer.parseInt(updateBrandId.getValue()), updateBrandName.getValue());

            brandController.update(request);

            Notification.show("Brand succesfuly updated");
        });

        VerticalLayout l4 = new VerticalLayout(updateBrandId, updateBrandName, updateBrandByIdButton);

        

        HorizontalLayout layout = DefHor(l1, l2, l3, l4);

        return layout;
    }

    private Component TestUi(){
        TextField inputField = new TextField("Enter Text here:");
        TextField outputField = new TextField();

        Button submit = new Button("Submit", event -> {
            String input =  "You entered : " + inputField.getValue();
            outputField.setValue(input);
        });

        outputField.setReadOnly(true);

        VerticalLayout layout = DefVer(inputField, outputField, submit);
        
        return layout;
    }
}