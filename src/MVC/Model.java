package MVC;

import MVC.Helpers.Logic;
import MVC.Helpers.ModelListener;
import MVC.Helpers.SnakeAteAppleListener;
import MVC.Helpers.State;

import java.util.ArrayList;
import java.util.List;

public class Model implements SnakeAteAppleListener {
    //All calculations are accumulated in logic object.
    private Logic logic;
    //MVC.Controller class obj. is later added in this list.
    List<ModelListener> listeners = new ArrayList<>();

    public Model(int snakeRow,int snakeCol)
    {
        logic = new Logic(new State(snakeRow,snakeCol));
        logic.setListener(this);
    }

    public void moveLeft()
    {
        moveInArbitraryDirection(logic.moveLeft());
    }

    public void moveRight()
    {
        moveInArbitraryDirection(logic.moveRight());
    }

    public void moveDown()
    {
        moveInArbitraryDirection(logic.moveDown());
    }

    public void moveUp()
    {
        moveInArbitraryDirection(logic.moveUp());
    }

    private void moveInArbitraryDirection(boolean succesfullyMoved) {
        if (succesfullyMoved)
            refreshView();
        else fireGameOver();
    }

    public void addListener(ModelListener listener)
    {
        listeners.add(listener);
    }

    //Notify MVC.Model listeners that model was updated/changed.
    public void refreshView()
    {
        for(ModelListener listener : listeners)
        {
            listener.onChange(logic.getBoard());
        }
    }

    //Notify MVC.Model listeners that game is over.
    public void fireGameOver()
    {
        for(ModelListener listener : listeners)
        {
            listener.fireLableChange("Game over");
        }
    }
    //Notify listeners to increase speed.
    @Override
    public void increaseSpeed() {
        for(ModelListener listener : listeners)
        {
            listener.increaseSpeed();
        }
    }
}
