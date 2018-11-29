package net.tillard.ricarduino.web.actuator;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.Datasource;
import net.tillard.ricarduino.entity.Actuator;
import net.tillard.ricarduino.service.GpioService;

import javax.inject.Inject;

public class ActuatorEdit extends AbstractEditor<Actuator> {

    @Inject
    private Datasource<Actuator> actuatorDs;

    @Inject
    private TextField pulseField;

    public void onPulseBtnClick() {
        AppBeans.get(GpioService.class).pulseGpio(actuatorDs.getItem().getGpio(),Long.parseLong(pulseField.getRawValue()));
    }

    public void onHighBtnClick() {
        AppBeans.get(GpioService.class).highGpio(actuatorDs.getItem().getGpio());
    }

    public void onLowBtnClick() {
        AppBeans.get(GpioService.class).lowGpio(actuatorDs.getItem().getGpio());
    }
}