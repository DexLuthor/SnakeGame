package Snake.Utils;

public class Utils {
    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }
}
