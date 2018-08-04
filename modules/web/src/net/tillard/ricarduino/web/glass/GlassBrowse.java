package net.tillard.ricarduino.web.glass;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import net.tillard.ricarduino.entity.Glass;

import javax.inject.Inject;
import java.util.Map;

public class GlassBrowse extends AbstractLookup {

    @Inject
    private GroupTable<Glass> glassesTable;

    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {

        glassesTable.addGeneratedColumn("picture", glass -> {
            if (glass.getPicture() != null) {
                Image image = componentsFactory.createComponent(Image.class);
                image.setScaleMode(Image.ScaleMode.CONTAIN);
                image.setHeight("100");
                image.setWidth("100");

                FileDescriptor userImageFile = glass.getPicture();
                image.setSource(FileDescriptorResource.class).setFileDescriptor(userImageFile);

                HBoxLayout hBox = componentsFactory.createComponent(HBoxLayout.class);
                hBox.setSpacing(true);
                hBox.add(image);
                return hBox;
            } else {
                return null;
            }
        });
    }
}