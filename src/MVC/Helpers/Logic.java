package MVC.Helpers;

import BoardComponents.*;
import BoardComponents.Board.Board;
import BoardComponents.Food.Food;
import BoardComponents.Snake.Snake;
import BoardComponents.Snake.SnakeChange;

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
        return moveSnakeInArbitraryDirection(0, 1, DIRECTION_RIGHT);
    }

    public boolean moveLeft()
    {
        return moveSnakeInArbitraryDirection(0, -1, DIRECTION_LEFT);
    }

    public boolean moveDown()
    {
        return moveSnakeInArbitraryDirection(1, 0, DIRECTION_DOWN);
    }

    public boolean moveUp()
    {
        return moveSnakeInArbitraryDirection(-1, 0, DIRECTION_UP);
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

    private boolean moveSnakeInArbitraryDirection(int deltaX, int deltaY, int nextDirectionConstant) {
        if (snakeMeetsItself()) return false;
        snakeEatsApple();
        SnakeChange.moveSnake(state.getStateBoard(), state.getStateSnake(), deltaX, deltaY);
        currentDirection = nextDirectionConstant;
        return true;
    }

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
