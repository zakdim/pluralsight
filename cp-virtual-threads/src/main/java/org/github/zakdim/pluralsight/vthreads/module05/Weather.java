package org.github.zakdim.pluralsight.vthreads.module05;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public record Weather(String weather, String server) {
    
    private static Random random = new Random();
    
    private enum WeatherForecast {
        Sunny, Rainy, Cloudy
    }

    public static Weather readWeather() {

        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<Weather>()) {

            var f1 = scope.fork(Weather::readFromInternationalWF);
            var f2 = scope.fork(Weather::readFromGlobalWF);
            var f3 = scope.fork(Weather::readFromPlanetEarthWF);

            scope.join(); // blocking code that will return when all the tasks are done

//            System.out.println(STR."F1: \{f1.state()}");
//            if (f1.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                System.out.println(STR." \{f1.get()}");
//            }
//            System.out.println(STR."F2: \{f2.state()}");
//            if (f2.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                System.out.println(STR." \{f2.get()}");
//            }
//            System.out.println(STR."F3: \{f3.state()}");
//            if (f3.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                System.out.println(STR." \{f3.get()}");
//            }

            // The first successful weather server response will be returned
            return scope.result();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Weather readFromInternationalWF() {
        sleepFor(random.nextInt(70, 120), ChronoUnit.MILLIS);
        return new Weather(
                WeatherForecast.values()[random.nextInt(0, 3)].name(),
                "International Weather Forecast");
    }

    public static Weather readFromGlobalWF() {
        throw null;
//        sleepFor(random.nextInt(80, 100), ChronoUnit.MILLIS);
//        return new Weather(
//                WeatherForecast.values()[random.nextInt(0, 3)].name(),
//                "Global Weather Forecast");
    }
    public static Weather readFromPlanetEarthWF() {
        sleepFor(random.nextInt(80, 110), ChronoUnit.MILLIS);
        return new Weather(
                WeatherForecast.values()[random.nextInt(0, 3)].name(),
                "Planet Earth Weather Forecast");
    }

    private static void sleepFor(int amount, ChronoUnit unit) {
        try {
            Thread.sleep(Duration.of(amount, unit));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
