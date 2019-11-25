package snake.entities;

import javafx.scene.paint.Color;

/**
 * @author Yevhenii Kozhevin
 * 
 */
public final class Apple extends Fruit {
    public static final int RADIUS;
    public static final Color COLOR;
    static {
        VALUE = 1;
        RADIUS = 10;
        COLOR = Color.rgb(17, 255, 0);
    }

    // =============== Constructors ===============
    public Apple(double x, double y) {
        super(x, y);
        setRadius(RADIUS);
        setFill(COLOR);
    }
}