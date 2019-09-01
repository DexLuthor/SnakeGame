package snake.entities;

import java.util.LinkedList;
import java.util.List;

public final class SnakeManager {
    private Snake snake;
    static List<Cell> parts;

    public SnakeManager(){
        snake = Snake.getInstance();
        parts = new LinkedList<>();
        parts.add(snake);
    }
    public static void add(Cell cell){
        parts.add(cell);
        System.out.println(parts.toString());
    }

}