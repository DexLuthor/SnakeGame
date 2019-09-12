package snake.entities;

import javafx.scene.paint.Color;

public final class PartOfSnake extends Cell {
    // =============== Constructor ===============
    public PartOfSnake() {
        super();
        setFill(Color.rgb(228, 228, 228));
    }

    public void follow(Cell cell){
        setPosition(cell.getPreviousXCoordinate(), cell.getPreviousYCoordinate());
    }
}