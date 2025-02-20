import com.github.kayedavi.fpinjava.utils.Return;
import com.github.kayedavi.fpinjava.utils.Suspend;
import com.github.kayedavi.fpinjava.utils.TailCall;

int abs(int n) {
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
int factorial(int n) {
    return go(n, 1).eval();
}

TailCall<Integer> go(int n, int acc) {
    if (n <= 0) return new Return<>(acc);
    else return new Suspend<>(() -> go(n - 1, acc));
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