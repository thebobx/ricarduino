package net.tillard.ricarduino.web.ingredient;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import net.tillard.ricarduino.entity.Ingredient;

import javax.inject.Inject;
import java.util.Map;

import static com.haulmont.cuba.gui.components.Image.*;

public class IngredientBrowse extends AbstractLookup {

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private GroupTable<Ingredient> ingredientsTable;

    @Override
    public void init(Map<String, Object> params) {

        ingredientsTable.addGeneratedColumn("picture", ingredient -> {
            if (ingredient.getPicture() != null) {
                Image image = componentsFactory.createComponent(Image.class);
                image.setScaleMode(ScaleMode.CONTAIN);
                image.setHeight("100");
                image.setWidth("100");

                FileDescriptor userImageFile = ingredient.getPicture();
                image.setSource(FileDescriptorResource.class).setFileDescriptor(userImageFile);

                //Label userLogin = componentsFactory.createComponent(Label.class);
                //userLogin.setValue(entity.getName());
                //userLogin.setAlignment(Alignment.MIDDLE_LEFT);

                HBoxLayout hBox = componentsFactory.createComponent(HBoxLayout.class);
                hBox.setSpacing(true);
                hBox.add(image);
                //hBox.add(userLogin);

                return hBox;
            } else {
                return null;
            }
        });
    }
}