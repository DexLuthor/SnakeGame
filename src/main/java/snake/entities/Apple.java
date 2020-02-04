package snake.entities;

import javafx.scene.paint.Color;

/**
 * Сlass represents an apple
 * 
 * @author Yevhenii Kozhevin
 * 
 */
public final class Apple extends Fruit {
	// =============== Constructor ===============
	public Apple(double x, double y) {
		super(x, y, Color.rgb(17, 255, 0));
	}

	// =============== Methods ===============
	/**
	 * Returns value of an apple
	 * 
	 * @return value of an apple
	 */
	public static int getValue() {
		return 1;
	}
}