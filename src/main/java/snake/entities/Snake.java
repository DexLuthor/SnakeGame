package snake.entities;

public final class Snake extends Cell {
	// =============== Fields ===============
	private static volatile Snake snakeInstance;
	
	// =============== Methods ===============
	public static Snake getInstance() {
		if (snakeInstance == null) {
			synchronized (Snake.class) {
				if (snakeInstance == null) {
					snakeInstance = new Snake();
				}
			}
		}
		return snakeInstance;
	}

	public double distanceTo(Fruit fruit) {
		return Math.sqrt(Math.abs((fruit.getX() - getXCoordinate()) * (fruit.getX() - getXCoordinate())
				+ (fruit.getY() - getYCoordinate()) * (fruit.getY() - getYCoordinate())));
	}

	public void moveUp() {
		setYCoordinate(getYCoordinate() - 20);
	}

	public void moveLeft() {
		setXCoordinate(getXCoordinate() - 20);
	}

	public void moveDown() {
		setYCoordinate(getYCoordinate() + 20);
	}

	public void moveRight() {
		setXCoordinate(getXCoordinate() + 20);
	}

	public static final class PartOfSnake extends Cell {
		public PartOfSnake() {
			setPosition(-50, -50);
		}

		public void follow(Cell cell) {
			setPosition(cell.getPreviousXCoordinate(), cell.getPreviousYCoordinate());
		}
	}
}