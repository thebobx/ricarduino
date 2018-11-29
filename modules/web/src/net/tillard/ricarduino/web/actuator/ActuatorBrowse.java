package net.tillard.ricarduino.web.actuator;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.AbstractLookup;

public class ActuatorBrowse extends AbstractLookup {

    public void onTestGpioBtnClick() {

        AbstractFrame gpioTesting = openFrame(this,"ricarduino$Gpio.test");
                //openWindow("ricarduino$Gpio.test",WindowManager.OpenType.NEW_TAB);
                //openEditor("ricarduino$Gpio.test", cocktailsTable.getSingleSelected(), WindowManager.OpenType.NEW_TAB, parameters);
    }
}