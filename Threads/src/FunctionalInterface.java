public class FunctionalInterface {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("hi!");
        }
        runnable.run();

        Callable<Integer> callable = () -> 2;
        
        Consumer<Integer> consumer = i -> System.out.println(i);
        Predicate<Integer> predicate = i -> i % 2 == 0;

    }
}
