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

public class GamePane extends Application {

    // =============== Fields ===============
    static final int WIDTH = 488; // 488 because javaFX adds additional 12 pix
    static final int HEIGHT = 488;
    private static volatile GamePane gamePane;

    private Pane root;
    private Snake snakeInstance = Snake.getInstance();

    private Apple apple;
    private BigApple bigApple;

    private IGameLogic controller = new Controller();

    // =============== Constructors ===============
    private GamePane(){

    }

    // =============== Get/Set ===============
    public Apple getApple() {
        return apple;
    }
    public BigApple getBigApple() {
        return bigApple;
    }
    public static GamePane getInstance(){
        if(gamePane == null)
            synchronized (GamePane.class) {
                if (gamePane == null)
                    gamePane = new GamePane();
            }
        return gamePane;
    }
    // =============== Methods ===============
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent(), Color.BLACK);

        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.setScene(scene);
        //scene.setOnMouseClicked(event -> System.out.println("x: " + event.getX() + " y: " + event.getY()));
//
        scene.setOnKeyPressed(event -> {
            if (snakeInstance.isAlive()){
                switch (event.getCode()) {
                    case W:
                    case UP:
                        controller.move(Direction.UP);
                        break;
                    case A:
                    case LEFT:
                        controller.move(Direction.LEFT);
                        break;
                    case S:
                    case DOWN:
                        controller.move(Direction.DOWN);
                        break;
                    case D:
                    case RIGHT:
                        controller.move(Direction.RIGHT);
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
        root.getChildren().add(snakeInstance.new PartOfBody());
    }

    // #################################################
    public static void main(String[] args) {
        Application.launch(args);
    }
}