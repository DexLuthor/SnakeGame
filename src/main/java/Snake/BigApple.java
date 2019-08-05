package Snake;

import javafx.scene.paint.Color;

public class BigApple extends Fruit {
    // =============== Fields ===============
    private final int VALUE = 3;
    private final int RADIUS = 20;
    static final int TIME_TO_LIVE = 8;

    // =============== Constructors ===============
    public BigApple(double x, double y){
        super(x, y);
        setFill(Color.rgb(17, 255, 0));
        setRadius(RADIUS);
    }

    // =============== Get/Set ===============
    @Override
    public void setX(double x) throws IllegalPositionException {
        if (x < 10 || x > GamePane.WIDTH - 10)
            throw new IllegalPositionException("Big apple is on wrong position");
        setCenterX(x);
    }

    @Override
    public void setY(double y) throws IllegalPositionException {
        if (y < 10 || y > GamePane.WIDTH - 10)
            throw new IllegalPositionException("Big apple is on wrong position");
        setCenterX(y);
    }

    // =============== Methods ===============
    @Override
    public int getValue() {
        return VALUE;
    }

    public static BigApple generateRandomBigApple() {// FIXME может спавниться за границами
        return new BigApple(20 + Math.random() * 480 ,20 + Math.random() * 480);
    }
}