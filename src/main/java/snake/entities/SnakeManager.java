package snake.entities;

import java.util.LinkedList;
import java.util.List;

public final class SnakeManager {
    private static final List<Snake.PartOfSnake> PARTS;
    private static final Snake SNAKE;

    static {
        PARTS = new LinkedList<>();
        SNAKE = Snake.getInstance();
    }

    private SnakeManager() {
        throw new AssertionError("Non-instantiating class");
    }

    public static final void add(Snake.PartOfSnake partOfSnake) {
        PARTS.add(partOfSnake);
    }

    public static void moveBody() {
        if (PARTS.size() != 0) {
            for (int i = PARTS.size() - 1; i > 0; i--) {
                PARTS.get(i).follow(PARTS.get(i - 1));
            }
            PARTS.get(0).follow(SNAKE);
        }
    }

    public static void updatePreviousPositions() {
        if (PARTS.size() != 0) {
            SNAKE.updatePreviousPosition();
            for (int i = 0; i < PARTS.size(); i++) {
                PARTS.get(i).updatePreviousPosition();
            }
        }
    }

    public static int getPartsCount() {
        return PARTS.size();
    }
}