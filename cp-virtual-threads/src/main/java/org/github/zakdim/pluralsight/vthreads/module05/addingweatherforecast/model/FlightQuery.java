package org.github.zakdim.pluralsight.vthreads.module05.addingweatherforecast.model;

public record FlightQuery(String from, String to) {

    public Flight readFromGlobalAirlines() {
        return Flight.readFromGlobalAirlines(from, to);
    }

    public Flight readFromPlanetAirlines() {
        return Flight.readFromPlanetAirlines(from, to);
    }

    public Flight readFromAlphaAirlines() {
        return Flight.readFromAlphaAirlines(from, to);
    }
}
