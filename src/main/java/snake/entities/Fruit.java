package snake.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Abstract class for {@code Apple} and {@code Orange}
 * 
 * @author Yevhenii Kozhevin
 */
public abstract class Fruit extends Circle {
	// =============== Contants ===============
	/**
	 * Radius of fruits
	 */
	static final int RADIUS = 10;

	// =============== Constructors ===============
	public Fruit(double x, double y, Color color) {
		setRadius(RADIUS);
		setPosition(x, y);
		setFill(color);
	}

	// =============== Get/Set ===============
	/**
	 * Returns central x coordinate of a fruit
	 * 
	 * @return central x coordinate of a fruit
	 */
	public double getX() {
		return getCenterX();
	}

	/**
	 * Returns central y coordinate of a fruit
	 * 
	 * @return central y coordinate of a fruit
	 */
	public double getY() {
		return getCenterY();
	}

	// =============== Methods ===============
	/**
	 * Set position of center of a fruit on (x,y)
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		setCenterX(x);
		setCenterY(y);
	}
}