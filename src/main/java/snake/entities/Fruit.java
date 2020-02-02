package snake.entities;

import javafx.scene.shape.Circle;

/**
 * @author Yevhenii Kozhevin
 */
public abstract class Fruit extends Circle {
    // =============== Fields ===============
    private static final int RADIUS = 10;
    
    // =============== Constructors ===============
    Fruit(double x, double y) {
    	setRadius(RADIUS);
        setPosition(x, y);
    }

    // =============== Get/Set ===============
    double getX() {
        return getCenterX();
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