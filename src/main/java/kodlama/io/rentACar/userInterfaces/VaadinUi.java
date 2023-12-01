package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Route("")
public class VaadinUi extends VerticalLayout {

    private TextField inputField = new TextField("Enter text");
    private TextField outputField = new TextField();

    public VaadinUi() {
        Button button = new Button("Submit", event -> {
            String input = inputField.getValue();
            outputField.setValue("You entered: " + input);
        });

        outputField.setReadOnly(true);
        VerticalLayout layout = new VerticalLayout(inputField, button, outputField);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(layout);
    }
}