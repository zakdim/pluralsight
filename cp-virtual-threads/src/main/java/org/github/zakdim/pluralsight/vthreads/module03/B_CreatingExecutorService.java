package org.github.zakdim.pluralsight.vthreads.module03;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Create by dmitri on 2024-04-03.
 */
public class B_CreatingExecutorService {

    public static void main(String[] args) {
        var set = ConcurrentHashMap.<String>newKeySet();
        Runnable task = () -> set.add(Thread.currentThread().toString());

//        int N_TASKS = 2;
//        int N_TASKS = 10;
//
//        try (var es1 = Executors.newFixedThreadPool(10)) {
//            for (int index = 0; index < N_TASKS; index++) {
//                es1.submit(task);
//            }
//        }
//
//        System.out.println(STR."# threads used = \{set.size()}");
//        set.forEach(System.out::println);

        int N_TASKS = 2000;

        try (var es1 = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int index = 0; index < N_TASKS; index++) {
                es1.submit(task);
            }
        }

        System.out.println(STR."# threads used = \{set.size()}");
    }

}
