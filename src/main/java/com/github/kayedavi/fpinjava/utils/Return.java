package com.github.kayedavi.fpinjava.utils;

public record Return<T>(T t) implements TailCall<T> {
    public T eval() {
        return t;
    }
}
