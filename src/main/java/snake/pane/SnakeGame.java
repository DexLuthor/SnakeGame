package snake.pane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import snake.engine.GameLogic;
import snake.interfaces.IGameLogic;
import snake.interfaces.IGraphicInterface;

/**
 * @author Yevhenii Kozhevin
 *
 *         Class is responsible for area, where game is running
 */
public class SnakeGame extends Application implements IGraphicInterface {

	public static void main(String[] args) {
		Application.launch(args);
	}

	// =============== CONSTANTS ===============
	public static final int SIZE = 500;
	// public static final int HEIGHT = 500;

	// =============== Fields ===============
	private Pane root;
	private Scene scene;
	private Label labelScore;

	private IGameLogic logic = new GameLogic(this);

	// =============== Methods ===============

	@Override
	public void start(Stage stage) {
		stage.setTitle("Snake");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void init() {
		initGreetingPane();
	}

	private void initGreetingPane() {
		root = new Pane();
		root.setPrefSize(SIZE - 12, SIZE - 12); // fx adds additional 12px
		root.setBackground(new Background(
				new BackgroundImage(new Image("/snake-bg.jpg", 700, 500, false, true), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		Button btn = new Button("Start");
		btn.setPrefSize(80, 40);
		btn.setDefaultButton(true);
		btn.setLayoutX(SIZE / 2 - btn.getWidth() / 2 - 25);
		btn.setLayoutY(SIZE - 50);
		root.getChildren().add(btn);
		scene = new Scene(root);

		btn.setOnAction(e -> initGamePane());
	}

	private void initGamePane() {
		root = new Pane();
		root.setPrefSize(SIZE, SIZE);

		labelScore = initLabel();

		Stage stage = (Stage) scene.getWindow();
		scene = new Scene(new Group(root, labelScore), Color.BLACK);
		
		scene.setOnMouseClicked(e -> System.out.println(e.getX() + " " + e.getY()));
		
		stage.setScene(scene);

		initKeyListener(scene);
		logic.initGame();
	}

	private Label initLabel() {
		Label label = new Label();
		label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		label.setTextFill(Color.WHITE);
		label.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
		label.setPrefWidth(130);
		label.setPrefHeight(20);
		label.setLayoutX(350);
		label.setLayoutY(450);

		// label.textProperty().bind();

		return label;
	}

	@SuppressWarnings("incomplete-switch")
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

	@Override
	public void stop() {
		logic.finishGame();
	}

	@Override
	public void addObject(Node node) {
		addAllObjects(node);
	}

	@Override
	public void removeObject(Node node) {
		removeAllObjects(node);
	}

	@Override
	public void addAllObjects(Node... nodes) {
		Platform.runLater(() -> {
			for (int i = 0; i < nodes.length; i++) {
				root.getChildren().add(nodes[i]);
			}
		});
	}

	@Override
	public void removeAllObjects(Node... nodes) {
		Platform.runLater(() -> {
			for (int i = 0; i < nodes.length; i++) {
				root.getChildren().remove(nodes[i]);
			}
		});
	}

	@Override
	public void clear() {
		root.getChildren().clear();
	}
}