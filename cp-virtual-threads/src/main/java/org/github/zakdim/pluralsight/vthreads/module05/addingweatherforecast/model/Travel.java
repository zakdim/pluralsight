package org.github.zakdim.pluralsight.vthreads.module05.addingweatherforecast.model;

import org.github.zakdim.pluralsight.vthreads.module05.addingweatherforecast.scope.TravelScope;

public interface Travel {

    int price();

    String from();

    String to();

    String airline();

    static Travel readTravel(TravelQuery travelQuery1, TravelQuery travelQuery2, TravelQuery travelQuery0) {
        try (var scope = new TravelScope()) {

            scope.fork(() -> MultiLegFlight.readMultiLegFlight(travelQuery1, travelQuery2));
            scope.fork(() -> Flight.readFlight(travelQuery0));

            scope.join();

            return scope.bestTravel();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}