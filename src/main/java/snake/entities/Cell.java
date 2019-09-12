package snake.entities;

import javafx.scene.shape.Rectangle;
import snake.interfaces.ICell;

public abstract class Cell extends Rectangle implements ICell {
    //     =============== CONSTANTS ===============
    protected static final int WIDTH = 20;
    protected static final int HEIGHT = 20;

    //     =============== FIELDS ===============
    protected double previousXCoordinate;
    protected double previousYCoordinate;
    public double getPreviousXCoordinate() {
        return previousXCoordinate;
    }
    public double getPreviousYCoordinate() {
        return previousYCoordinate;
    }

    protected void updatePreviousPosition(){ // FIXME(когда запускается во время шага или как)
        previousXCoordinate = getXCoordinate();
        previousYCoordinate = getYCoordinate();
    }

    //     =============== GET/SET ===============
    @Override public final void setXCoordinate(double x) {
        setX(x - WIDTH / 2);
    }
    @Override public final void setYCoordinate(double y) {
        setY(y - HEIGHT / 2);
    }
    @Override public final double getXCoordinate() {
        return getX() + WIDTH / 2;
    }
    @Override public final double getYCoordinate() {
        return getY() + HEIGHT / 2;
    }

    //     =============== CONSTRUCTORS ===============
    public Cell(){
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    //     =============== METHODS ===============
    public void setPosition(double x, double y) {
        setXCoordinate(x);
        setYCoordinate(y);
    }
}