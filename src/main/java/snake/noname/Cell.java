package snake.noname;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {
    //     =============== CONSTANTS ===============
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    //     =============== FIELDS ===============
    protected double previousXCoordinate;
    protected double previousYCoordinate;

    //     =============== CONSTRUCTORS ===============
    public Cell() {
        setWidth(WIDTH);
        setHeight(HEIGHT);
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
    public final double getXCoordinate() {
        return getX() + WIDTH / 2;
    }

    public final void setXCoordinate(double x) {
        setX(x - WIDTH / 2);
    }

    public final double getYCoordinate() {
        return getY() + HEIGHT / 2;
    }

    public final void setYCoordinate(double y) {
        setY(y - HEIGHT / 2);
    }

    //     =============== METHODS ===============
    public void setPosition(double x, double y) {
        setXCoordinate(x);
        setYCoordinate(y);
    }
}