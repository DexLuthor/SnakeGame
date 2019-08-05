package Snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Rectangle {
    // =============== Fields ===============
        private boolean isAlive;
    private int snakeLength = 2;
    private List<? super Fruit> body;
    private Direction currentDirection;
    private static final int WIDTH = 20; // FIXME из-за изменений в размерах сломался дистанс ту и не работает нормально поедание
    private static final int HEIGHT = 20;
    private static volatile Snake snakeInstance;
    private Color color;

    // =============== Constructors ===============
    private Snake(){
        isAlive = true;
        body = new ArrayList<>();
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
    public void setAlive(boolean isAlive){
        System.out.println("Dead");
        System.out.println(snakeInstance);
        this.isAlive = isAlive;
    }

    public void setXCoordinate(double x){
        setX(x - WIDTH / 2);
    }
    public void setYCoordinate(double y){
        setY(y - HEIGHT / 2);
    }
    public double getXCoordinate(){
        return getX() + WIDTH / 2;
    }
    public double getYCoordinate(){
        return getY() + HEIGHT / 2;
    }
    public Direction getCurrentDirection(){
        return currentDirection;
    }

    // =============== Methods ===============
    public static Snake getInstance() {
        if(snakeInstance == null)
            synchronized (Snake.class){
                if(snakeInstance == null)
                    snakeInstance = new Snake();
            }
        return snakeInstance;
    }

    public void setPosition(double x, double y){
        setXCoordinate(x);
        setYCoordinate(y);
    }

    public void eatFruit(Fruit fruit) {
        body.add(fruit);
        updateSnakeLength();
    }

    private int updateSnakeLength(){
        int sum = snakeLength;
        for (int i = 0; i < body.size(); i++)
            if (body.get(i) instanceof Apple)
                sum += ((Apple) body.get(i)).getValue();
            else
                sum += ((BigApple) body.get(i)).getValue();
        //System.out.println(sum);
        return sum;
    }

    public double distanceTo(Fruit fruit){
        return Math.sqrt(Math.abs( (fruit.getX() - getXCoordinate()) * (fruit.getX() - getXCoordinate())
                + (fruit.getY() - getYCoordinate()) * (fruit.getY() - getYCoordinate()) ));
    }

    public void moveUp(){
        //if(isAlive)
            if (currentDirection == null || currentDirection != Direction.DOWN) {
                currentDirection = Direction.UP;
                setYCoordinate(getYCoordinate() - 10);
            }
    }
    public void moveLeft(){ // TODO сделать прерывестое движение
        //if(isAlive)
            if (currentDirection == null || currentDirection != Direction.RIGHT) {
                currentDirection = Direction.LEFT;
                setXCoordinate(getXCoordinate() - 10);
            }
    }
    public void moveDown(){ // TODO сделать прерывестое движение
        //if(isAlive)
            if (currentDirection == null || currentDirection != Direction.UP) {
                currentDirection = Direction.DOWN;
                setYCoordinate(getYCoordinate() + 10);
            }
    }
    public void moveRight(){ // TODO сделать прерывестое движение
        //if(isAlive)
            if (currentDirection == null || currentDirection != Direction.LEFT) {
                currentDirection = Direction.RIGHT;
                setXCoordinate(getXCoordinate() + 10);
            }
    }

    @Override
    public String toString(){
        return "x: " + getXCoordinate() + " y: " + getYCoordinate();
    }

    public class PartOfBody extends Rectangle{
        //  =============== Fields ===============
        private static final int WIDTH = 20;
        private static final int HEIGHT = 20;

//        private PartOfBody next = null;
//        private PartOfBody previous = null;
//        private PartOfBody me = null;
//        private Snake head = Snake.getInstance();

        // ================ Get/Set ===============
        public void setXCoordinate(double x){
            setX(x - WIDTH / 2);
        }
        public void setYCoordinate(double y){
            setY(y - HEIGHT / 2);
        }
        public double getXCoordinate(){
            return getX() + WIDTH / 2;
        }
        public double getYCoordinate(){
            return getY() + HEIGHT / 2;
        }

        // =============== Methods ===============
        public PartOfBody(){
            setFill(Color.rgb(228,228,228));
            setWidth(WIDTH);
            setHeight(HEIGHT);
            setPosition(111,222);
        }

        public void setPosition(double x, double y){
            setXCoordinate(x);
            setYCoordinate(y);
        }
    }

}