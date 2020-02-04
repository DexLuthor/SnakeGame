package snake.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {
	// =============== Constrants ===============
	/**
	 * The size of each sell's side
	 */
	public static final int SIZE = 20;

	// =============== Fields ===============
	/**
	 * Previous x coordinate
	 */
	protected double previousXCoordinate;
	/**
	 * Previous y coordinate
	 */
	protected double previousYCoordinate;

	// =============== Constructors ===============
	public Cell() {
		setWidth(SIZE);
		setHeight(SIZE);
		setFill(Color.WHITE);
	}

	// =============== Methods ===============
	/**
	 * Returns previous x coordinate
	 * 
	 * @return previous x coordinate
	 */
	public double getPreviousXCoordinate() {
		return previousXCoordinate;
	}

	/**
	 * Returns previous y coordinate
	 * 
	 * @return previous y coordinate
	 */
	public double getPreviousYCoordinate() {
		return previousYCoordinate;
	}

	public void updatePreviousPosition() {
		previousXCoordinate = getXCoordinate();
		previousYCoordinate = getYCoordinate();
	}

	/**
	 * Returns x coordinate center of cell
	 * 
	 * @return x coordinate center of cell
	 */
	public int getXCoordinate() {
		return (int) (getX() + SIZE / 2);
	}

	/**
	 * Sets x coordinate center of cell
	 * 
	 * @param x
	 */
	public void setXCoordinate(double x) {
		setX(x - SIZE / 2);
	}

	/**
	 * Returns y coordinate center of cell
	 * 
	 * @return y coordinate center of cell
	 */
	public int getYCoordinate() {
		return (int) (getY() + SIZE / 2);
	}

	/**
	 * Sets t coordinate center of cell
	 * 
	 * @param y
	 */
	public void setYCoordinate(double y) {
		setY(y - SIZE / 2);
	}

	/**
	 * Sets center of cell on (x,y) coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		setXCoordinate(x);
		setYCoordinate(y);
	}
}
