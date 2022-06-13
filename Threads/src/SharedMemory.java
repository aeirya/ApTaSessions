import java.util.concurrent.Semaphore;

public class SharedMemory {
    
    static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        Thread alice = new Thread(
            () -> {
                try {
                    for (int i=0; i<3; ++i) {
                            semaphore.acquire();
                            ++x;
                            System.out.println("increasing");
                            sleep();
                            System.out.println("alice says x is " + x);
                            semaphore.release();
                    }
                } catch (InterruptedException e) {}
            }
        );
            
        Thread bob = new Thread(
            () -> {
                try {
                    for (int i=0; i<2; ++i) {
                        semaphore.acquire();
                        --x;
                        System.out.println("decreasing");
                        sleep();
                        System.out.println("bob says x is " + x);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {}
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
