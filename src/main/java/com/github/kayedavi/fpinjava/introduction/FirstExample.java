package com.github.kayedavi.fpinjava.introduction;

import static java.io.IO.println;

public class FirstExample {
    public record CreditCard() {
        public void charge(double price) {
            println("charging " + price);
        }
    }

    public record Coffee(double price) {
        public Coffee() {
            this(2.0d);
        }
    }

    public class Cafe {
        public Coffee buyCoffee(CreditCard cc) {
            var cup = new Coffee();
            cc.charge(cup.price());
            return cup;
        }
    }
}
