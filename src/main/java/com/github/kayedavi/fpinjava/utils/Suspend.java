package com.github.kayedavi.fpinjava.utils;

import java.util.function.Supplier;

public record Suspend<T>(Supplier<TailCall<T>> resumeFunction) implements TailCall<T> {
    public TailCall<T> resume() {
        return resumeFunction().get();
    }
}