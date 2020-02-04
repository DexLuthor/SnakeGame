package snake.entities.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import snake.entities.Apple;
import snake.entities.Orange;
import snake.entities.Snake;

/**
 * 
 * Factory creates fruit on random, allowed positions
 * 
 * @author Yevhenii Kozhevin
 *
 */
public class FruitFactory {
	/**
	 * List of values representing coordinates
	 */
	private static List<Integer> coordinates = new ArrayList<Integer>(26);
	/**
	 * Singleton object of {@code Snake} class
	 */
	private static Snake snake = Snake.getInstance();

	static {
		// filling list coordinates with values
		for (int i = 0, j = 1; i < 25; i++, j += 2) {
			coordinates.add(j * 10);
		}
	}

	/**
	 * Returns an {@code Apple} object on random position
	 * 
	 * @return an {@code Apple} object on random position
	 */
	public static Apple createAppleOnRandomPosition() {
		return new Apple(generateRandomCoordinate(Axis.X), generateRandomCoordinate(Axis.Y));
	}

	/**
	 * Returns an {@code Orange} object on random position
	 * 
	 * @return an {@code Orange} object on random position
	 */
	public static Orange createOrangeOnRandomPosition() {
		return new Orange(generateRandomCoordinate(Axis.X), generateRandomCoordinate(Axis.Y));
	}

	/**
	 * Generates random coordinate from a list of allowed coordinates
	 * 
	 * @param axis
	 * @return random coordinate from a list of allowed coordinates
	 */
	private static int generateRandomCoordinate(Axis axis) {
		List<Integer> allowedPositions = allowedPositions(axis);
		return allowedPositions.get((int) (Math.random() * allowedPositions.size()));
	}

	/**
	 * Returns list of allowed coordinates
	 * 
	 * @param axis
	 * @return list of allowed coordinates
	 */
	private static List<Integer> allowedPositions(Axis axis) {
		Set<Integer> badPositions = badPositions(axis);
		ArrayList<Integer> allowedPositions = new ArrayList<>(coordinates);
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

	/**
	 * Returns a list of forbidden coordinates
	 * 
	 * @param axis
	 * @return list of forbidden coordinates
	 */
	private static Set<Integer> badPositions(Axis axis) {
		Set<Integer> badPositions = new HashSet<>(26);
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
		return badPositions;
	}

	private enum Axis {
		X, Y
	}
}