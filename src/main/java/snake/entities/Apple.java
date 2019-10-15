package snake.entities;

import javafx.scene.paint.Color;

/**
 * @author Yevhenii Kozhevin
 */
public final class Apple extends Fruit {
    static {
        VALUE = 1;
        RADIUS = 10;
    }

    // =============== Constructors ===============
    public Apple(double x, double y) {
        super(x, y);
        setFill(Color.rgb(255, 170, 0));
        setRadius(RADIUS);
    }
}