package snake.entities;

import snake.GamePane;
import snake.engine.GameLogic;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import snake.interfaces.ICell;

import java.util.LinkedList;

public final class Snake extends Cell {
    // =============== Fields ===============
    private boolean isAlive;
    private int snakeLength = 1;
    private GameLogic.Direction currentDirection;
    private static volatile Snake snakeInstance;
    private Color color;

    // =============== Constructors ===============
    private Snake() {
        super();
        isAlive = true;
        color = Color.WHITE;
        setFill(color);
        setXCoordinate((GamePane.WIDTH + 12) / 2);// устанавливаю центр змейки в центре площади
        setYCoordinate((GamePane.HEIGHT + 12) / 2);
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
    public int updateSnakeLength(Fruit fruit) {
        return snakeLength += fruit.getValue();
    }

    public double distanceTo(Fruit fruit) {
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

    @Override
    public String toString() {
        return "x: " + getXCoordinate() + " y: " + getYCoordinate();
    }
}