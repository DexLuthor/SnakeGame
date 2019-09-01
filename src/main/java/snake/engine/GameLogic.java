package snake.engine;

import snake.entities.*;
import snake.interfaces.*;
import snake.utils.Utils;
import javafx.animation.*;
import javafx.util.Duration;

public class GameLogic implements IGameLogic {
    // =============== Fields ===============
    private final IGraphicInterface gui;

    private boolean isGameRunning = true;

    private Snake snake = Snake.getInstance();
    private Direction direction = Direction.UP;

    private Apple apple;
    private BigApple bigApple;

    // =============== Enum ===============
    public enum Direction {UP, DOWN, LEFT, RIGHT}

    // =============== Constructors ===============
    public GameLogic(IGraphicInterface gui) {
        this.gui = gui;
    }

    // =============== Methods ===============
    @Override
    public void initGame() {
        createContent();
        startSnake();
        startGeneratingApples();
    }
    private void createContent() {
        gui.addObject(Snake.getInstance());
        plantApple();
    }
    private void startSnake() {
        new Thread(() -> {
            while (isGameRunning) {
                if (snake.isAlive()) {
                    switch (direction) {
                        case UP:
                            snake.moveUp();
                            break;
                        case LEFT:
                            snake.moveLeft();
                            break;
                        case DOWN:
                            snake.moveDown();
                            break;
                        case RIGHT:
                            snake.moveRight();
                            break;
                    }
                    checkSnakePosition();
                    Utils.sleep(100);
                }
            }
        }).start();
    }
    private void startGeneratingApples() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(BigApple.TIME_TO_LIVE), event -> {
            if (Math.random() > 0.8) {
                if (bigApple != null) {
                    removeFruit(bigApple);
                }
                bigApple = BigApple.generateRandomBigApple();
                gui.addObject(bigApple);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // =============== Methods ===============
    private void checkSnakePosition() {
        if (snake.isAlive()) {
            checkPositionWithinBorders();
            checkPositionRelativeToFruit();
        }
    }
    private void checkPositionWithinBorders() {
        if (snake.getXCoordinate() < 10 || snake.getXCoordinate() > 490 ||
                snake.getYCoordinate() < 10 || snake.getYCoordinate() > 490) {
            snake.setAlive(false);
            finishGame();
        }
    }
    private void checkPositionRelativeToFruit() {
        if (snake.distanceTo(apple) < 12) { // FIXME прописать нормальную дистанцию
            snake.eatFruit(apple);
            removeFruit(apple);
            plantApple();
            addPartToSnake(new PartOfBody());
        }
        if (bigApple != null && snake.distanceTo(bigApple) < 15) {
            snake.eatFruit(bigApple);
      //      addPart();  TODO

            removeFruit(bigApple);
        }
    }

    @Override
    public void changeDirection(Direction direction) {
        if (this.direction != getOppositeDirection(direction)) {
            this.direction = direction;
        }
    }
    private Direction getOppositeDirection(Direction direction){
       switch (direction){
           case UP:
               return Direction.DOWN;
           case LEFT:
               return Direction.RIGHT;
           case DOWN:
               return Direction.UP;
           case RIGHT:
               return Direction.LEFT;
       }
       return null;
    }

    private void plantApple() {
        apple = Apple.generateRandomApple();
        gui.addObject(apple);
    }

    private void removeFruit(Apple apple) {
        gui.removeObject(apple);
        this.apple = null;
    }
    private void removeFruit(BigApple bigApple) {
        gui.removeObject(bigApple);
        this.bigApple = null;
    }

    private void addPartToSnake(PartOfBody partOfBody) {
        SnakeManager.add(partOfBody);
        gui.addObject(partOfBody);
    }

    @Override
    public void finishGame() {
        isGameRunning = false;
    }
}
