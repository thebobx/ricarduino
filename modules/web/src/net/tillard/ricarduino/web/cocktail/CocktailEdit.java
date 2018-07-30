package net.tillard.ricarduino.web.cocktail;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.WindowContext;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.DsContext;
import net.tillard.ricarduino.entity.Cocktail;
import com.haulmont.cuba.gui.data.Datasource;
import net.tillard.ricarduino.entity.CocktailLine;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CocktailEdit extends AbstractEditor<Cocktail> {

    @Inject
    private FileUploadField upload;

    @Named("fieldGroup.description")
    private TextField descriptionField;
    @Named("fieldGroup.howTo")
    private TextField howToField;
    @Named("fieldGroup.name")
    private TextField nameField;

    @Inject
    private DataManager dataManager;
    @Inject
    private DsContext dsContext;

    @Inject
    private Datasource<Cocktail> cocktailDs;

    private String source = "";

    private Cocktail saveCocktail = null;

    @Inject
    private Persistence persistence;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params); // Utile ???
        if (((String)params.get("sourceBtn")) != null && "details".equals((String)params.get("sourceBtn"))) {
            //showNotification("From details", " Class = " + WindowParams.ITEM.getEntity(params).getClass().toString(), NotificationType.HUMANIZED);
            source = "details";
            Cocktail origCocktail = (Cocktail) WindowParams.ITEM.getEntity(params);
            saveCocktail = Cocktail.getCopyFrom(origCocktail);

            upload.setVisible(false);
            nameField.setEditable(false);
            descriptionField.setEditable(false);
            howToField.setEditable(false);

        } else {
            showNotification("From edit", "", NotificationType.HUMANIZED);
        }
    }

    @Override
    protected boolean preCommit() {
        //return super.preCommit();
        if ("details".equals(source)) {
            //showNotification("Commit from details6", "name ingredient3 = ", NotificationType.HUMANIZED);
            Cocktail afterCocktail = cocktailDs.getItem();
            //TODO: Call prepareDrink(afterCocktail) here !!
            afterCocktail.setCocktailLines(saveCocktail.getCocktailLines());
            cocktailDs.setItem(afterCocktail);
            cocktailDs.commit();
            this.close(WINDOW_CLOSE,true);
            return false;
        } else {
            //showNotification("Commit from editing", "name ingredient = " + cocktailDs.getItem().getCocktailLines().get(0).getIngredient().getName(), NotificationType.HUMANIZED);
            return true;
        }
    }

    public Component generatePictureField(Datasource datasource, String fieldId) {
		return null;
    }
}