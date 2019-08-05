package Snake;

public class Controller implements IGameLogic, Runnable {
    private GamePane gamePane = GamePane.getInstance();
    // =============== Fields ===============
    private Snake snakeInstance = Snake.getInstance();
    private Direction snakeDirection = snakeInstance.getCurrentDirection();

    private boolean isMoving;

    // =============== Get/Set ===============
    public double getSnakeX() {
        return snakeInstance.getXCoordinate();
    }
    public double getSnakeY() {
        return snakeInstance.getYCoordinate();
    }

    // =============== Methods ===============
    @Override
    public void checkSnakePosition() {
        if (snakeInstance.isAlive()) {
            checkPositionWithinBorders();
            checkPositionRelativeToFruit();
        }
    }
    private void checkPositionWithinBorders(){
        //System.out.println(snakeInstance);
        if (getSnakeX() < 10 || getSnakeX() > 490)
            snakeInstance.setAlive(false);
        if (getSnakeY() < 10 || getSnakeY() > 490)
            snakeInstance.setAlive(false);
    }
    private void checkPositionRelativeToFruit(){
        if (snakeInstance.distanceTo(gamePane.getApple()) < 12) { // FIXME прописать нормальную дисстанцию
            snakeInstance.eatFruit(gamePane.getBigApple());
            gamePane.removeFruit(gamePane.getApple());
            //addPart(); // TODO
            gamePane.plantApple();
        }
        if (gamePane.getBigApple() != null && snakeInstance.distanceTo(gamePane.getBigApple()) < 15) {
            snakeInstance.eatFruit(gamePane.getBigApple());
            //addPart(); // TODO
            gamePane.removeFruit(gamePane.getBigApple());
        }
    }

    @Override
    public void move(Direction direction) {
        switch (direction){
            case UP:
                snakeInstance.moveUp();
                checkSnakePosition();
                break;
            case LEFT:
                snakeInstance.moveLeft();
                checkSnakePosition();
                break;
            case DOWN:
                snakeInstance.moveDown();
                checkSnakePosition();
                break;
            case RIGHT:
                snakeInstance.moveRight();
                checkSnakePosition();
                break;
        }
    }

    @Override
    public void run() {

    }
}
