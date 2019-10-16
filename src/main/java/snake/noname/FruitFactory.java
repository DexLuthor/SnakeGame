package snake.noname;

import snake.entities.Apple;
import snake.entities.BigApple;
import snake.pane.GamePane;

public enum FruitFactory {
    INSTANCE;

    public Apple createAppleOnRandomPosition() {
        double appleX = Apple.RADIUS + Math.random() * (GamePane.WIDTH - Apple.RADIUS - Apple.RADIUS);
        double appleY = Apple.RADIUS + Math.random() * (GamePane.HEIGHT - Apple.RADIUS - Apple.RADIUS);
        return new Apple(appleX, appleY);
    }

    public BigApple createBigAppleOnRandomPostion() {
        double bigAppleX = BigApple.RADIUS + Math.random() * (GamePane.WIDTH - BigApple.RADIUS - BigApple.RADIUS);
        double bigAppleY = BigApple.RADIUS + Math.random() * (GamePane.HEIGHT - BigApple.RADIUS - BigApple.RADIUS);
        return new BigApple(bigAppleX, bigAppleY);
    }
}