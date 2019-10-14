package snake.entities;

import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import snake.pane.GamePane;
import snake.engine.GameLogic;


public final class Snake extends Cell {

    public static final class PartOfSnake extends Cell{
        public PartOfSnake() {

            setPosition(-50, -50);
        }
        public void follow(@NotNull Cell cell){
            setPosition(cell.getPreviousXCoordinate(), cell.getPreviousYCoordinate());
        }
    }

    // =============== Fields ===============
    private boolean isAlive;
    private int snakeLength = 1;
    private GameLogic.Direction currentDirection;
    private static volatile Snake snakeInstance;

    // =============== Constructors ===============
    private Snake() {
        super();
        isAlive = true;
        setXCoordinate((GamePane.WIDTH + 12) / 2);// устанавливаю центр змейки в центре площади
        setYCoordinate((GamePane.HEIGHT + 12) / 2);//TODO адаптивность
    }

    // =============== Get/Set ===============
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    // =============== Methods ===============
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

    public void eatFruit(Fruit fruit) {
        updateSnakeLength(fruit);
    }
    public int updateSnakeLength(@NotNull Fruit fruit) {
        return snakeLength += fruit.getValue();
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

    @NotNull
    @Override
    public String toString() {
        return "x: " + getXCoordinate() + " y: " + getYCoordinate();
    }
}