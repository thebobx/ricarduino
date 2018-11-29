package net.tillard.ricarduino.service;


public interface GpioService {
    String NAME = "ricarduino_GpioService";

    void pulseGpio(Integer gpioNumber, long duration);

    void highGpio(Integer gpioNumber);

    void lowGpio(Integer gpioNumber);

    void testGpio(Integer gpioNumber, long pulseDuration, Integer nbPulses, long waitDuration);

}