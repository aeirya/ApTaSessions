import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryCatch {
    public static void main(String[] args) {
        try {
            new Scanner(new File("invalidPath")).nextLine();
        } catch (FileNotFoundException e) {

        } catch (InterruptedException e) {

        } catch ()
    }

    public void interrupt() throws InterruptedException {
        while (true) {
            // waste time
            if (keyPressed()) {
                throw new InterruptedException();
            }
        }
    }
}
