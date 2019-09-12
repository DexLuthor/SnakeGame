package snake.entities;

import snake.utils.IllegalPositionException;
import javafx.scene.paint.Color;
import snake.GamePane;

public final class Apple extends Fruit {
    //     =============== CONSTANTS ===============
    private final static int VALUE;
    private final static int RADIUS;

    static {
        VALUE = 1;
        RADIUS = 10;
    }

    // =============== Get/Set ===============
    @Override
    public void setX(double x) throws IllegalPositionException {//FIXME
        if (x < 5 || x > GamePane.WIDTH - 5) {
            throw new IllegalPositionException("Apple is on wrong position");
        }
        setCenterX(x);
    }

    @Override
    public void setY(double y) throws IllegalPositionException {//FIXME
        if (y < 5 || y > GamePane.WIDTH - 5) {
            throw new IllegalPositionException("Apple is on wrong position");
        }
        setCenterY(y);
    }

    // =============== Constructors ===============
    private Apple(double x, double y) {
        super(x, y);
        setFill(Color.rgb(255, 170, 0));
        setRadius(RADIUS);
    }

    // =============== Methods ===============
    @Override
    public int getValue() {
        return VALUE;
    }

    //FIXME может спавниться за границей
    public static Apple generateRandomApple() {
        return new Apple(10 + Math.random() * 490, 10 + Math.random() * 490);
    }
}