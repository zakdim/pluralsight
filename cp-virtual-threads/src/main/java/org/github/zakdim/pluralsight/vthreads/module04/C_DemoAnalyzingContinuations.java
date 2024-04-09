package org.github.zakdim.pluralsight.vthreads.module04;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

/**
 * Create by dmitri on 2024-04-07.
 */
public class C_DemoAnalyzingContinuations {

    public static void main(String[] args) {

        var scope = new ContinuationScope("My scope");
        var continuation = new Continuation(
                scope,
                () -> {
                    System.out.println("Running in continuation");
                    Continuation.yield(scope);
                    System.out.println("After the call to yield");
                });

        System.out.println("Running in the main method");
        continuation.run();
        System.out.println("Back in the main method");

        continuation.run();
        System.out.println("Back again in the main method");
    }
}
