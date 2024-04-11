package org.github.zakdim.pluralsight.vthreads.module05;

import java.util.Random;

/**
 * Create by dmitri on 2024-04-10.
 */
public class A_Weather {

    public static void main(String[] args) {

        var weather = Weather.readWeather();
        System.out.println(STR."weather: \{weather}");
    }
}

