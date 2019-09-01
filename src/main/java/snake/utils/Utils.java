package snake.utils;

public class Utils {
    //  Utils class is not creating for instantiation
    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class is not creating for instantiation");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }
}
