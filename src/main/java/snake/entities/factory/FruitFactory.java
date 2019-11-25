package snake.entities.factory;

import snake.entities.Apple;
import snake.entities.BigApple;
import snake.entities.Snake;

public enum FruitFactory {
	INSTANCE;

	private static int[] oddNumbers = new int[25];
	private Snake snakeInstance = Snake.getInstance();
	
	static {
		for (int i = 0, j = 1; i < oddNumbers.length; i++, j += 2) {
			oddNumbers[i] = j;
		}
	}

	public Apple createAppleOnRandomPosition() {
		double appleX = oddNumbers[(int) (Math.random() * oddNumbers.length)];
		double appleY = oddNumbers[(int) (Math.random() * oddNumbers.length)];
		return new Apple(appleX * 10, appleY * 10);
	}

	public BigApple createBigAppleOnRandomPostion() {
		double bigAppleX = oddNumbers[(int) (Math.random() * oddNumbers.length)];
		double bigAppleY = oddNumbers[(int) (Math.random() * oddNumbers.length)];
		return new BigApple(bigAppleX * 20, bigAppleY * 20);
	}
}