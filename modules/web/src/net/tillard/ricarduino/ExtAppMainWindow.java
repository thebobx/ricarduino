package net.tillard.ricarduino;

import com.haulmont.cuba.gui.components.AbstractMainWindow;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.components.mainwindow.AppWorkArea;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.components.mainwindow.UserIndicator;

import javax.inject.Inject;
import java.util.Map;

public class ExtAppMainWindow extends AbstractMainWindow {
    @Inject
    private FtsField ftsField;

    @Inject
    private Embedded logoImage;

    @Inject
    private SideMenu sideMenu;

    @Inject
    private UserIndicator userIndicator;

    @Inject
    private AppWorkArea workArea;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initLayoutAnalyzerContextMenu(logoImage);
        initLogoImage(logoImage);
        initFtsField(ftsField);
        sideMenu.setSelectOnClick(true);
        sideMenu.setSelectedItem(sideMenu.getMenuItem("cocktailBrowse"));
        userIndicator.setUserNameFormatter(value -> value.getName());
    }
}