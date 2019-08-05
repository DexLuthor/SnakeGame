package Snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class, representing a pane where all actions are going
 */
public class GamePane extends Application {

    // =============== Fields ===============
    static final int WIDTH = 488; // 488 because javaFX adds additional 12 pix
    static final int HEIGHT = 488;

    private Pane root;
    private Snake snakeInstance = Snake.getInstance();

    private Apple apple;
    private BigApple bigApple;

    // =============== Get/Set ===============
    public double getSnakeX() {
        return snakeInstance.getXCoordinate();
    }
    public double getSnakeY() {
        return snakeInstance.getYCoordinate();
    }
    public Apple getApple() {
        return apple;
    }
    public BigApple getBigApple() {
        return bigApple;
    }
    // =============== Methods ===============

    public void checkSnakePosition() {
        if (snakeInstance.isAlive()) {
            checkPositionWithinBorders();
            checkPositionRelativeToFruit();
        }
    }
    private void checkPositionWithinBorders(){
        if (getSnakeX() < 10 || getSnakeX() > 490)
            snakeInstance.setAlive(false);
        if (getSnakeY() < 10 || getSnakeY() > 490)
            snakeInstance.setAlive(false);
    }
    private void checkPositionRelativeToFruit(){
        if (snakeInstance.distanceTo(getApple()) < 12) { // FIXME прописать нормальную дисстанцию
            snakeInstance.eatFruit(getBigApple());
            removeFruit(getApple());
            //addPart(); // TODO
            plantApple();
        }
        if (getBigApple() != null && snakeInstance.distanceTo(getBigApple()) < 15) {
            snakeInstance.eatFruit(getBigApple());
            //addPart(); // TODO
            removeFruit(getBigApple());
        }
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent(), Color.BLACK);

        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            if (snakeInstance.isAlive()){
                switch (event.getCode()) {
                    case W:
                    case UP:
                        checkSnakePosition();
                        snakeInstance.moveUp();
                        break;
                    case A:
                    case LEFT:
                        checkSnakePosition();
                        snakeInstance.moveLeft();
                        break;
                    case S:
                    case DOWN:
                        checkSnakePosition();
                        snakeInstance.moveDown();
                        break;
                    case D:
                    case RIGHT:
                        checkSnakePosition();
                        snakeInstance.moveRight();
                        break;
                }
            }
        });
        plantBigApple();
        stage.show();
    }
    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        root.getChildren().add(snakeInstance);

        apple = Apple.generateRandomApple();
        root.getChildren().add(apple);
        return root;
    }

    public void plantBigApple() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(BigApple.TIME_TO_LIVE), event -> {
            if (Math.random() > 0.8) {
                if (bigApple != null)
                    removeFruit(bigApple);
                bigApple = BigApple.generateRandomBigApple();
                root.getChildren().add(bigApple);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void plantApple() {
        apple = Apple.generateRandomApple();
        root.getChildren().add(apple);
        System.out.println(root.getChildren());
    }

    public void removeFruit(Apple apple) {
        root.getChildren().remove(apple);
        this.apple = null;
    }

    public void removeFruit(BigApple bigApple) {
        root.getChildren().remove(bigApple);
        this.bigApple = null;
    }

    private void addPart(){ // TODO

    }

    // #################################################
    public static void main(String[] args) {
        Application.launch(args);
    }
}