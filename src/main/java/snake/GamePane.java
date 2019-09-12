package snake;

import snake.interfaces.*;
import snake.engine.GameLogic;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Класс ответственные за игровое поле
 */
public class GamePane extends Application implements IGraphicInterface {
    // =============== CONSTANTS ===============
    public static final int WIDTH = 488; // 488 because javaFX adds additional 12 pix
    public static final int HEIGHT = 488;

    // =============== Fields ===============
    private Pane root;
    private Scene scene;

    //private Label scoreLabel;

    private IGameLogic logic = new GameLogic(this);
    // =============== Methods ===============
    @Override
    public void init() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        scene = new Scene(root, Color.BLACK);
    //    addScorePanel(); // TODO

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                case UP:
                    logic.changeDirection(GameLogic.Direction.UP);
                    break;
                case A:
                case LEFT:
                    logic.changeDirection(GameLogic.Direction.LEFT);
                    break;
                case S:
                case DOWN:
                    logic.changeDirection(GameLogic.Direction.DOWN);
                    break;
                case D:
                case RIGHT:
                    logic.changeDirection(GameLogic.Direction.RIGHT);
                    break;
            }
        });
        logic.initGame();
    }

//    private void addScorePanel(){
//        scoreLabel = new Label("0");
//    }

//    public Label getScoreLabel() {
//        return scoreLabel;
//    }

    @Override public void start(Stage stage) {
        stage.setTitle("snake");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        logic.finishGame();
    }

    @Override
    public void addObject(Node node) {
        Platform.runLater(() -> root.getChildren().add(node));
    }

    @Override
    public void removeObject(Node node) {
        Platform.runLater(() -> root.getChildren().remove(node));
    }

    // #################################################
    public static void main(String[] args) {
        Application.launch(args);
    }
}