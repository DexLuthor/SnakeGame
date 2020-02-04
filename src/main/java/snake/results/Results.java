package snake.results;

/**
 * Class containing results of one turn
 * 
 * @author Yevhenii Kozhevin
 *
 */
public class Results implements Comparable<Results> {
	// =============== Fields ===============
	/**
	 * Duration of a turn in minutes
	 */
	private int timeMinutes;
	/**
	 * Length of a snake
	 */
	private int length;
	/**
	 * Count of eaten apples
	 */
	private int countOfEatenApples;
	/**
	 * Count of eaten oranges
	 */
	private int countOfEatenOranges;

	// =============== Methods ===============
	public void setTimeMinutes(int timeMinutes) {
		this.timeMinutes = timeMinutes;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setCountOfEatenApples(int countOfEatenApples) {
		this.countOfEatenApples = countOfEatenApples;
	}

	public void setCountOfEatenOranges(int countOfEatenOranges) {
		this.countOfEatenOranges = countOfEatenOranges;
	}

	public int getTimeMinutes() {
		return timeMinutes;
	}

	public int getLength() {
		return length;
	}

	public int getCountOfEatenApples() {
		return countOfEatenApples;
	}

	public int getCountOfEatenOranges() {
		return countOfEatenOranges;
	}

	@Override
	public int compareTo(Results r2) {
		if (this.getLength() < r2.getLength()) {
			return -1;
		} else if (this.getLength() > r2.getLength()) {
			return 1;
		}

		if (this.getTimeMinutes() < r2.getTimeMinutes()) {
			return -1;
		} else if (this.getTimeMinutes() > r2.getTimeMinutes()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Check and updates if it is needed the best results with current
	 * 
	 * @param currentResults
	 * @return
	 */
	public static boolean check(Results currentResults) {
		Results bestResults = ResultsSerializer.deserializeExistingResults();
		if (bestResults == null || currentResults.compareTo(bestResults) > 0) {
			ResultsSerializer.serializeResults(currentResults);
			return true;
		}
		return false;
	}
}
