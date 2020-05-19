package MVC.Helpers;

import BoardComponents.Board.Board;

public interface ModelListener {
    void onChange(Board board);
    void fireLableChange(String msg);
    void increaseSpeed();
}
