package Snake.Interfaces;

import Snake.engine.GameLogic;

public interface IGameLogic {
    void initGame();

    void changeDirection(GameLogic.Direction direction);

    void finishGame();
}
