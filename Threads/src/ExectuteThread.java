import java.security.spec.ECField;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExectuteThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int i;
        ExecutorService executor = Executors.newFixedThreadPool(i);
        
        executor.execute(new RunnableThreadExample());
        
        executor.execute(() -> {
            System.out.println("HI");
        });

        executor.execute(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("HI");
                }
            }
        );

        Future<Boolean> future = executor.submit(() -> {
            // connnect
            Thread.sleep(10000);
            return true;
        });

        int x = future.get();

        int y;
        try {
            y = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("y is " + y);
    }
}
