import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteThreadExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> x = executor.submit(ExecuteThreadExample2::longTask);
        Future<Integer> y = executor.submit(ExecuteThreadExample2::longTask);
        Future<Integer> z = executor.submit(ExecuteThreadExample2::longTask);
        

        x.cancel(true);
        
        try {
            x.get();
            System.out.println("got x");
        } catch (CancellationException e) { }

        y.get();
        System.out.println("got y");
        z.get();
        System.out.println("got z");

        executor.shutdown();
    }

    public static int longTask() throws InterruptedException {
        try {
            System.out.println("running a task");
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("I was interrupted. bye bye!");
            throw e;
        }
        return 0;
    }

    
}
