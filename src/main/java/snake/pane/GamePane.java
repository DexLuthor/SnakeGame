package snake.pane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import snake.entities.SnakeManager;
import snake.interfaces.*;
import snake.engine.GameLogic;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

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

        createLabel();

        scene = new Scene(new Group(root, labelScore), Color.BLACK);
        scene.setOnMouseClicked(e -> System.out.println(e.getX() + "" + e.getY())); //REFACTOR
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

    private void createLabel(){
        labelScore = new Label(String.valueOf(SnakeManager.getScore()));
        labelScore.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        labelScore.setTextFill(Color.WHITE);
        labelScore.setStyle("-fx-font-size: 25; -fx-font-weight: bold" );
        labelScore.setPrefWidth(100);
        labelScore.setPrefHeight(25);
        labelScore.setLayoutX(400);
        labelScore.setLayoutY(475);
    }

    public void updateLabelScore(){
        Platform.runLater(() -> labelScore.setText(String.valueOf(SnakeManager.getScore())));
    }

    @Override public void start(Stage stage) {
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