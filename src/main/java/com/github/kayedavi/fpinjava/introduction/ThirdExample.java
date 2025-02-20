package com.github.kayedavi.fpinjava.introduction;

import com.github.kayedavi.fpinjava.introduction.utils.Tuple2;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

public class ThirdExample {
    public static class Cafe {
        public Tuple2<Coffee, Charge> buyCoffee(CreditCard cc) {
            var cup = new Coffee();
            return new Tuple2<>(cup, new Charge(cc, cup.price()));
        }

        public Tuple2<List<Coffee>, Charge> buyCoffees(CreditCard cc, int n) {
            var purchases = new ArrayList<Tuple2<Coffee, Charge>>();
            for (var i = 0; i < n; i++)
                purchases.add(buyCoffee(cc));
            var coffees = purchases.stream().map(Tuple2::_1).toList();
            var charges = purchases.stream().map(Tuple2::_2).toList();
            var combinedCharge = charges.stream().reduce(Charge::combine).orElseThrow(() -> new RuntimeException("Error combining charges"));
            return new Tuple2<>(coffees, combinedCharge);
        }
    }

    public record CreditCard() {
    }

    public record Charge(CreditCard cc, double amount) {
        public Charge combine(Charge other) {
            if (cc.equals(other.cc()))
                return new Charge(cc, amount() + other.amount());
            else
                throw new RuntimeException("Can't combine charges to different cards");
        }
    }

    public record Coffee(double price) {
        public Coffee() {
            this(2.0d);
        }
    }

    public List<Charge> coalesce(List<Charge> charges) {
        return charges.stream()
                .collect(groupingBy(Charge::cc))
                .values()
                .stream()
                .map(list -> list.stream()
                        .reduce(Charge::combine)
                        .orElseThrow(() -> new RuntimeException("Error combining charges")))
                .toList();
    }
}