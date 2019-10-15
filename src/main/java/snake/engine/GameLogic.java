package snake.engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import snake.entities.*;
import snake.interfaces.IGameLogic;
import snake.interfaces.IGraphicInterface;
import snake.utils.Utils;

/**
 * @author Yevhenii Kozhevin
 */
public class GameLogic implements IGameLogic {
    // =============== Constants ===============
    private final IGraphicInterface gui;
    private final Snake snake = Snake.getInstance();

    // =============== Fields ===============
    private boolean isGameRunning = true;

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
        addContent();
        startMovingSnake();
        startGeneratingBigApples();
    }

    private void addContent() {
        gui.addObject(Snake.getInstance());
        plantApple();
    }

    private void startMovingSnake() {
        new Thread(() -> {
            while (isGameRunning) {
                SnakeManager.updatePreviousPositions();
                moveHead();
                SnakeManager.moveBody();
                checkSnakePosition();
                Utils.sleep(100);
            }
        }).start();
    }

    private void moveHead() {
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
    }

    private void checkSnakePosition() {
        if (isOutOfBorders() || runIntoYourself()) {
            finishGame();
        }
        checkPositionRelativeToFruit();
    }

    private boolean isOutOfBorders() {
        return snake.getXCoordinate() < (snake.getWidth() / 2) - 1 || snake.getXCoordinate() > 490 || //TODO adaprive
                snake.getYCoordinate() < (snake.getHeight() / 2) - 1 || snake.getYCoordinate() > 490;
    }

    private boolean runIntoYourself() {
        if (SnakeManager.PARTS.size() != 0) {
            for (Snake.PartOfSnake part : SnakeManager.PARTS) {
                if (snake.getXCoordinate() == part.getXCoordinate() && snake.getYCoordinate() == part.getYCoordinate()) {
                    part.setFill(Color.RED);
                    return true;
                }
            }
        }
        return false;
    }

    private void checkPositionRelativeToFruit() {//TODO refactor, adaptive
        if (snake.distanceTo(apple) < 12) { // FIXME прописать нормальную дистанцию
            gui.removeObject(apple);
            plantApple();
            addPartToSnake(Apple.getValue());
        }
        if (bigApple != null && snake.distanceTo(bigApple) < 15) {
            gui.removeObject(bigApple);
            addPartToSnake(BigApple.getValue());
        }
    }

    private void startGeneratingBigApples() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(BigApple.TIME_TO_REGENERATE), event -> {
            if (Math.random() > 0.3) {
                bigApple = (BigApple) FruitFactory.instanceOf(FruitFactory.Fruits.BIG_APPLE);
                gui.addObject(bigApple);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void changeDirection(@NotNull Direction direction) {
        if (this.direction != direction.getOppositeDirection()) {
            this.direction = direction;
        }
    }

    private void plantApple() {
        apple = (Apple) FruitFactory.instanceOf(FruitFactory.Fruits.APPLE);
        gui.addObject(apple);
    }

    private void addPartToSnake(int count) {
        for (int i = 0; i < count; i++) {
            Snake.PartOfSnake partOfSnake = new Snake.PartOfSnake();
            SnakeManager.add(partOfSnake);
            gui.addObject(partOfSnake);
            gui.updateLabelScore();
        }
    }

    @Override
    public void finishGame() {
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