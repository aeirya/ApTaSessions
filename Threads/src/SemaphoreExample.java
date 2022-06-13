import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semAlice = new Semaphore(1);
        Semaphore semBob = new Semaphore(0);

        Thread alice = new Thread(
            () -> {
                try {
                    for (int i=0; i<3; ++i) {
                        semAlice.acquire();
                        ++x;
                        System.out.println("increasing");
                        sleep();
                        System.out.println("alice says x is " + x);
                        semBob.release();
                    }
                } catch (InterruptedException e) { }
            }
        );
            
        Thread bob = new Thread(
            () -> {
                try {
                    for (int i=0; i<2; ++i) {
                        semBob.acquire();
                        --x;
                        System.out.println("decreasing");
                        sleep();
                        System.out.println("bob says x is " + x);
                        semAlice.release();
                    }
                } catch (InterruptedException e) { }
            }
        );

        alice.start();
        bob.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
