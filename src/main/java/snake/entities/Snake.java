package snake.entities;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;
import snake.engine.GameLogic.Direction;

/**
 * Class representing a head of snake
 * 
 * @author Yevhenii Kozhevin
 *
 */
public final class Snake extends Cell {
	// =============== Fields ===============
	/**
	 * Count of eaten apples
	 */
	private int countOfEatenApples = 0;
	/**
	 * Count of eaten oranges
	 */
	private int countOfEatenOranges = 0;
	/**
	 * List of part of snake's body
	 */
	private final List<PartOfSnake> PARTS = new LinkedList<>();

	// =============== Singleton ===============
	private static Snake instance = null;

	/**
	 * Returns a singleton of a {@code Snake}
	 * 
	 * @return
	 */
	public static Snake getInstance() {
		if (instance == null) {
			synchronized (Snake.class) {
				if (instance == null) {
					instance = new Snake();
				}
			}
		}
		return instance;
	}

	// =============== Constructors ===============
	private Snake() {
	}

	// =============== Methods ===============
	public List<PartOfSnake> getPartsOfSnake() {
		return PARTS;
	}

	public int getLength() {
		return PARTS.size();
	}

	public int getCountOfEatenApples() {
		return countOfEatenApples;
	}

	public int getCountOfEatenOranges() {
		return countOfEatenOranges;
	}

	/**
	 * Increments count of eaten apples
	 */
	public void incrementAppleEatenCount() {
		countOfEatenApples = getCountOfEatenApples() + 1;
	}

	/**
	 * Increments count of eaten oranges
	 */
	public void incrementOrangesEatenCount() {
		countOfEatenOranges = getCountOfEatenOranges() + 1;
	}

	/**
	 * Returns distance to a fruit
	 * 
	 * @param fruit
	 * @return distance to a fruit
	 */
	public double distanceTo(Fruit fruit) {
		return Math.sqrt(Math.abs((fruit.getX() - getXCoordinate()) * (fruit.getX() - getXCoordinate())
				+ (fruit.getY() - getYCoordinate()) * (fruit.getY() - getYCoordinate())));
	}

	/**
	 * Moves snake's head up on 20 px
	 */
	public void moveUp() {
		setYCoordinate(getYCoordinate() - 20);
	}

	/**
	 * Moves snake's head left on 20 px
	 */
	public void moveLeft() {
		setXCoordinate(getXCoordinate() - 20);
	}

	/**
	 * Moves snake's head down on 20 px
	 */
	public void moveDown() {
		setYCoordinate(getYCoordinate() + 20);
	}

	/**
	 * Moves snake's head right on 20 px
	 */
	public void moveRight() {
		setXCoordinate(getXCoordinate() + 20);
	}

	/**
	 * Clear snake's data about eaten fruits
	 */
	public void dropData() {
		countOfEatenApples = 0;
		countOfEatenOranges = 0;
	}

	/**
	 * Moves snake's body
	 */
	public void moveBody() {
		if (PARTS.size() != 0) {
			PARTS.get(0).follow(this);
			for (int i = PARTS.size() - 1; i > 0; i--) {
				PARTS.get(i).follow(PARTS.get(i - 1));
			}
		}
	}

	/**
	 * updates previous positions of every part of snake's body
	 */
	public void updatePreviousPositions() {
		updatePreviousPosition();
		if (PARTS.size() != 0) {
			PARTS.forEach(Cell::updatePreviousPosition);
		}
	}

	/**
	 * Checks if snake's head have run into it's own body
	 * 
	 * @return true if snake's head have run into it's own body
	 */
	public boolean runIntoYourself() {
		if (PARTS.size() != 0) {
			for (PartOfSnake part : PARTS) {
				if (getXCoordinate() == part.getXCoordinate() && getYCoordinate() == part.getYCoordinate()) {
					part.setFill(Color.RED);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Moves head according to snake's head direction
	 */
	public void moveHead(Direction direction) {
		switch (direction) {
		case UP:
			moveUp();
			break;
		case LEFT:
			moveLeft();
			break;
		case DOWN:
			moveDown();
			break;
		case RIGHT:
			moveRight();
			break;
		}
	}

	/**
	 * Adds one or several parts to snake's body
	 * 
	 * @param count - how many parts are goint to be added
	 * @return list of those parts
	 */
	public List<PartOfSnake> addPart(int count) {
		List<PartOfSnake> list = new LinkedList<>();
		for (int i = 0; i < count; i++) {
			PartOfSnake partOfSnake = new PartOfSnake();
			list.add(partOfSnake);
			PARTS.addAll(list);
		}
		return list;
	}

	/**
	 * Class representing a part of snake's body
	 */
	public static final class PartOfSnake extends Cell {
		public PartOfSnake() {
			setPosition(-50, -50);
		}

		/**
		 * The method moves {@code this} to previous position of next {@code Cell}
		 * 
		 * @param cell object to follow
		 */
		public void follow(Cell cell) {
			setPosition(cell.getPreviousXCoordinate(), cell.getPreviousYCoordinate());
		}
	}
}