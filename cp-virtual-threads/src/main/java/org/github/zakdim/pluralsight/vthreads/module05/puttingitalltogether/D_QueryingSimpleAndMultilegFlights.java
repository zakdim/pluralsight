package org.github.zakdim.pluralsight.vthreads.module05.puttingitalltogether;

import org.github.zakdim.pluralsight.vthreads.module05.puttingitalltogether.model.Travel;
import org.github.zakdim.pluralsight.vthreads.module05.puttingitalltogether.model.TravelQuery;

import java.time.Duration;
import java.time.Instant;

/**
 * Create by dmitri on 2024-04-21.
 */
public class D_QueryingSimpleAndMultilegFlights {

    public static void main(String[] args) {

        String from = "New York";
        String via = "Atlanta";
        String to = "San Francisco";

        TravelQuery travelQuery0 = new TravelQuery(from, to);
        TravelQuery travelQuery1 = new TravelQuery(from, via);
        TravelQuery travelQuery2 = new TravelQuery(via, to);

        var begin = Instant.now();
        Travel.readTravel(travelQuery1, travelQuery2, travelQuery0);
        var end = Instant.now();

        System.out.println(STR."Done in \{Duration.between(begin, end).toMillis()}ms");
    }
}
