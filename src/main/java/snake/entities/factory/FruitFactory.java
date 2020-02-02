package snake.entities.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import snake.entities.Apple;
import snake.entities.Orange;
import snake.entities.Snake;

public enum FruitFactory {
	INSTANCE;

	private static Integer[] coordinates = new Integer[25];
	private Snake snake = Snake.getInstance();

	static {
		for (int i = 0, j = 1; i < coordinates.length; i++, j += 2) {
			coordinates[i] = j * 10;
		}
	}

	public Apple createAppleOnRandomPosition() {
		return new Apple(generateRandomCoordinate(Axis.X), generateRandomCoordinate(Axis.Y));
	}

	public Orange createOrangeOnRandomPosition() {
		return new Orange(generateRandomCoordinate(Axis.X), generateRandomCoordinate(Axis.Y));
	}

	private int generateRandomCoordinate(Axis axis) {
		List<Integer> allowedPositions = allowedPositions(axis);
		return allowedPositions.get((int) (Math.random() * allowedPositions.size()));
	}

	private List<Integer> allowedPositions(Axis axis) {
		List<Integer> badPositions = badPositions(axis);
		ArrayList<Integer> allowedPositions = new ArrayList<>(Arrays.asList(coordinates));
		for (Integer d : coordinates) {
			for (Integer bad : badPositions) {
				if (d.equals(bad)) {
					allowedPositions.remove(d);
					break;
				}
			}
		}
		return allowedPositions;
	}

	private List<Integer> badPositions(Axis axis) {
		ArrayList<Integer> badPositions = new ArrayList<>(26);
		int snakeCoord; 
		if (axis == Axis.X) {
			snakeCoord = snake.getXCoordinate();
			for (Snake.PartOfSnake part : snake.getPartsOfSnake()) {
				badPositions.add(part.getXCoordinate());
			}
		} else {
			snakeCoord = snake.getYCoordinate();
			for (Snake.PartOfSnake part : snake.getPartsOfSnake()) {
				badPositions.add(part.getYCoordinate());
			}
		}
		badPositions.add(snakeCoord);
		System.out.println("badXPositions: " + badPositions);
		return badPositions;
	}

	private enum Axis {
		X, Y
	}
}