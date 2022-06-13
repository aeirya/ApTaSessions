import javax.swing.plaf.synth.SynthButtonUI;

public class JavaTools {
    synchronized void myMethod() {
        Thread.sleep(100);
        System.out.println("");
    }

    void myOtherMethod() {
        synchronized(this) {

        }
    }

    

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                synchronized (object) {
                    object.notifyAll();
                }
            } catch (InterruptedException e) {

            }
        }).start();

        synchronized (object) {
            object.wait();
        }
        System.out.println("HI");
    }

}
