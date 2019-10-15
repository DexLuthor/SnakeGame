package snake.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Tasks:
 * Пофикстить увеличение размера маленького яблока
 * Прописать норм дистанцию при кушании сблок
 * Прописать нормальные спавны яблокам
 */
//TODO разобрать класс и добро пожаловать в гейм лоджик
public final class SnakeManager {
    public static final List<Snake.PartOfSnake> PARTS;
    private static final Snake SNAKE;

    static {
        PARTS = new LinkedList<>();
        SNAKE = Snake.getInstance();
    }

    private SnakeManager() throws IllegalAccessException {
        throw new IllegalAccessException("Non-instantiating class");
    }

    public static final void add(Snake.PartOfSnake partOfSnake) {
        PARTS.add(partOfSnake);
    }

    public static void moveBody() {
        if (PARTS.size() != 0) {
            PARTS.get(0).follow(SNAKE);
            for (int i = PARTS.size() - 1; i > 0; i--) {
                PARTS.get(i).follow(PARTS.get(i - 1));
            }
        }
    }

    public static void updatePreviousPositions() {
        SNAKE.updatePreviousPosition();

        if (PARTS.size() != 0) {
            PARTS.forEach(Cell::updatePreviousPosition);
        }
    }

    public static int getScore() {
        return PARTS.size() * 100;
    }
}