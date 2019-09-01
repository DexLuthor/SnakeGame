package snake.entities;

import javafx.scene.paint.Color;

public final class PartOfBody extends Cell {

    // =============== Constructor ===============
    public PartOfBody() {
        super();
        setFill(Color.rgb(228, 228, 228));
    }

    // =============== Methods ===============
    private void follow(){
        Cell last = SnakeManager.parts.get(SnakeManager.parts.size() - 1);
        Cell preLast = SnakeManager.parts.get(SnakeManager.parts.size() - 2);
        last.setPosition(preLast.getXCoordinate(), preLast.getYCoordinate());
    }
}