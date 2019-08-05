package Snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class representing head of snake, which is the main part of snake's body
 */
public class Snake extends Rectangle {
    // =============== Fields ===============
    private boolean isAlive;
    private Direction currentDirection;
    private static final int WIDTH = 20; // FIXME из-за изменений в размерах сломался дистанс ту и не работает нормально поедание
    private static final int HEIGHT = 20;
    private static volatile Snake snakeInstance;
    private Color color;

    // =============== Constructors ===============
    private Snake(){
        isAlive = true;
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
        //TODO
    }

    public double distanceTo(Fruit fruit){
        return Math.sqrt(Math.abs( (fruit.getX() - getXCoordinate()) * (fruit.getX() - getXCoordinate())
                + (fruit.getY() - getYCoordinate()) * (fruit.getY() - getYCoordinate()) ));
    }

    public void moveUp(){
        if (currentDirection == null || currentDirection != Direction.DOWN) {
            currentDirection = Direction.UP;
            setYCoordinate(getYCoordinate() - 10);
        }
    }
    public void moveLeft(){ // TODO сделать прерывестое движение
        if (currentDirection == null || currentDirection != Direction.RIGHT) {
            currentDirection = Direction.LEFT;
            setXCoordinate(getXCoordinate() - 10);
        }
    }
    public void moveDown(){ // TODO сделать прерывестое движение
        if (currentDirection == null || currentDirection != Direction.UP) {
            currentDirection = Direction.DOWN;
            setYCoordinate(getYCoordinate() + 10);
        }
    }
    public void moveRight(){ // TODO сделать прерывестое движение
        if (currentDirection == null || currentDirection != Direction.LEFT) {
            currentDirection = Direction.RIGHT;
            setXCoordinate(getXCoordinate() + 10);
        }
    }

    @Override
    public String toString(){
        return "x: " + getXCoordinate() + " y: " + getYCoordinate();
    }
}