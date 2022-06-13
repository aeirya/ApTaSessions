import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionAsArgument {
     void f() {
        System.out.println("HI");
    }

    void g(int x) {
        System.out.println("x is " + x);
    }

    boolean pred(int x) {
        return x % 2 == 0;
    }

    public static void main(String[] args) {
        FunctionAsArgument obj = new FunctionAsArgument();
        
        Runnable runnable = obj::f;
        Consumer<Integer> consumer = obj::g;
        Predicate<Integer> predicate = obj::pred;
    }

    public void useFuncAsArg(Runnable runnable) {
        runnable.run();
    }
}
