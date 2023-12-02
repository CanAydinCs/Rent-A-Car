package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Route("")
public class VaadinUi extends VerticalLayout {
    private VerticalLayout baseLine;

    private VerticalLayout layoutToRemove;

    public VaadinUi() {
        baseLine = BaseLineCreator();
        
        add(baseLine);
    }

    private VerticalLayout BaseLineCreator(){
        Button btnTestShower = new Button("Show Test Ui", event -> {
            TestUi();
            
        });

        VerticalLayout layout = new VerticalLayout(btnTestShower);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return layout;
    }

    private void TestUi(){
        if(layoutToRemove != null)
        {
            remove(layoutToRemove);
        }

        TextField inputField = new TextField("Enter Text here:");
        TextField outputField = new TextField();

        Button button = new Button("Submit", event -> {
            String input =  "You entered : " + inputField.getValue();
            outputField.setValue(input);
        });

        outputField.setReadOnly(true);

        VerticalLayout layout = new VerticalLayout(inputField, button, outputField);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(layout);

        layoutToRemove = layout;
    }
}