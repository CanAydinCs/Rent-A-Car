package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("")
public class mainPage extends Tools {
    public mainPage(){
        baseLine = BaseLineCreator();

        add(baseLine);
    }

    private Component BaseLineCreator(){
        H1 title = new H1("Rent A Car");

        Button btnGoToTest = BtnDirector("Go To Test Page", testUi.class);
        Button btnGoToVaadin = BtnDirector("Go To Old Ui", VaadinUi.class); 
        Button btnGoToBrands = BtnDirector("Go To Brands", brandPage.class); 
        Button btnGoToModels = BtnDirector("Go To Models", modelPage.class);

        Component layout = DefVer(title, btnGoToTest, btnGoToVaadin, btnGoToBrands, btnGoToModels);

        return layout;
    }
}
