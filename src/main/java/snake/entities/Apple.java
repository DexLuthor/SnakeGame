package snake.entities;

import javafx.scene.paint.Color;

/**
 * @author Yevhenii Kozhevin
 * 
 */
public final class Apple extends Fruit {
    public static final Color COLOR = Color.rgb(17, 255, 0); // color dedicnost
    
    // =============== Constructors ===============
    public Apple(double x, double y) {
        super(x, y);
        setFill(COLOR);
    }
    
    public static int getValue() {
    	return 1;
    }
}