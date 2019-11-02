package snake.entities;

import snake.engine.GameLogic;
import snake.noname.Cell;

public final class Snake extends Cell {
	// =============== Fields ===============
	private static volatile Snake snakeInstance;
	private GameLogic.Direction currentDirection;

	// =============== Methods ===============
	/**
	 * Creates instance of Snake or returns if exists
	 */
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
		if (canMove()) {
			currentDirection = GameLogic.Direction.UP;
			setYCoordinate(getYCoordinate() - 20);
		}
	}

	public void moveLeft() {
		if (canMove()) {
			currentDirection = GameLogic.Direction.LEFT;
			setXCoordinate(getXCoordinate() - 20);
		}
	}

	public void moveDown() {
		if (canMove()) {
			currentDirection = GameLogic.Direction.DOWN;
			setYCoordinate(getYCoordinate() + 20);
		}
	}

	public void moveRight() {
		if (canMove()) {
			currentDirection = GameLogic.Direction.RIGHT;
			setXCoordinate(getXCoordinate() + 20);
		}
	}

	private boolean canMove() {
		return currentDirection == null || currentDirection != currentDirection.getOppositeDirection();
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