package snake.engine;

class Stopwatch {
	private long startingTime;
	private int timeMinutes;
	
	void start() {
		startingTime = getCurrentTimeMillis();
	}

	private long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	void stop() {
		timeMinutes = (int) ((getCurrentTimeMillis() - startingTime) / 60000);
	}
	
	int getTimeMinutes() {
		return timeMinutes;
	}
}
