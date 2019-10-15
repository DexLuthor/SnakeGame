package snake.entities;

import javafx.scene.paint.Color;

/**
 * @author Yevhenii Kozhevin
 */
public final class BigApple extends Fruit {
    // =============== Constants ===============
    public static final int TIME_TO_REGENERATE = 8;
    public static final int TIME_TO_LIVE = 10;

    static {
        VALUE = 2;
        RADIUS = 20;
    }

    // =============== Constructors ===============
    public BigApple(double x, double y) {
        super(x, y);
        setFill(Color.rgb(17, 255, 0));
        setRadius(RADIUS);
    }
}