package com.github.kayedavi.fpinjava.utils;

import java.util.function.Supplier;

public interface TailCall<T> {
    default T eval() {
        var tailRec = this;
        while (tailRec instanceof Suspend<T> suspend)
            tailRec = suspend.resume();
        return tailRec.eval();
    }

    static <T> Return<T> ret(T t) {
        return new Return<>(t);
    }

    static <T> Suspend<T> sus(Supplier<TailCall<T>> resumeFunction) {
        return new Suspend<>(resumeFunction);
    }
}