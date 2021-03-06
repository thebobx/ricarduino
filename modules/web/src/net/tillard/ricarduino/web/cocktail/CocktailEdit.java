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
    private Button windowCommit;
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
        super.init(params);
    }

    public Component generatePictureField(Datasource datasource, String fieldId) {
		return null;
    }
}