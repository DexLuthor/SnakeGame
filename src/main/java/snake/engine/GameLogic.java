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
    public enum Direction { UP, DOWN, LEFT, RIGHT;
        private Direction getOppositeDirection(){
            switch (this){
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
    }

    // =============== Constructors ===============
    public GameLogic(IGraphicInterface gui) {
        this.gui = gui;
    }

    // =============== Methods ===============
//    public void updateScoreLabel(){
//        gui.getScoreLabel().setText(String.valueOf(SnakeManager.getPartsCount()));
//    }

    @Override public void initGame() {
        createContent();
        startMoveSnake();
        startGeneratingBigApples();
    }
    private void createContent() {
        gui.addObject(Snake.getInstance());
        plantApple();
    }
    private void startMoveSnake() {
        new Thread(() -> {
            while (isGameRunning && snake.isAlive()) {
                switch (direction) {
                    case UP:
                        SnakeManager.updatePreviousPositions();
                        snake.moveUp();
                        SnakeManager.moveBody();
                        break;
                    case LEFT:
                        SnakeManager.updatePreviousPositions();
                        snake.moveLeft();
                        SnakeManager.moveBody();
                        break;
                    case DOWN:
                        SnakeManager.updatePreviousPositions();
                        snake.moveDown();
                        SnakeManager.moveBody();
                        break;
                    case RIGHT:
                        SnakeManager.updatePreviousPositions();
                        snake.moveRight();
                        SnakeManager.moveBody();
                        break;
                }

                checkSnakePosition();
                Utils.sleep(250);
            }
        }).start();
    }
    private void startGeneratingBigApples() {
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
    private void checkPositionRelativeToFruit() {//TODO refactor
        if (snake.distanceTo(apple) < 12) { // FIXME прописать нормальную дистанцию
            snake.eatFruit(apple);
            removeFruit(apple);
            plantApple();
            addPartToSnake();
        }
        if (bigApple != null && snake.distanceTo(bigApple) < 15) {
            snake.eatFruit(bigApple);
            removeFruit(bigApple);
            addTwoPartsToSnake();
        }
        //updateScoreLabel();
    }

    @Override public void changeDirection(Direction direction) {
        if (this.direction != direction.getOppositeDirection()) {
            this.direction = direction;
        }
    }

    private void plantApple() {
        apple = Apple.generateRandomApple();
        gui.addObject(apple);
    }

    private void removeFruit(Apple apple) {
        gui.removeObject(apple);
    }
    private void removeFruit(BigApple bigApple) {
        gui.removeObject(bigApple);
        this.bigApple = null;
    }

    private void addPartToSnake() {
        SnakeManager.add();
        gui.addObject(new PartOfSnake());
    }
    private void addTwoPartsToSnake(){
       addPartToSnake();
       addPartToSnake();
    }

    @Override public void finishGame() {
        isGameRunning = false;
    }
}