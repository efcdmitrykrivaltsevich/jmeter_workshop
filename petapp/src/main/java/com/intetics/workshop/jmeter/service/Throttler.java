package com.intetics.workshop.jmeter.service;

import java.util.Random;

import static org.springframework.util.Assert.isTrue;

public final class Throttler {

    public static final Random RANDOM = new Random();

    private Throttler() {
    }

    /**
     * Throttle current thread for specified amount of seconds.
     *
     * @param min throttle for {@code min} seconds
     * @param max throttle for {@code max} seconds
     */
    public static void throttle(int min, int max) {
        isTrue(min <= max);

        try {
            Thread.sleep((Math.min(min + RANDOM.nextInt(max), max)));
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
