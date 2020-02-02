package snake.entities;

import javafx.scene.paint.Color;

public final class Orange extends Fruit {
	// =============== Constants ===============
	public static final int TIME_TO_GENERATE = 5;
	public static final Color COLOR = Color.rgb(255, 215, 0);;
	
	// =============== Constructors ===============
	public Orange(double x, double y) {
		super(x, y);
		setFill(COLOR);
	}

	public static int getValue() {
		return 2;
	}
}