package org.github.zakdim.pluralsight.vthreads.module05.puttingitalltogether.scope;

import org.github.zakdim.pluralsight.vthreads.module05.puttingitalltogether.model.FlightException;
import org.github.zakdim.pluralsight.vthreads.module05.puttingitalltogether.model.Travel;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.StructuredTaskScope;

/**
 * Create by dmitri on 2024-07-01.
 */
public class TravelScope extends StructuredTaskScope<Travel> {

    private volatile Collection<Travel> travels = new ConcurrentLinkedQueue<>();
    private volatile Collection<Throwable> exceptions = new ConcurrentLinkedQueue<>();

    @Override
    protected void handleComplete(Subtask<? extends Travel> subtask) {
        switch (subtask.state()) {
            case UNAVAILABLE -> throw new IllegalStateException("Task should be done");
            case SUCCESS -> this.travels.add(subtask.get());
            case FAILED -> this.exceptions.add(subtask.exception());
        }
    }

    public FlightException exceptions() {
        FlightException flightException = new FlightException();
        this.exceptions.forEach(flightException::addSuppressed);
        return flightException;
    }

    public Travel bestTravel() {
        return this.travels.stream()
                .min(Comparator.comparingInt(Travel::price))
                .orElseThrow(this::exceptions);
    }
}
