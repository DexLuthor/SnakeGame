package snake.entities;

import javafx.scene.shape.Rectangle;
import snake.interfaces.ICell;

public abstract class Cell extends Rectangle implements ICell {
    //     =============== CONSTANTS ===============
    protected static final int WIDTH = 20;
    protected static final int HEIGHT = 20;

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
    protected Cell(){
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    //     =============== METHODS ===============
    public void setPosition(double x, double y) {
        setXCoordinate(x);
        setYCoordinate(y);
    }
}