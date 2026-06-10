package org.storm.cpuintensive;

public class FibonacciSequence {

    public static long findFibonacci(long input) {
        if (input < 2) {
            return input;
        }
        return findFibonacci(input-1) + findFibonacci(input-2);
    }

}
