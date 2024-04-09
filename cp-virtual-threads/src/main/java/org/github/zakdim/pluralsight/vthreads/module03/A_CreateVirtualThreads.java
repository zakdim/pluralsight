package org.github.zakdim.pluralsight.vthreads.module03;

/**
 * Create by dmitri on 2024-04-03.
 */
public class A_CreateVirtualThreads {

    public static void main(String[] args) throws InterruptedException {

        // OLD: classic thread creation
        Runnable task = () -> {
            System.out.println(STR."I am running in the thread \{Thread.currentThread().getName()}");
            System.out.println(STR."I am running in daemon thread? \{Thread.currentThread().isDaemon()}");
        };

        Thread thread1 = new Thread(task);
        thread1.start();
        thread1.join();

        // NEW: platform thread creation
        Thread thread2 = Thread.ofPlatform()
                .daemon()
                .name("Platform thread 2")
                .unstarted(task);
        thread2.start();
        thread2.join();

        // NEW: virtual thread creation
        Thread thread3 = Thread.ofVirtual()
                .name("Virtual thread 3")
                .unstarted(task);
        thread3.start();
        thread3.join();

        // NEW: another way of creating and starting the virtual thread
        Thread thread4 = Thread.startVirtualThread(task);
        thread4.join();

    }
}
