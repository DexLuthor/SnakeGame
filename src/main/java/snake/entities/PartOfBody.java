package snake.entities;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public final class PartOfBody extends Cell {
    // =============== Fields ===============
    private static int partsCounter = 0;

    // =============== Constructor ===============
    public PartOfBody() {
        super();
        setFill(Color.rgb(228, 228, 228));
        ++partsCounter;
        follow();
    }

    // =============== Methods ===============
    private void follow(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> { //100 ms
            Cell last = SnakeManager.parts.get(SnakeManager.parts.size() - 1);
            Cell preLast = SnakeManager.parts.get(SnakeManager.parts.size() - 2);
            last.setPosition(preLast.getXCoordinate(), preLast.getYCoordinate());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play(); //FIXME
    }
}