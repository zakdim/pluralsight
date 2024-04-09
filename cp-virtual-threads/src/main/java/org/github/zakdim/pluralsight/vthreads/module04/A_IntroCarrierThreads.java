package org.github.zakdim.pluralsight.vthreads.module04;

import java.util.concurrent.Executors;

/**
 * Create by dmitri on 2024-04-04.
 */
public class A_IntroCarrierThreads {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> System.out.println(STR."I am running in the thead \{Thread.currentThread()}");

        var thread = Thread.ofVirtual()
                .name("My virtual thread")
                .unstarted(task);

        thread.start();
        thread.join();

        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            es.submit(task);
        }
    }
}
