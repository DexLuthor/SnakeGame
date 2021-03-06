package snake.engine;

import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import snake.entities.Apple;
import snake.entities.Orange;
import snake.entities.Snake;
import snake.entities.Snake.PartOfSnake;
import snake.entities.factory.FruitFactory;
import snake.interfaces.IGameLogic;
import snake.interfaces.IGraphicInterface;
import snake.main.SnakeGame;
import snake.results.Results;
import snake.utils.Utils;

/**
 * Class is responsible for game logic implementation
 * 
 * @author Yevhenii Kozhevin
 *
 */
public class GameLogic implements IGameLogic {
	// =============== Constants ===============
	/**
	 * 
	 */
	private final IGraphicInterface GUI;
	/**
	 * Singleton object of {@code Snake} class
	 */
	private final Snake snake = Snake.getInstance();
	/**
	 * Objects responsible for duration of a turn
	 */
	private final Stopwatch stopwatch;

	// =============== Fields ===============
	/**
	 * Flag representing the state of a turn
	 */
	private boolean isGameRunning = false;
	/**
	 * Current direction of snake's head
	 */
	private Direction currentDirection;
	/**
	 * Reference on {@code Apple} object on a pane
	 */
	private Apple appleOnPane;
	/**
	 * Reference on {@code Orange} object on a pane
	 */
	private Orange orangeOnPane;

	// =============== Constructors ===============
	public GameLogic(IGraphicInterface gui) {
		GUI = gui;
		stopwatch = new Stopwatch();
		currentDirection = Direction.RIGHT;
	}

	// =============== Methods ===============
	@Override
	public void initGame() {
		GUI.addObject(snake);
		snake.setPosition((SnakeGame.SIZE + (snake.getWidth()) / 2) / 2 - 5,
				(SnakeGame.SIZE + (snake.getWidth() / 2)) / 2 - 5);
		isGameRunning = true;
		plantApple();
		startMovingSnake();
		stopwatch.start();
		startGeneratingOranges();
	}

	/**
	 * Adds an apple on the pane on a random position
	 */
	private void plantApple() {
		appleOnPane = FruitFactory.createAppleOnRandomPosition();
		GUI.addObject(appleOnPane);
	}

	/**
	 * The method starts a new thread and moves the whole snake with control of
	 * position
	 */
	private void startMovingSnake() {
		new Thread(() -> {
			while (isGameRunning) {
				snake.updatePreviousPositions();
				snake.moveHead(currentDirection);
				snake.moveBody();
				checkSnakePosition();
				Utils.sleep(100);
			}
		}).start();
	}

	/**
	 * Finishes a turn if snake's head is out of borders, have run into body and
	 */
	private void checkSnakePosition() {
		if (isOutOfBorders() || snake.runIntoYourself()) {
			finishGame();
		}
		checkPositionRelativeToFruit();
	}

	/**
	 * Checks if snake's head is not within borders
	 * 
	 * @return true if head is out of borders
	 */
	private boolean isOutOfBorders() {
		return snake.getXCoordinate() <= -1 || snake.getXCoordinate() >= SnakeGame.SIZE + 1
				|| snake.getYCoordinate() <= -1 || snake.getYCoordinate() >= SnakeGame.SIZE + 1;
	}

	@Override
	public void finishGame() {
		isGameRunning = false;
		stopwatch.stop();
		updateBestResultsIfNeeded();
		restartWindow();
	}

	/**
	 * If current results are better than previous, or current results are the only
	 * existing results the method will save it in .json file
	 */
	private void updateBestResultsIfNeeded() {
		Results.check(getCurrentResults());
	}

	/**
	 * When turn is finished, we can choose in restart window we want to exit or to
	 * continue playing
	 */
	private void restartWindow() {
		Platform.runLater(() -> {
			Stage stage = new Stage();

			stage.setTitle("Restart");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);

			Pane root = new Pane();
			Scene scene = new Scene(root, 300, 100);

			Label label = new Label();
			if (snake.getPartsOfSnake().size() < 624) {
				label.setText("Loose :(");
				label.setTextFill(Color.rgb(255, 0, 0));
			} else {
				label.setText("Win :D");
				label.setTextFill(Color.rgb(0, 255, 0));
			}

			label.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

			Button restartButton = new Button("Restart");
			restartButton.setDefaultButton(true);
			restartButton.setOnAction(e -> {
				clearData();
				stage.hide();
				initGame();
			});

			Button exitButton = new Button("Exit");
			exitButton.setCancelButton(true);
			exitButton.setOnAction(e -> System.exit(1));

			restartButton.setLayoutX(230);
			restartButton.setLayoutY(70);
			exitButton.setLayoutX(170);
			exitButton.setLayoutY(70);
			label.setLayoutX(125);
			label.setLayoutY(30);

			root.getChildren().addAll(restartButton, exitButton, label);

			stage.setScene(scene);
			stage.show();
		});
	}

	/**
	 * Clears a pane, refreshing snakes' data when restarting
	 */
	private void clearData() {
		GUI.clear();
		snake.dropData();
		snake.getPartsOfSnake().clear();
	}

	/**
	 * Checks if snake's head is on apple or orange position
	 */
	private void checkPositionRelativeToFruit() {
		if (snake.distanceTo(appleOnPane) < 10) {
			GUI.removeObject(appleOnPane);
			List<PartOfSnake> addedParts = snake.addPart(Apple.getValue());
			for (int i = 0; i < addedParts.size(); i++) {
				GUI.addObject(addedParts.get(i));
			}
			plantApple();
			snake.incrementAppleEatenCount();
		}
		if (orangeOnPane != null && snake.distanceTo(orangeOnPane) < 10) {
			GUI.removeObject(orangeOnPane);
			List<PartOfSnake> addedParts = snake.addPart(Orange.getValue());
			for (int i = 0; i < addedParts.size(); i++) {
				GUI.addObject(addedParts.get(i));
			}
			snake.incrementOrangesEatenCount();
		}
	}

	/**
	 * With defined probability generates oranges every
	 * {@code Orange.TIME_TO_GENERATE} seconds
	 */
	private void startGeneratingOranges() {
		final Timeline[] array = new Timeline[1];
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Orange.TIME_TO_GENERATE), event -> {
			if (!isGameRunning) {
				array[0].stop();
				return;
			}
			if (Math.random() >= 0.9) {
				if (orangeOnPane != null) {
					GUI.removeObject(orangeOnPane);
				}
				orangeOnPane = FruitFactory.createOrangeOnRandomPosition();
				GUI.addObject(orangeOnPane);
			}
		}));
		array[0] = timeline;
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	@Override
	public void changeDirection(Direction direction) {
		if (this.currentDirection != direction.getOppositeDirection()) {
			this.currentDirection = direction;
		}
	}

	/**
	 * Returns results of a turn
	 * 
	 * @return object of {@code Results} class filled with data of a turn
	 */
	private Results getCurrentResults() {
		Results results = new Results();
		results.setCountOfEatenApples(snake.getCountOfEatenApples());
		results.setCountOfEatenOranges(snake.getCountOfEatenOranges());
		results.setLength(snake.getLength());
		results.setTimeMinutes(stopwatch.getTimeMinutes());
		return results;
	}

	/**
	 * Enum containing 4 directions
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
		/**
		 * Returns opposite direction to actual
		 * 
		 * @return opposite direction to actual
		 */
		public Direction getOppositeDirection() {
			switch (this) {
			case UP:
				return DOWN;
			case LEFT:
				return RIGHT;
			case DOWN:
				return UP;
			case RIGHT:
				return LEFT;
			default:
				return null;
			}
		}
	}
}