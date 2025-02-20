package com.github.kayedavi.fpinjava.introduction;

import com.github.kayedavi.fpinjava.introduction.utils.Tuple2;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Stream.generate;

public class ThirdExample {
    public static class Cafe {
        public Tuple2<Coffee, Charge> buyCoffee(CreditCard cc) {
            var cup = new Coffee();
            return new Tuple2<>(cup, new Charge(cc, cup.price()));
        }

        public Tuple2<List<Coffee>, Charge> buyCoffees(CreditCard cc, int n) {
            var purchases = generate(() -> buyCoffee(cc)).limit(n).toList();
            var coffees = purchases.stream().map(Tuple2::_1).toList();
            var charges = purchases.stream().map(Tuple2::_2).toList();
            var combinedCharge = charges.stream().reduce(Charge::combine).orElseThrow(RuntimeException::new);
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
                .flatMap(list -> list.stream()
                        .reduce(Charge::combine)
                        .stream())
                .toList();
    }
}