import com.github.kayedavi.fpinjava.gettingstarted.MyProgram;

import static com.github.kayedavi.fpinjava.gettingstarted.MyProgram.formatResult;

// Now we can use our general `formatResult` function
// with both `abs` and `factorial`
void main() {
    println(formatResult("absolute value", -42, MyProgram::abs));
    println(formatResult("factorial", 7, MyProgram::factorial));
}