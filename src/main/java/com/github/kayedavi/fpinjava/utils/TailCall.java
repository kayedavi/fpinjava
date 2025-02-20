package com.github.kayedavi.fpinjava.utils;

public interface TailCall<T> {
    default T eval() {
        var tailRec = this;
        while (tailRec instanceof Suspend<T> suspend)
            tailRec = suspend.resume();
        return tailRec.eval();
    }
}