import com.github.kayedavi.fpinjava.gettingstarted.MyProgram;

import static com.github.kayedavi.fpinjava.gettingstarted.MyProgram.formatResult;

void main() {
    println(formatResult("absolute value", -42, MyProgram::abs));
    println(formatResult("factorial", 7, MyProgram::factorial));
    println(formatResult("increment", 7, x -> x + 1));
    println(formatResult("increment2", 7, (x) -> x + 1));
    println(formatResult("increment3", 7, x -> x + 1));
    println(formatResult("increment4", 7, x -> x + 1));
    println(formatResult("increment5", 7, x -> { var r = x + 1; return r; }));
}