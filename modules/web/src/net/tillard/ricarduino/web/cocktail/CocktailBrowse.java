package net.tillard.ricarduino.web.cocktail;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.filter.QueryFilter;
import com.haulmont.cuba.gui.WindowContext;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.Formatter;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.FilterEntity;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.WebWindowManager;
import com.haulmont.cuba.web.theme.HaloTheme;
import net.tillard.ricarduino.entity.Cocktail;
import net.tillard.ricarduino.entity.CocktailLine;
import net.tillard.ricarduino.entity.Ingredient;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

import static com.haulmont.cuba.gui.components.Image.*;

public class CocktailBrowse extends AbstractLookup {

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private UserSession userSession;

    @Inject
    private GroupTable<Cocktail> cocktailsTable;

    @Inject
    private PopupView popupViewServe;

    @Inject
    private Button detailsBtn;

    @Inject
    private GroupDatasource<Cocktail, UUID> cocktailsDs;

    @Inject
    private Filter filter;

    @Inject
    private Metadata metadata;

    @Inject
    private Button createBtn;

    @Inject
    private Button editBtn;

    @Inject
    private Button removeBtn;

    @Inject
    private Button drinkBtn;

    @Inject
    private CheckBox availableCheckBox;

    @Inject
    private OptionsGroup availableOptionsGroup;

    @Inject
    protected HBoxLayout rowsCountBox;

    private Logger log = LoggerFactory.getLogger(CocktailBrowse.class);

