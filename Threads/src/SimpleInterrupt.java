public class SimpleInterrupt {
    public static void main(String[] args) {
        // Thread thead = new Thread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("i was interrupted!");
                Thread.currentThread().interrupt();
            }
            System.out.println("HI from other thread");
        });

        thread.start();

        Thread.sleep(500);
        thread.interrupt();
        Thread.sleep(500);

        System.out.println("HI from main thread");
    }
}
