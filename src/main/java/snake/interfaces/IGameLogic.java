package snake.interfaces;

import snake.engine.GameLogic;

/**
 * 
 * @author Yevhenii Kozhevin
 *
 */
public interface IGameLogic {
	/**
	 * What to do when game is started
	 */
	void initGame();

	/**
	 * Changes snake direction
	 */
	void changeDirection(GameLogic.Direction direction);

	/**
	 * What to do when game is finished
	 */
	void finishGame();
}
