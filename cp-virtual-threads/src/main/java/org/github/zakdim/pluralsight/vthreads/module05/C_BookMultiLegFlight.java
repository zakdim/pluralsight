package org.github.zakdim.pluralsight.vthreads.module05;

import java.time.Duration;
import java.time.Instant;

import static org.github.zakdim.pluralsight.vthreads.module05.MultiLegFlight.readMultiLegFlight;

/**
 * Create by dmitri on 2024-04-21.
 */
public class C_BookMultiLegFlight {

    public static void main(String[] args) {

        String from = "New York";
        String via = "Atlanta";
        String to = "San Francisco";

        var start = Instant.now();
        FlightQuery flightQuery1 = new FlightQuery(from, via);
        FlightQuery flightQuery2 = new FlightQuery(via, to);

        MultiLegFlight travel = readMultiLegFlight(flightQuery1, flightQuery2);

        var end = Instant.now();
        System.out.println(STR."Flight = \{travel} in \{Duration.between(start, end).toMillis()}ms");
    }
}
