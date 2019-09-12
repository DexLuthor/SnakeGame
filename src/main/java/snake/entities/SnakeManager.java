package snake.entities;

import java.util.ArrayList;
import java.util.List;

public class SnakeManager {
    static final List<PartOfSnake> PARTS = new ArrayList<>(10);
    private static final Snake SNAKE = Snake.getInstance();
    private static int partsCounter = 0;

    private SnakeManager() {
        throw new AssertionError("Non-instantiating class");
    }

    public static final void add() {
        PartOfSnake partOfSnake = new PartOfSnake();
        PARTS.add(partOfSnake);
        partsCounter++;
    }

    public static int getPartsCount() {
        return partsCounter;
    }

    public static void moveBody() {
        if(PARTS.size() != 0) {
            for (int i = PARTS.size() - 1; i > 0; i--) {
                PARTS.get(i).follow(PARTS.get(i - 1));
            }
            PARTS.get(0).follow(SNAKE);
        }
    }
    public static void updatePreviousPositions(){
        for (int i = 0; i < PARTS.size(); i++) {
            PARTS.get(i).updatePreviousPosition();
        }
    }
}