package BoardComponents.Snake;

import BoardComponents.Board.Board;
import BoardComponents.Cell;

import java.awt.*;
import java.util.LinkedList;

//stateless class/bean.
public class SnakeChange {
    //Move snake with deltaRow and deltaCol
    public static void moveSnake(Board board, Snake snake, int deltaRow, int deltaCol) {
        int newRow = snake.getRow() + deltaRow;
        int newCol = snake.getCol() + deltaCol;
        if (newCol < 0) newCol = board.getNumCols() - 1;
        if (newRow < 0) newRow = board.getNumRows() - 1;
        if (newRow > board.getNumRows() - 1) newRow = 0;
        if (newCol > board.getNumCols() - 1) newCol = 0;
        moveSnakeToNextLocation(board, snake, newRow, newCol);
    }

    private static void moveSnakeToNextLocation(Board board, Snake snake, int newRow, int newCol) {
        //If body is empty modify only head of snake.
        if ( snake.getSnakeBody().isEmpty()) {
            snakeHeadMove(board, snake, newRow, newCol);
        } else {
            snakeHeadAndBodyMove(board, snake, newRow, newCol);
        }
    }

    private static void snakeHeadAndBodyMove(Board board, Snake snake, int newRow, int newCol) {
        //If snake has body, removeLast cell, add new one in front.
        Cell newHead = new Cell(newRow, newCol, Color.RED);
        board.setColor(snake.getSnakeBody().getLast().getRow(), snake.getSnakeBody().getLast().getCol(), Color.BLACK);
        snake.getSnakeBody().removeLast();
        snake.getSnakeBody().addFirst(new Cell(snake.getHeadRow(), snake.getHeadCol(), snake.getColor()));
        snake.setComponent(newHead);
        board.setColor(newHead.getRow(), newHead.getCol(), Color.RED);
    }

    private static void snakeHeadMove(Board board, Snake snake, int newRow, int newCol) {
        board.setColor(snake.getRow(), snake.getCol(), Color.BLACK);
        snake.setHeadCol(newCol);
        snake.setHeadRow(newRow);
        board.setColor(snake.getHeadRow(), snake.getHeadCol(), Color.RED);
    }

    //Grow snake
    public static void growSnake(Snake snake, Board board, int direction){
        int r = 0;
        int c = 0;
        if(snake.getSnakeBody().isEmpty())
        {
            r = snake.getRow();
            c = snake.getCol();
        }
        else{
            r = snake.getSnakeBody().getLast().getRow();
            c = snake.getSnakeBody().getLast().getCol();
        }
        if( direction == 1) c++;
        else if (direction == -1) c--;
        else if (direction == 2) r--;
        else if (direction == -2) r++;
        //This next part is needed as growth may have bugs ,
        //When snake's body is on the border of board
        //and snake in specific direction eats an apple.
        if (c < 0) c = board.getNumCols() - 1;
        if (r < 0) r = board.getNumRows() - 1;
        if (r > board.getNumRows() - 1) r = 0;
        if (c > board.getNumCols() - 1) c = 0;
        Cell cell = new Cell(r,c,Color.RED);
        snake.getSnakeBody().addLast(cell);
    }
}
