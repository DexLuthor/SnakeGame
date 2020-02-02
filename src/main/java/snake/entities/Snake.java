package snake.entities;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;

public final class Snake extends Cell {
	// =============== Fields ===============
	private int countOfEatenApples = 0;
	private int countOfEatenOranges = 0;

	private final List<PartOfSnake> PARTS = new LinkedList<>();

	// =============== Singleton ===============
	private static class SingletonHolder {
		private static Snake instance = null;
	}

	public static Snake getInstance() {
		return SingletonHolder.instance != null ? SingletonHolder.instance : (SingletonHolder.instance = new Snake());
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

	public void incrementAppleEatenCount() {
		countOfEatenApples = getCountOfEatenApples() + 1;
	}

	public void incrementOrangesEatenCount() {
		countOfEatenOranges = getCountOfEatenOranges() + 1;
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

	public void dropData() {
		countOfEatenApples = 0;
		countOfEatenOranges = 0;
	}

	public void moveBody() {
		if (PARTS.size() != 0) {
			PARTS.get(0).follow(this);
			for (int i = PARTS.size() - 1; i > 0; i--) {
				PARTS.get(i).follow(PARTS.get(i - 1));
			}
		}
	}

	public void updatePreviousPositions() {
		updatePreviousPosition();
		if (PARTS.size() != 0) {
			PARTS.forEach(Cell::updatePreviousPosition);
		}
	}

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

	public static final class PartOfSnake extends Cell {
		public PartOfSnake() {
			setPosition(-50, -50);
		}

		public void follow(Cell cell) {
			setPosition(cell.getPreviousXCoordinate(), cell.getPreviousYCoordinate());
		}
	}
}