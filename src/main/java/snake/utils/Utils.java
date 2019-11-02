package snake.utils;

public class Utils {
	private Utils() throws IllegalAccessException {
		throw new IllegalAccessException("Utils class is not created for instantiation");
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException("Exception while sleep method", e);
		}
	}
}
