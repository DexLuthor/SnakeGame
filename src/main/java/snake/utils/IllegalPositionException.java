package snake.utils;

public class IllegalPositionException extends Exception {

    public IllegalPositionException() {
    }
    public IllegalPositionException(String message) {
        super(message);
    }
    public IllegalPositionException(String message, Throwable cause) {
        super(message, cause);
    }
    public IllegalPositionException(Throwable cause) {
        super(cause);
    }
    public IllegalPositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
