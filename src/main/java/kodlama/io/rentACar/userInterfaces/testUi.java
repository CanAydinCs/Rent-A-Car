package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/test")
public class testUi extends Tools {
    public testUi(){
        TextField textField = new TextField("2. sayfa");
         Button btnGoBack =  BtnDirector("Go Back", mainPage.class);
        
        HorizontalLayout layout = DefHor(textField, btnGoBack);

        add(layout);
    }
}
