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

public class CocktailCustomize extends AbstractEditor<Cocktail> {

    @Inject
    private FileUploadField upload;

    @Named("fieldGroup.description")
    private TextField descriptionField;
    @Named("fieldGroup.howTo")
    private TextField howToField;
    @Named("fieldGroup.name")
    private TextField nameField;
    @Inject
    private Button windowCommit;
    @Inject
    private DataManager dataManager;
    @Inject
    private DsContext dsContext;

    @Inject
    private Datasource<Cocktail> cocktailDs;

    private Cocktail saveCocktail = null;

    @Inject
    private Persistence persistence;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        Cocktail origCocktail = (Cocktail) WindowParams.ITEM.getEntity(params);
        saveCocktail = Cocktail.getCopyFrom(origCocktail);

        upload.setVisible(false);
        nameField.setEditable(false);
        descriptionField.setEditable(false);
        howToField.setEditable(false);
        windowCommit.setCaption("Prepare Cocktail !!");
    }

    @Override
    protected boolean preCommit() {
        Boolean cocktailAvailable = true;
        String missingIngredient = "";
        for (CocktailLine cocktailLine : cocktailDs.getItem().getCocktailLines()) {
            if (!cocktailLine.getIngredient().getAvailable()) {
                cocktailAvailable = false;
                missingIngredient = cocktailLine.getIngredient().getName();
                break;
            }
        }
        if (cocktailAvailable) {
            Cocktail afterCocktail = cocktailDs.getItem();
            afterCocktail.prepareCocktail();
            this.close(WINDOW_CLOSE, true);
            return false;
        } else {
            showNotification("Au moins un ingrédient manquant !", "Ingrédient : " + missingIngredient, NotificationType.HUMANIZED);
            return false;
        }
    }

}