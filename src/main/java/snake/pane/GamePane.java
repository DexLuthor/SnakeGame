package snake.pane;

import snake.interfaces.*;
import snake.engine.GameLogic;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Yevhenii Kozhevin
 *
 * Class is responsible for area, where game is running
 */
public class GamePane extends Application implements IGraphicInterface {
    // =============== CONSTANTS ===============
    /**
     * Width of the window
     */
    public static final int WIDTH = 488; // 488 because javaFX adds additional 12 pix
    /**
     * Height of the window
     */
    public static final int HEIGHT = 488;

    // =============== Fields ===============
    private Pane root;
    private Scene scene;

    private IGameLogic logic = new GameLogic(this);
    // =============== Methods ===============

    /**
     * The method initializes root element, key handler, game and sets size,
     */
    @Override
    public void init() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);
        scene = new Scene(root, Color.BLACK);

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

    @Override public void start(Stage stage) {
        stage.setTitle("snake");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The method stops the game
     */
    @Override
    public void stop() {
        logic.finishGame();
    }

    /**
     * The method place an object on the pane
     * @param node - element to place
     */
    @Override
    public void addObject(Node node) {
        Platform.runLater(() -> root.getChildren().add(node));
    }

    /**
     * The method remove an object from the pane
     * @param node - element to remove
     */
    @Override
    public void removeObject(Node node) {
        Platform.runLater(() -> root.getChildren().remove(node));
    }

    // #################################################
    public static void main(String[] args) {
        Application.launch(args);
    }
}