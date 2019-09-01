package snake.entities;

import java.util.LinkedList;
import java.util.List;

public final class SnakeManager {
    private static Snake snake;
    static List<Cell> parts;

    static {
        snake = Snake.getInstance();
        parts = new LinkedList<>();
        parts.add(snake);
    }

    public static void add(Cell cell){
        parts.add(cell);
    }

}