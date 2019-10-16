package snake.pane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import snake.engine.GameLogic;
import snake.engine.SnakeManager;
import snake.interfaces.IGameLogic;
import snake.interfaces.IGraphicInterface;

/**
 * @author Yevhenii Kozhevin
 *
 * Class is responsible for area, where game is running
 */
public class GamePane extends Application implements IGraphicInterface {

    public static void main(String[] args) {
        Application.launch(args);
    }

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
    private Label labelScore;

    private IGameLogic logic = new GameLogic(this);

    // =============== Methods ===============
    /**
     * The method initializes root element, key handler, game and sets size,
     */
    @Override
    public void init() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        labelScore = createAndAdjustLabel();

        scene = new Scene(new Group(root, labelScore), Color.BLACK);
        initKeyListener(scene);

        logic.initGame();
    }

    /**
     * Creates label, which represents gamer's score
     */
    private Label createAndAdjustLabel() {
        Label label = new Label("Score: " + SnakeManager.getScore());
        label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
        label.setPrefWidth(130);
        label.setPrefHeight(20);
        label.setLayoutX(350);
        label.setLayoutY(450);
        return label;
    }

    /**
     * Initizlizes key listener so, that it can listen to w, a, s, d and arrows up, left, down, right
     */
    private void initKeyListener(Scene scene) {
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
    }

    /**
     * Updates label, which represents gamer's score
     */
    public void updateLabelScore() {
        Platform.runLater(() -> labelScore.setText("Score: " + SnakeManager.getScore()));
    }

    /**
     * The method starts the game
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Snake");
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
     *
     * @param node - element to place
     */
    @Override
    public void addObject(Node node) {
        Platform.runLater(() -> root.getChildren().add(node));
    }

    /**
     * The method remove an object from the pane
     *
     * @param node - element to remove
     */
    @Override
    public void removeObject(Node node) {
        Platform.runLater(() -> root.getChildren().remove(node));
    }
}