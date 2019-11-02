package snake.noname;

import snake.entities.Apple;
import snake.entities.BigApple;
import snake.pane.SnakeGame;

public enum FruitFactory {
	INSTANCE;

	public Apple createAppleOnRandomPosition() {
		double appleX = Apple.RADIUS + Math.random() * (SnakeGame.SIZE - Apple.RADIUS - Apple.RADIUS);
		double appleY = Apple.RADIUS + Math.random() * (SnakeGame.SIZE - Apple.RADIUS - Apple.RADIUS);
		return new Apple(appleX, appleY);
	}

	public BigApple createBigAppleOnRandomPostion() {
		double bigAppleX = BigApple.RADIUS + Math.random() * (SnakeGame.SIZE - BigApple.RADIUS - BigApple.RADIUS);
		double bigAppleY = BigApple.RADIUS + Math.random() * (SnakeGame.SIZE - BigApple.RADIUS - BigApple.RADIUS);
		return new BigApple(bigAppleX, bigAppleY);
	}
}