package snake.interfaces;

import snake.engine.GameLogic;

public interface IGameLogic {
    void initGame();
    void changeDirection(GameLogic.Direction direction);
    void finishGame();
}
