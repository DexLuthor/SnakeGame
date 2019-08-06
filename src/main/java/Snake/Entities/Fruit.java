package Snake.Entities;

import Snake.Utils.IllegalPositionException;
import javafx.scene.shape.Circle;

public abstract class Fruit extends Circle {
    // =============== Fields ===============

    // =============== Constructors ===============
    public Fruit(double x, double y) {
        setPosition(x, y);
    }

    // =============== Get/Set ===============
    public double getX() {
        return getCenterX();
    }

    public double getY() {
        return getCenterY();
    }

    protected abstract void setX(double x) throws IllegalPositionException;

    protected abstract void setY(double y) throws IllegalPositionException;

    // =============== Methods ===============
    public void setPosition(double x, double y) {
        setCenterX(x);
        setCenterY(y);
    }

    protected abstract int getValue();
}