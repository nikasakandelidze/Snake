
import java.util.LinkedList;

public class Logic {
    //This boolean will be true only in tests.
    public boolean SANITY_CHECK = false;

    //Constants for determining directions of snake.
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_LEFT = -1;
    private static final int DIRECTION_UP = 2;
    private static final int DIRECTION_DOWN = -2;
    private State state;

    public Logic(State state)
    {
        this.state = state;
    }
    private int currentDirection;
    SnakeAteAppleListener listener;

    public boolean moveRight()
    {
        if (snakeMeetsItself()) return false;
        snakeEatsApple();
        SnakeChange.moveSnake(state.getStateBoard(),state.getStateSnake(), 0,1);
        currentDirection = DIRECTION_RIGHT;
        return true;
    }

    public boolean moveLeft()
    {
        if (snakeMeetsItself()) return false;
        snakeEatsApple();
        SnakeChange.moveSnake(state.getStateBoard(),state.getStateSnake(), 0,-1);
        currentDirection = DIRECTION_LEFT;
        return true;
    }

    public boolean moveDown()
    {
        if (snakeMeetsItself()) return false;
        snakeEatsApple();
        SnakeChange.moveSnake(state.getStateBoard(),state.getStateSnake(), 1,0);
        currentDirection = DIRECTION_DOWN;
        return true;
    }

    public boolean moveUp()
    {
        if (snakeMeetsItself()) return false;
        snakeEatsApple();
        SnakeChange.moveSnake(state.getStateBoard(),state.getStateSnake(), -1,0);
        currentDirection = DIRECTION_UP;
        return true;
    }

    public Board getBoard()
    {
        return state.getStateBoard();
    }

    public Snake getSnake()
    {
        return state.getStateSnake();
    }

    public Food getApple() { return state.getStateApple(); }

    //Snake/apple relationship.
    private void snakeEatsApple()
    {
        if(state.snakeMeetsApple())
        {
            state.generateApple();
            SnakeChange.growSnake(state.getStateSnake(),state.getStateBoard(),currentDirection*-1);
            //Since we don't need listeners in tests.
            if(!SANITY_CHECK)listener.increaseSpeed();
        }
    }

    private boolean snakeMeetsItself()
    {
        LinkedList<Cell> snakeBody = state.getStateSnake().getSnakeBody();
        for(Cell element : snakeBody)
        {
            if(element.getCol() == state.getStateSnake().getCol() && element.getRow() == state.getStateSnake().getRow()) return true;
        }
        return false;
    }

    public void setListener(SnakeAteAppleListener listener)
    {
        this.listener = listener;
    }
}
