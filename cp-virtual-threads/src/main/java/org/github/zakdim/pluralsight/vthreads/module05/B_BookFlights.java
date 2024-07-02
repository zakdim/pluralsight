package org.github.zakdim.pluralsight.vthreads.module05;

import java.time.Duration;
import java.time.Instant;

/**
 * Create by dmitri on 2024-04-10.
 */
public class B_BookFlights {

    public static void main(String[] args) {

        String from = "New York";
        String to = "San Francisco";

        var start = Instant.now();
        Flight bestFlight = Flight.readFlight(from, to);
        var end = Instant.now();

        System.out.println(STR."Best flight = \{bestFlight} in \{Duration.between(start, end).toMillis()}");
    }
}
