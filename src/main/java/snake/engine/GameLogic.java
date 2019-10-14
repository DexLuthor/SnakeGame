package snake.engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import snake.entities.Apple;
import snake.entities.BigApple;
import snake.entities.Snake;
import snake.entities.SnakeManager;
import snake.interfaces.IGameLogic;
import snake.interfaces.IGraphicInterface;
import snake.utils.Utils;

public class GameLogic implements IGameLogic {
    // =============== Fields ===============
    private final IGraphicInterface gui;

    private boolean isGameRunning = true;

    private Snake snake = Snake.getInstance();
    private Direction direction = Direction.UP;

    private Apple apple;
    private BigApple bigApple;

    // =============== Constructors ===============
    public GameLogic(IGraphicInterface gui) {
        this.gui = gui;
    }

    // =============== Methods ===============
    @Override
    public void initGame() {
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
                SnakeManager.updatePreviousPositions();
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
                SnakeManager.moveBody();
                checkSnakePosition();
                Utils.sleep(100);
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
            checkPositionRelativeToBody();
        }
    }

    private void checkPositionWithinBorders() { //TODO adaptive
        if (snake.getXCoordinate() < 10 || snake.getXCoordinate() > 490 ||
                snake.getYCoordinate() < 10 || snake.getYCoordinate() > 490) {//TODO REFACTOR лучше сделать булеоновским методом а потом на него реагировать финишом чтобы метод выполнял только одно действие(тут 2)
            finishGame();
        }
    }

    private void checkPositionRelativeToFruit() {//TODO refactor, adaptive
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
    }

    private void checkPositionRelativeToBody() {
        if (SnakeManager.PARTS.size() != 0) {
            SnakeManager.PARTS.forEach(part -> {
                if (snake.getXCoordinate() == part.getXCoordinate() && snake.getYCoordinate() == part.getYCoordinate()) {
                    part.setFill(Color.RED);
                    finishGame();
                }
            });
        }
    }

    @Override
    public void changeDirection(@NotNull Direction direction) {
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
        Snake.PartOfSnake partOfSnake = new Snake.PartOfSnake();
        SnakeManager.add(partOfSnake);
        gui.addObject(partOfSnake);
        gui.updateLabelScore();
    }

    private void addTwoPartsToSnake() {
        addPartToSnake();
        addPartToSnake();
    }

    @Override
    public void finishGame() {
        snake.setAlive(false);
        isGameRunning = false;
    }

    // =============== Enum ===============
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        private Direction getOppositeDirection() {
            switch (this) {
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
}