package net.tillard.ricarduino.web.cocktail;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.FieldGroup;
import net.tillard.ricarduino.entity.Cocktail;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;

public class CocktailDetails extends AbstractEditor<Cocktail> {

    @Inject
    private FieldGroup fieldGroup;

    public Component generatePictureField(Datasource datasource, String fieldId) {
		return null;
    }

    public void onTestBtnClick() {
        showNotification("FieldGroup " + fieldGroup.getField("name"), "test", NotificationType.HUMANIZED);
    }
}