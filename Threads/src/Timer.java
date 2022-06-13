
public class Timer {
    private Thread thread;

    private boolean isCancelled;

    public Timer() {
        isCancelled = false;
    }

    public void start(long waitTime) {
        thread = new Thread(() -> {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                // do nothing
                // Thread.currentThread().interrupt();
            }
        });
        thread.start();
    }
    
    public void start(long waitTime, Runnable runnable) {
        thread = new Thread(() -> {
            while (!isCancelled) {
                try {
                    Thread.sleep(waitTime);
                    runnable.run();
                    break;
                } catch (InterruptedException e) {
                    // if (isCancelled) break;
                    // Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
    }

    public void cancel() {
        isCancelled = true;
        thread.interrupt();
    }

    public void reset() {
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        // timer.start(1000);
        // System.out.println("finished waiting");

        timer.start(4000, () -> {
            System.out.println("play!");
        });

        Thread.sleep(3000);

        timer.reset();
        timer.cancel();
    }
}
