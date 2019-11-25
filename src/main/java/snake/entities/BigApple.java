package snake.entities;

import javafx.scene.paint.Color;

/**
 * @author Yevhenii Kozhevin
 */
public final class BigApple extends Fruit {
    // =============== Constants ===============
    public static final int TIME_TO_GENERATE = 8;
    public static final int TIME_TO_LIVE = 10;
    public static final int RADIUS;
    public static final Color COLOR;

    static {
        VALUE = 2;
        RADIUS = 20;
        COLOR = Color.rgb(255, 215, 0);
    }

    // =============== Constructors ===============
    public BigApple(double x, double y) {
        super(x, y);
        setRadius(RADIUS);
        setFill(COLOR);
    }
}