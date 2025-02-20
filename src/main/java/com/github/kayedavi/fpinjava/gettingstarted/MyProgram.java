package com.github.kayedavi.fpinjava.gettingstarted;

import com.github.kayedavi.fpinjava.utils.Return;
import com.github.kayedavi.fpinjava.utils.Suspend;
import com.github.kayedavi.fpinjava.utils.TailCall;

import java.util.function.IntUnaryOperator;

import static java.io.IO.println;

public class MyProgram {
    public static int abs(int n) {
        if (n < 0) return -n;
        else return n;
    }

    private String formatAbs(int x) {
        var msg = "The absolute value of %d is %d";
        return msg.formatted(x, abs(x));
    }

    void main() {
        println(formatAbs(-42));
    }

    // A definition of factorial, using a local, tail recursive function
    public static int factorial(int n) {
        return factorial(n, 1).eval();
    }

    private static TailCall<Integer> factorial(int n, int acc) {
        if (n <= 0) return new Return<>(acc);
        else return new Suspend<>(() -> factorial(n - 1, acc));
    }

    //  Another implementation of `factorial`, this time with a `while` loop
    int factorial2(int n) {
        var acc = 1;
        var i = n;
        while (i > 0) {
            acc *= i;
            i -= 1;
        }
        return acc;
    }

    //  Exercise 1: Write a function to compute the nth fibonacci number
    //
    //  0 and 1 are the first two numbers in the sequence,
    //  so we start the accumulators with those.
    //  At every iteration, we add the two numbers to get the next one.
    int fib(int n) {
        return fib(n, 0, 1).eval();
    }

    private TailCall<Integer> fib(int n, int current, int next) {
        if (n <= 0) return new Return<>(current);
        else return new Suspend<>(() -> fib(n - 1, next, current + next));
    }

    //  This definition and `formatAbs` are very similar..
    private String formatFactorial(int n) {
        var msg = "The factorial of %d is %d.";
        return msg.formatted(n, factorial(n));
    }

    //  We can generalize `formatAbs` and `formatFactorial` to
    //  accept a _function_ as a parameter
    public static String formatResult(String name, int n, IntUnaryOperator f) {
        var msg = "The %s of %d is %d.";
        return msg.formatted(name, n, f.applyAsInt(n));
    }
}