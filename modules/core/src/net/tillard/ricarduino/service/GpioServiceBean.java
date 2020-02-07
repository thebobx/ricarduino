package net.tillard.ricarduino.service;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.sys.AppContext;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;

import javax.annotation.PostConstruct;

@Service(GpioService.NAME)
public class GpioServiceBean implements GpioService, AppContext.Listener {

    private static Logger log = LoggerFactory.getLogger(GpioServiceBean.class);

    private GpioController gpioController = null;

    GpioPinDigitalOutput gpio1 = null;
    GpioPinDigitalOutput gpio2 = null;
    GpioPinDigitalOutput gpio3 = null;
    GpioPinDigitalOutput gpio4 = null;
    GpioPinDigitalOutput gpio5 = null;
    GpioPinDigitalOutput gpio6 = null;
    GpioPinDigitalOutput gpio7 = null;
    GpioPinDigitalOutput gpio8 = null;
    GpioPinDigitalOutput gpio9 = null;
    GpioPinDigitalOutput gpio10 = null;

    @PostConstruct
    public void init() {
        AppContext.addListener(this);
    }

    @Override
    public void applicationStarted() {
        log.info("Dans applicationStarted de GpioServiceBean");
        if (true) {//SystemUtils.IS_OS_WINDOWS
            log.info("No GPIO on Windows !");
        } else {
            gpioController = GpioFactory.getInstance();

            gpio1 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01,"gpio1",PinState.HIGH);
            gpio2 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02,"gpio2",PinState.HIGH);
            gpio3 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03,"gpio3",PinState.HIGH);
            gpio4 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04,"gpio4",PinState.HIGH);
            gpio5 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05,"gpio5",PinState.HIGH);
            gpio6 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06,"gpio6",PinState.HIGH);
            gpio7 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07,"gpio7",PinState.HIGH);
            gpio8 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08,"gpio8",PinState.HIGH);
            gpio9 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_09,"gpio9",PinState.HIGH);
            gpio10 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_10,"gpio10",PinState.HIGH);

            gpio1.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio2.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio3.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio4.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio5.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio6.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio7.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio8.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio9.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
            gpio10.setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);

            log.info("Gpio init done");
        }
    }

    @Override
    public void applicationStopped() {
        gpioController.shutdown();
        log.info("Dans applicationStopped");
    }

    @Override
    public void pulseGpio(Integer gpioNumber, long duration) {
        log.info("Pulsing gpio : " + gpioNumber.toString() + " for " + duration + " milliseconds");
        if (true) {
            log.info("No GPIO on Windows !");
        } else {
            ((GpioPinDigitalOutput)gpioController.getProvisionedPin("gpio"+gpioNumber.toString())).pulse(duration, PinState.LOW);
            log.info("Pulsing done");
        }
    }

    @Override
    public void highGpio(Integer gpioNumber) {
        log.info("Set high gpio : " + gpioNumber.toString());
        if (true) {
            log.info("No GPIO on Windows !");
        } else {
            ((GpioPinDigitalOutput)gpioController.getProvisionedPin("gpio"+gpioNumber.toString())).high();
            log.info("Setting done");
        }
    }

    @Override
    public void lowGpio(Integer gpioNumber) {
        log.info("Set low gpio : " + gpioNumber.toString());
        if (true) {
            log.info("No GPIO on Windows !");
        } else {
            ((GpioPinDigitalOutput)gpioController.getProvisionedPin("gpio"+gpioNumber.toString())).low();
            log.info("Setting done");
        }
    }

    @Override
    public void testGpio(Integer gpioNumber, long pulseDuration, Integer nbPulses, long waitDuration) {
        log.info("TestGpio : gpioNumber=" + gpioNumber.toString() + "|pulseDuration=" + pulseDuration + "|nbPulses=" + nbPulses.toString() + "|waitDuration=" + waitDuration);
        for (int i = 0; i < nbPulses; i++) {
            if (true) {
                log.info("No GPIO on Windows !");
            } else {
                log.info("Pulse number " + i);
                ((GpioPinDigitalOutput) gpioController.getProvisionedPin("gpio" + gpioNumber.toString())).pulse(pulseDuration, PinState.LOW);
            }
            try {
                Thread.sleep(waitDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Testing done");
    }

}