package kodlama.io.rentACar.userInterfaces;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.DeleteBrandByIdRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.webApi.Controllers.BrandsController;

@Route("/brand")
public class brandPage extends Tools {
    @Autowired
    private BrandsController brandController;

    public brandPage(){
        baseLine = BaseLineCreator();

        add(baseLine);
    }

    private Component BaseLineCreator(){
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

        VerticalLayout l4 = DefVer(updateBrandId, updateBrandName, updateBrandByIdButton);

        Component lMain = DefHor(l1, l2, l3, l4);

        Button btnGoToMain =  BtnDirector("Go Back", mainPage.class);

        Component layout = DefVer(lMain, btnGoToMain);

        return layout;
    }
}
