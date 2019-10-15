package snake.entities;

import org.jetbrains.annotations.NotNull;
import snake.engine.GameLogic;
import snake.pane.GamePane;

/**
 * @author Yevhenii Kozhevin
 */
public final class Snake extends Cell {
    // =============== Fields ===============
    private static volatile Snake snakeInstance;
    private GameLogic.Direction currentDirection;

    // =============== Constructors ===============
    private Snake() {
        // sets center of head in the center of the pane
        setXCoordinate((GamePane.WIDTH + (getWidth() / 2)) / 2);
        setYCoordinate((GamePane.HEIGHT + (getWidth() / 2)) / 2);
    }

    // =============== Methods ===============

    /**
     * Creates instance of Snake or return if exists
     */
    public static Snake getInstance() {
        if (snakeInstance == null) {
            synchronized (Snake.class) {
                if (snakeInstance == null) {
                    snakeInstance = new Snake();
                }
            }
        }
        return snakeInstance;
    }

    public double distanceTo(@NotNull Fruit fruit) {
        return Math.sqrt(Math.abs((fruit.getX() - getXCoordinate()) * (fruit.getX() - getXCoordinate())
                + (fruit.getY() - getYCoordinate()) * (fruit.getY() - getYCoordinate())));
    }

    public void moveUp() {
        if (currentDirection == null || currentDirection != GameLogic.Direction.DOWN) {
            currentDirection = GameLogic.Direction.UP;
            setYCoordinate(getYCoordinate() - 20);
        }
    }

    public void moveLeft() {
        if (currentDirection == null || currentDirection != GameLogic.Direction.RIGHT) {
            currentDirection = GameLogic.Direction.LEFT;
            setXCoordinate(getXCoordinate() - 20);
        }
    }

    public void moveDown() {
        if (currentDirection == null || currentDirection != GameLogic.Direction.UP) {
            currentDirection = GameLogic.Direction.DOWN;
            setYCoordinate(getYCoordinate() + 20);
        }
    }

    public void moveRight() {
        if (currentDirection == null || currentDirection != GameLogic.Direction.LEFT) {
            currentDirection = GameLogic.Direction.RIGHT;
            setXCoordinate(getXCoordinate() + 20);
        }
    }

    public static final class PartOfSnake extends Cell {
        public PartOfSnake() {
            setPosition(-50, -50);
        }

        public void follow(@NotNull Cell cell) {
            setPosition(cell.getPreviousXCoordinate(), cell.getPreviousYCoordinate());
        }
    }
}