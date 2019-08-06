package Snake.Entities;

import Snake.engine.GameLogic;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Snake.GamePane;
import java.util.List;

public class Snake extends Rectangle {
    //     =============== CONSTANTS ===============
    private static final int WIDTH = 20; // FIXME из-за изменений в размерах сломался дистанс ту и не работает нормально поедание
    private static final int HEIGHT = 20;

    // =============== Fields ===============
    private boolean isAlive;
    private int snakeLength = 2;
    private List<Fruit> body;
    private GameLogic.Direction currentDirection;
    private static volatile Snake snakeInstance;
    private Color color;

    // =============== Constructors ===============
    private Snake() {
        isAlive = true;
        //body = new ArrayList<>();
        color = Color.WHITE;

        setWidth(WIDTH);
        setHeight(HEIGHT);

        setX(((GamePane.WIDTH + 12) / 2) - WIDTH / 2); // устанавливаю центр змейки в центре площади
        setY(((GamePane.HEIGHT + 12) / 2) - HEIGHT / 2);

        setFill(color);
    }

    // =============== Get/Set ===============
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        System.out.println("Dead");
        System.out.println(snakeInstance);
        this.isAlive = isAlive;
    }

    public void setXCoordinate(double x) {
        setX(x - WIDTH / 2.0);
    }

    public void setYCoordinate(double y) {
        setY(y - HEIGHT / 2.0);
    }

    public double getXCoordinate() {
        return getX() + WIDTH / 2.0;
    }

    public double getYCoordinate() {
        return getY() + HEIGHT / 2.0;
    }

    public GameLogic.Direction getCurrentDirection() {
        return currentDirection;
    }

    // =============== Methods ===============
    public static Snake getInstance() {
        if (snakeInstance == null) {
            synchronized (Snake.class) {
                if (snakeInstance == null) {
                    snakeInstance = new Snake();
                }
            }
        }
        return snakeInstance;
    }

    public void setPosition(double x, double y) {
        setXCoordinate(x);
        setYCoordinate(y);
    }

    public void eatFruit(Fruit fruit) {
        body.add(fruit);
        updateSnakeLength();
    }

    private int updateSnakeLength() {
        int sum = snakeLength;
        for (Object bodyPart : body) {
            if (bodyPart instanceof Apple) {
                sum += ((Apple) bodyPart).getValue();
            } else if (bodyPart instanceof BigApple) {
                sum += ((BigApple) bodyPart).getValue();
            }
        }
        //System.out.println(sum);
        return sum;
    }

    public double distanceTo(Fruit fruit) {
        return Math.sqrt(Math.abs((fruit.getX() - getXCoordinate()) * (fruit.getX() - getXCoordinate())
                + (fruit.getY() - getYCoordinate()) * (fruit.getY() - getYCoordinate())));
    }

    public void moveUp() {
        if (currentDirection == null || currentDirection != GameLogic.Direction.DOWN) {
            currentDirection = GameLogic.Direction.UP;
            setYCoordinate(getYCoordinate() - 10);
        } else {
            setAlive(false);
        }
    }

    public void moveLeft() { // TODO сделать прерывестое движение
        if (currentDirection == null || currentDirection != GameLogic.Direction.RIGHT) {
            currentDirection = GameLogic.Direction.LEFT;
            setXCoordinate(getXCoordinate() - 10);
        } else {
            setAlive(false);
        }
    }

    public void moveDown() { // TODO сделать прерывестое движение
        if (currentDirection == null || currentDirection != GameLogic.Direction.UP) {
            currentDirection = GameLogic.Direction.DOWN;
            setYCoordinate(getYCoordinate() + 10);
        } else {
            setAlive(false);
        }
    }

    public void moveRight() { // TODO сделать прерывестое движение
        if (currentDirection == null || currentDirection != GameLogic.Direction.LEFT) {
            currentDirection = GameLogic.Direction.RIGHT;
            setXCoordinate(getXCoordinate() + 10);
        } else {
            setAlive(false);
        }
    }

    @Override
    public String toString() {
        return "x: " + getXCoordinate() + " y: " + getYCoordinate();
    }

    public class PartOfBody extends Rectangle {
        //  =============== Fields ===============
        private static final int WIDTH = 20;
        private static final int HEIGHT = 20;

//        private PartOfBody next = null;
//        private PartOfBody previous = null;
//        private PartOfBody me = null;
//        private ru.edem.snake head = ru.edem.snake.getInstance();

        // ================ Get/Set ===============
        public void setXCoordinate(double x) {
            setX(x - WIDTH / 2);
        }

        public void setYCoordinate(double y) {
            setY(y - HEIGHT / 2);
        }

        public double getXCoordinate() {
            return getX() + WIDTH / 2;
        }

        public double getYCoordinate() {
            return getY() + HEIGHT / 2;
        }

        // =============== Methods ===============
        public PartOfBody() {
            setFill(Color.rgb(228, 228, 228));
            setWidth(WIDTH);
            setHeight(HEIGHT);
            setPosition(111, 222);
        }

        public void setPosition(double x, double y) {
            setXCoordinate(x);
            setYCoordinate(y);
        }
    }

}