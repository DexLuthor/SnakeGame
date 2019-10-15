package snake.entities;

import javafx.scene.shape.Circle;

/**
 * @author Yevhenii Kozhevin
 */
public abstract class Fruit extends Circle {
    // =============== Fields ===============
    protected static int VALUE;
    protected static int RADIUS;

    // =============== Constructors ===============
    public Fruit(double x, double y) {
        setPosition(x, y);
    }

    // =============== Get/Set ===============
    public double getX() {
        return getCenterX();
    }

    public static int getValue() {
        return VALUE;
    }

    public double getY() {
        return getCenterY();
    }

    // =============== Methods ===============
    public void setPosition(double x, double y) {
        setCenterX(x);
        setCenterY(y);
    }
}