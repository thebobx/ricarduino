package net.tillard.ricarduino.web.cocktail;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import net.tillard.ricarduino.entity.Cocktail;
import net.tillard.ricarduino.entity.CocktailLine;
import net.tillard.ricarduino.entity.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

import static com.haulmont.cuba.gui.components.Image.*;

public class CocktailBrowse extends AbstractLookup {

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private GroupTable<Cocktail> cocktailsTable;

    @Inject
    private PopupView popupViewServe;

    @Inject
    private Button detailsBtn;

    @Inject
    private GroupDatasource<Cocktail, UUID> cocktailsDs;

    private Logger log = LoggerFactory.getLogger(CocktailBrowse.class);

    @Override
    public void init(Map<String, Object> params) {

        cocktailsDs.addItemChangeListener(event -> {
            //log.info("Datasource {} item has been changed from {} to {}",
            //        event.getDs(), event.getPrevItem(), event.getItem());
            if (cocktailsTable.getSingleSelected()!=null) {
                detailsBtn.setEnabled(true);
            }
        });

        cocktailsTable.addGeneratedColumn("picture", cocktail -> {
            if (cocktail.getPicture() != null) {
                Image image = componentsFactory.createComponent(Image.class);
                image.setScaleMode(ScaleMode.CONTAIN);
                image.setHeight("100");
                image.setWidth("100");

                FileDescriptor userImageFile = cocktail.getPicture();
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

        cocktailsTable.addGeneratedColumn("Ingredients", cocktail -> {
            List<CocktailLine> cocktailLineSet = cocktail.getCocktailLines();
            Label content = componentsFactory.createComponent(Label.class);
            content.setHtmlEnabled(true);
            StringBuilder sb = new StringBuilder();
            sb.append("<ul style=\"margin-right: 1em;\">");
            for (CocktailLine cocktailLine : cocktailLineSet) {
                sb.append("<li>").append(cocktailLine.getParts().toString().replaceAll(".0","")).append(" parts de ").append(cocktailLine.getIngredient().getName()).append("</li>");
            }
            sb.append("</ul>");
            content.setValue(sb.toString());
            return content;
        });
    }

    public void onDetailsBtnClick() {
        //showNotification("Préparation d'un " + cocktailsTable.getSingleSelected().getName(), cocktailsTable.getSingleSelected().getDescription(), NotificationType.HUMANIZED);
        //popupViewServe.setPopupVisible(true);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sourceBtn", "details");
        AbstractEditor editor = openEditor("ricarduino$Cocktail.edit", cocktailsTable.getSingleSelected(), WindowManager.OpenType.NEW_TAB, parameters);

    }

    public void onDrinkBntClick() {
        popupViewServe.setPopupVisible(false);
        //showNotification("Préparation d'un " + cocktailsTable.getSingleSelected().getName(), cocktailsTable.getSingleSelected().getDescription(), NotificationType.HUMANIZED);
    }
}