package Snake.Utils;

public class Utils {
    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class");
    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException ignored) {
        }
    }
}
