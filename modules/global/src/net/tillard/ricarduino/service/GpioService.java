package net.tillard.ricarduino.service;


public interface GpioService {
    String NAME = "ricarduino_GpioService";

    void activateGpio(Integer gpioNumber);

}