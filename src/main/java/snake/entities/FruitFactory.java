package snake.entities;

import snake.pane.GamePane;

public class FruitFactory {
    public enum Fruits {
        APPLE, BIG_APPLE
    }

    public static Fruit instanceOf(Fruits fruit) {
        switch (fruit) {
            case APPLE:
                double appleX = Apple.RADIUS + Math.random() * (GamePane.WIDTH - Apple.RADIUS - Apple.RADIUS);
                double appleY = Apple.RADIUS + Math.random() * (GamePane.HEIGHT - Apple.RADIUS - Apple.RADIUS);
                return new Apple(appleX, appleY);
            case BIG_APPLE:
                double bigAppleX = BigApple.RADIUS + Math.random() * (GamePane.WIDTH - BigApple.RADIUS - BigApple.RADIUS);
                double bigAppleY = BigApple.RADIUS + Math.random() * (GamePane.HEIGHT - BigApple.RADIUS - BigApple.RADIUS);
                return new BigApple(bigAppleX, bigAppleY);
            default:
                throw new IllegalArgumentException("Not a Fruit type");
        }
    }

}