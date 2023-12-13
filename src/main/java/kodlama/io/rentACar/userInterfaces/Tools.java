package kodlama.io.rentACar.userInterfaces;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Tools extends VerticalLayout {
    protected Component baseLine;

    protected Component layoutToRemove;

    public  <T extends Component> Button BtnDirector(String text, Class<T> classToDirect){
        Button button = new Button(text, event -> {
            UI.getCurrent().navigate(classToDirect);
        });

        return button;
    }

    public void AddTempLayout(Component layout){
        HideAll();

        layoutToRemove = layout;
        add(layoutToRemove);
    }

    public void HideAll(){
        if(layoutToRemove != null)
        {
            remove(layoutToRemove);
        }
    }

    public VerticalLayout DefVer(Component... comps){
        VerticalLayout layout = new VerticalLayout(comps);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return layout;
    }

    public HorizontalLayout DefHor(Component... comps){
        HorizontalLayout layout = new HorizontalLayout(comps);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        return layout;
    }
}
