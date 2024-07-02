package org.github.zakdim.pluralsight.vthreads.module05.addingweatherforecast.model;

/**
 * Create by dmitri on 2024-07-01.
 */
public class NoFlight implements Travel {
    @Override
    public int price() {
        return 0;
    }

    @Override
    public String from() {
        return null;
    }

    @Override
    public String to() {
        return null;
    }

    @Override
    public String airline() {
        return null;
    }
}