    @Override
    public void init(Map<String, Object> params) {
        //super.init(params);
        cocktailsTable.setSettingsEnabled(false);

        RowsCount rowsCount = componentsFactory.createComponent(RowsCount.class);
        rowsCount.setAlignment(Alignment.TOP_RIGHT);
        rowsCount.setDatasource(cocktailsDs);
        rowsCount.setOwner(cocktailsTable);
        rowsCountBox.add(rowsCount);

        List<String> list = new ArrayList<>();
        list.add("All cocktails");
        list.add("Available cocktails");
        availableOptionsGroup.setOptionsList(list);
        availableOptionsGroup.setValue("All cocktails");

        cocktailsDs.addItemChangeListener(event -> {
            //log.info("Datasource {} item has been changed from {} to {}",
            //        event.getDs(), event.getPrevItem(), event.getItem());
            if (cocktailsTable.getSingleSelected()!=null) {
                detailsBtn.setEnabled(true);
                drinkBtn.setEnabled(true);
            }
        });

        cocktailsTable.setItemClickAction(new BaseAction("Customize") {
            @Override
            public void actionPerform(Component component) {
                AbstractEditor editor = openEditor("ricarduino$Cocktail.customize", cocktailsTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB);
            }
        });

        cocktailsTable.addGeneratedColumn("Picture", cocktail -> {
            if (cocktail.getPicture() != null) {
                //Image
                Image image = componentsFactory.createComponent(Image.class);
                image.setScaleMode(ScaleMode.CONTAIN);
                image.setHeight("150");
                image.setWidth("150");
                image.setAlignment(Alignment.TOP_LEFT);
                FileDescriptor userImageFile = cocktail.getPicture();
                image.setSource(FileDescriptorResource.class).setFileDescriptor(userImageFile);
                //vBox
                VBoxLayout vBox = componentsFactory.createComponent(VBoxLayout.class);
                vBox.setSpacing(true);
                vBox.add(image);
                vBox.setAlignment(Alignment.TOP_LEFT);
                //vBox.add(cocktailName);
                return vBox;
            } else {
                return null;
            }
        });

        cocktailsTable.addGeneratedColumn("About", cocktail -> {
            if (cocktail.getPicture() != null) {
                //Cocktail Name
                Label cocktailName = componentsFactory.createComponent(Label.class);
                cocktailName.setValue(cocktail.getName());
                cocktailName.setAlignment(Alignment.TOP_LEFT);
                cocktailName.setStyleName(HaloTheme.LABEL_BOLD);
                //Cocktail description
                Label cocktailDescription = componentsFactory.createComponent(Label.class);
                cocktailDescription.setHtmlEnabled(true);
                StringBuilder sb = new StringBuilder();
                sb.append("<div style=\"word-wrap: break-word; width: 500px; white-space: normal\">");
                sb.append(cocktail.getDescription());
                sb.append("</div>");
                cocktailDescription.setValue(sb.toString());
                cocktailDescription.setAlignment(Alignment.TOP_LEFT);
                //vBox
                VBoxLayout vBox = componentsFactory.createComponent(VBoxLayout.class);
                vBox.setSpacing(true);
                vBox.add(cocktailName);
                vBox.add(cocktailDescription);
                vBox.setAlignment(Alignment.TOP_LEFT);
                return vBox;
            } else {
                return null;
            }
        });

        cocktailsTable.addGeneratedColumn("Ingredients", cocktail -> {
            List<CocktailLine> cocktailLineSet = cocktail.getCocktailLines();
            Label ingredients = componentsFactory.createComponent(Label.class);
            ingredients.setHtmlEnabled(true);
            StringBuilder sb = new StringBuilder();
            sb.append("<B>  Ingredients :</B>");
            sb.append("<ul style=\"margin-right: 1em;\">");
            for (CocktailLine cocktailLine : cocktailLineSet) {
                sb.append("<li>").append(cocktailLine.getParts().toString().replaceAll(".0","")).append(" parts of ").append(cocktailLine.getIngredient().getName()).append("</li>");
            }
            sb.append("</ul>");
            ingredients.setValue(sb.toString());
            ingredients.setAlignment(Alignment.TOP_LEFT);
            return ingredients;
        });

        availableOptionsGroup.addValueChangeListener(event -> {
            if ("Available cocktails".equals(event.getValue())) {
                FilterEntity filterEntity = metadata.create(FilterEntity.class);
                filterEntity.setName("AvailableCocktailOnly");
                String filterXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "\n" +
                        "<filter>\n" +
                        "  <and>\n" +
                        "    <c name=\"utHSseLjYa\" unary=\"true\" width=\"1\" type=\"CUSTOM\" locCaption=\"Only available ?\" entityAlias=\"e\"><![CDATA[{E}.id not in (select e.id from ricarduino$Cocktail e join e.cocktailLines li where li.ingredient.available = false)]]>\n" +
                        "      <param name=\"component$filter.utHSseLjYa52527\" javaClass=\"java.lang.Boolean\">true</param>\n" +
                        "    </c>\n" +
                        "  </and>\n" +
                        "</filter>\n";
                filterEntity.setXml(filterXml);
                filter.setFilterEntity(filterEntity);
                filter.apply(false);
            } else {
                cocktailsDs.setQueryFilter(null);
                cocktailsDs.refresh();
                //filter.apply(false);
            }
        });
    }

    public void onDetailsBtnClick() {
        AbstractEditor editor = openEditor("ricarduino$Cocktail.customize", cocktailsTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB);
    }

    public void onDrinkBtnClick() {
        if (cocktailsTable.getSingleSelected()!=null) {
            Boolean cocktailAvailable = true;
            String missingIngredient = "";
            for (CocktailLine cocktailLine : cocktailsTable.getSingleSelected().getCocktailLines()) {
                if (!cocktailLine.getIngredient().getAvailable()) {
                    cocktailAvailable = false;
                    missingIngredient = cocktailLine.getIngredient().getName();
                    break;
                }
            }
            if (cocktailAvailable) {
                showNotification("Preparing a " + cocktailsTable.getSingleSelected().getName() + " !", "Drink responsibly...", NotificationType.HUMANIZED);
                cocktailsTable.getSingleSelected().prepareCocktail();
            } else {
                showNotification("At least one missing ingredient !", "Ingredient : " + missingIngredient, NotificationType.HUMANIZED);
            }
        }
    }

}