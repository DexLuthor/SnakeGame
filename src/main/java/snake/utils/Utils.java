package snake.utils;

public class Utils {
	private Utils() throws IllegalAccessException {
		throw new IllegalAccessException("Useless instance of Utils class");
	}

	/**
	 * Wrapper method for Thread.sleep(int millis) method
	 * 
	 * @param millis time for thread sleep
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException("Exception while sleep method", e);
		}
	}
}
