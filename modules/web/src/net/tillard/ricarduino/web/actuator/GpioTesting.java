package net.tillard.ricarduino.web.actuator;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.TextField;
import net.tillard.ricarduino.service.GpioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class GpioTesting extends AbstractFrame {

    @Inject
    private TextField gpioNumberField;
    @Inject
    private TextField numberOfPulseField;
    @Inject
    private TextField pauseDurationField;
    @Inject
    private TextField pulseDurationField;

    @Inject
    private GpioService gpioService;

    private static Logger log = LoggerFactory.getLogger(GpioTesting.class);

    public void onTestGpioBtnClick() {
        Integer gpioNumber = gpioNumberField.getValue();
        Long pulseDuration = pulseDurationField.getValue();
        Integer numberOfPulse = numberOfPulseField.getValue();
        Long pauseDuration = pauseDurationField.getValue();
        if (gpioNumber != null && pulseDuration != null && numberOfPulse != null && pauseDuration != null) {
            log.info("gpioNumber=" + gpioNumber.toString()
                    + "|pulseDuration=" + pulseDuration.toString()
                    + "|numberOfPulse=" + numberOfPulse.toString()
                    + "|pauseDuration=" + pauseDuration.toString());
            gpioService.testGpio(gpioNumber,pulseDuration,numberOfPulse,pauseDuration);
        } else {
            showNotification("All the fields are required", "", NotificationType.HUMANIZED);
        }
    }

    public void onCancelBtnClick() {
        //this.close(WINDOW_CLOSE, true);
    }
}