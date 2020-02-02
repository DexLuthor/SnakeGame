package snake.results;

public class Results implements Comparable<Results> {

	private int timeMinutes;
	private int length;
	private int countOfEatenApples;
	private int countOfEatenOranges;

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

}
