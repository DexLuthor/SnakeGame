package Snake.Interfaces;

import Snake.engine.GameLogic;

public interface IGameLogic {
    void initGame();

    void refocusDirection(GameLogic.Direction direction);

    void finishGame();
}
