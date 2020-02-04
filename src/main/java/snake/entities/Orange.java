package snake.entities;

import javafx.scene.paint.Color;

/**
 * Сlass represents an orange
 * 
 * @author Yevhenii Kozhevin
 */
public final class Orange extends Fruit {
	// =============== Constants ===============
	/**
	 * How much time between generating cycles
	 */
	public static final int TIME_TO_GENERATE = 5;

	// =============== Constructors ===============
	public Orange(double x, double y) {
		super(x, y, Color.rgb(255, 215, 0));
	}

	/**
	 * Returns value of an apple
	 * 
	 * @return value of an apple
	 */
	public static int getValue() {
		return 2;
	}
}