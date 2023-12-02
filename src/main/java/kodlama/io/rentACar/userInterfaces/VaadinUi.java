package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.DeleteBrandByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.webApi.Controllers.BrandsController;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Route("")
public class VaadinUi extends VerticalLayout {
    private HorizontalLayout baseLine;

    @Autowired
    private BrandsController brandC;

    private Component layoutToRemove;

    public VaadinUi() {
        baseLine = BaseLineCreator();
        
        add(baseLine);
    }

    private HorizontalLayout BaseLineCreator(){
        Button btnTestShower = new Button("Show Test Ui", event -> {
            AddTempLayout(TestUi());
        }); 

        Button brandsButton = new Button("Brands Section", event -> {
            AddTempLayout(BrandsUi());
        });
        
        Button btnHideAll = new Button("Hide All", event -> {
            HideAll();
        });

        HorizontalLayout layout = DefHor(btnTestShower, brandsButton, btnHideAll);

        return layout;
    }

    private Component BrandsUi(){
        TextArea allBrandOutput = new TextArea();
        allBrandOutput.setReadOnly(true);
        Button getAllButton = new Button("Get All Brands", event -> {
            allBrandOutput.setValue("");
            StringBuilder brandOutputBuilder = new StringBuilder(allBrandOutput.getValue());
            for (GetAllBrandsResponse response : brandC.getAll()) {
                brandOutputBuilder.append("\n").append(response.getString());
            }
            allBrandOutput.setValue(brandOutputBuilder.toString());
        });

        VerticalLayout l1 = DefVer(allBrandOutput, getAllButton);

        TextField createBrandInput = new TextField();
        Button createBrandButton = new Button("Create Brand", event -> {
            brandC.add(new CreateBrandRequest(createBrandInput.getValue()));
            Notification.show("Brand created");
        });

        VerticalLayout l2 = DefVer(createBrandButton, createBrandButton);

        TextField deleteBrandId = new TextField();
        Button deleteBrandByIdButton = new Button("Delete", event -> {
            brandC.delete(new DeleteBrandByIdRequest(Integer.parseInt(deleteBrandId.getValue())));
            Notification.show("Brand Deleted");
        });

        VerticalLayout l3 = DefVer(deleteBrandId, deleteBrandByIdButton);

        TextField updateBrandId = new TextField("Enter ID");
        TextField updateBrandName = new TextField("Enter New Name");
        Button updateBrandByIdButton = new Button("Update", event -> {
            UpdateBrandRequest request = new UpdateBrandRequest(Integer.parseInt(updateBrandId.getValue()), updateBrandName.getValue());

            brandC.update(request);

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

    private void AddTempLayout(Component layout){
        HideAll();

        layoutToRemove = layout;
        add(layoutToRemove);
    }

    private void HideAll(){
        if(layoutToRemove != null)
        {
            remove(layoutToRemove);
        }
    }

    private VerticalLayout DefVer(Component... comps){
        VerticalLayout layout = new VerticalLayout(comps);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return layout;
    }

    private HorizontalLayout DefHor(Component... comps){
        HorizontalLayout layout = new HorizontalLayout(comps);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        return layout;
    }
}