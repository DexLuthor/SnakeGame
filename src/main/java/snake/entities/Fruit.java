package snake.entities;

import javafx.scene.shape.Circle;

/**
 * @author Yevhenii Kozhevin
 */
public abstract class Fruit extends Circle {
    // =============== Fields ===============
    protected static int VALUE;

    // =============== Constructors ===============
    Fruit(double x, double y) {
        setPosition(x, y);
    }

    // =============== Get/Set ===============
    double getX() {
        return getCenterX();
    }

    public static int getValue() {
        return VALUE;
    }

    double getY() {
        return getCenterY();
    }

    // =============== Methods ===============
    void setPosition(double x, double y) {
        setCenterX(x);
        setCenterY(y);
    }
}