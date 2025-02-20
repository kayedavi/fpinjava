package com.github.kayedavi.fpinjava.introduction;

import static java.io.IO.println;

public class SecondExample {
    public class Cafe {
        public Coffee buyCoffee(CreditCard cc, Payments p) {
            var cup = new Coffee();
            p.charge(cc, cup.price());
            return cup;
        }
    }

    public record CreditCard() {}

    public interface Payments {
        void charge(CreditCard cc, double price);
    }

    public class SimulatedPayments implements Payments {
        @Override
        public void charge(CreditCard cc, double price) {
            println("charging " + price + " to " + cc);
        }
    }

    public record Coffee(double price) {
        public Coffee() {
            this(2.0d);
        }
    }

    public void main(String[] args) {
        var cc = new CreditCard();
        var p = new SimulatedPayments();
        var cafe = new Cafe();
        var cup = cafe.buyCoffee(cc, p);
    }
}