package org.github.zakdim.pluralsight.vthreads.module05;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.Stream;

public record Flight(String from, String to, int price, String airline) {

    private static Random random = new Random();

    public static Flight readFlight(String from, String to) {

        FlightQuery query = new FlightQuery(from, to);

        try (var scope = new StructuredTaskScope<Flight>()) {
            var t1 = scope.fork(query::readFromAlphaAirlines);
            var t2 = scope.fork(query::readFromGlobalAirlines);
            var t3 = scope.fork(query::readFromPlanetAirlines);

            scope.join();

            var bestFlight = Stream.of(t1, t2, t3)
                    .filter(t -> t1.state() == StructuredTaskScope.Subtask.State.SUCCESS)
                    .map(StructuredTaskScope.Subtask::get)
                    .min(Comparator.comparing(Flight::price))
                    .get();

            return bestFlight;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Flight readFromAlphaAirlines(String from, String to) {
        sleepFor(random.nextInt(80, 100), ChronoUnit.MILLIS);
        return new Flight(from, to,
                random.nextInt(70, 120),
                "Alpha Air Lines");
    }

    public static Flight readFromGlobalAirlines(String from, String to) {
        sleepFor(random.nextInt(90, 110), ChronoUnit.MILLIS);
        return new Flight(from, to,
                random.nextInt(60, 90),
                "Global Air Lines");
    }

    public static Flight readFromPlanetAirlines(String from, String to) {
        sleepFor(random.nextInt(70, 120), ChronoUnit.MILLIS);
        return new Flight(from, to,
                random.nextInt(70, 90),
                "Planet Air Lines");
    }

    private static void sleepFor(int amount, ChronoUnit unit) {
        try {
            Thread.sleep(Duration.of(amount, unit));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
