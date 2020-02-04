package snake.engine;

/**
 * 
 * Thanks to this class we know duration of a turn
 * 
 * @author Yevhenii Kozhevin
 *
 */
class Stopwatch {
	/**
	 * When a turn was started
	 */
	private long startingTime;
	/**
	 * Duration of a turn in minutes
	 */
	private int timeMinutes;

	/**
	 * The method starts the countdown
	 */
	void start() {
		startingTime = getCurrentTimeMillis();
	}

	/**
	 * Returns the current time in milliseconds
	 * 
	 * @return the difference, measured in milliseconds, between the current time
	 *         and midnight, January 1, 1970 UTC.
	 */
	private long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * Sets a turn's ending time
	 */
	void stop() {
		timeMinutes = (int) ((getCurrentTimeMillis() - startingTime) / 60000);
	}

	/**
	 * Getter for a turn's duration
	 * 
	 * @return duration in minutes
	 */
	int getTimeMinutes() {
		return timeMinutes;
	}
}
