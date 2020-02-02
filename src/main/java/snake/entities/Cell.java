package snake.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {
    //     =============== CONSTANTS ===============
    public static final int SIZE = 20;
  
    //     =============== FIELDS ===============
    protected double previousXCoordinate;
    protected double previousYCoordinate;

    //     =============== CONSTRUCTORS ===============
    public Cell() {
        setWidth(SIZE);
        setHeight(SIZE);
        setFill(Color.WHITE);
    }

    public double getPreviousXCoordinate() {
        return previousXCoordinate;
    }

    public double getPreviousYCoordinate() {
        return previousYCoordinate;
    }

    public void updatePreviousPosition() {
        previousXCoordinate = getXCoordinate();
        previousYCoordinate = getYCoordinate();
    }

    //     =============== GET/SET ===============
    public final int getXCoordinate() {
        return (int)(getX() + SIZE / 2);
    }

    public final void setXCoordinate(double x) {
        setX(x - SIZE / 2);
    }

    public final int getYCoordinate() {
        return (int)(getY() + SIZE / 2);
    }

    public final void setYCoordinate(double y) {
        setY(y - SIZE / 2);
    }

    //     =============== METHODS ===============
    public void setPosition(double x, double y) {
        setXCoordinate(x);
        setYCoordinate(y);
    }
}
