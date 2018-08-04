package net.tillard.ricarduino.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(GpioService.NAME)
public class GpioServiceBean implements GpioService {

    private static Logger log = LoggerFactory.getLogger(GpioServiceBean.class);

    @Override
    public void activateGpio(Integer gpioNumber) {
        log.info("Ingredient sur gpio : " + gpioNumber.toString());
    }
}