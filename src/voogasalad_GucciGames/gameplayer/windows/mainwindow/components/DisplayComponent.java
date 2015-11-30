package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javax.sound.midi.ControllerEventListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public abstract class DisplayComponent {
    private GameControllerInterface myController;
    public DisplayComponent(GameControllerInterface controller) {
        setMyController(controller);
    }
    /**
     * 
     * @return Node to be displayed.
     */
    public abstract Node getNodeToDraw();
    
    /**
     * 
     * @return null if doesn't listen to the map, itself it if does.
     */
    public ListChangeListener<PlayerMapObjectInterface> getListener () {
        return null;
    }
    /**
     * Called if you want to manually refresh the DisplayComponent at the end of every turn.
     */
    public void updateDisplay () {
        return;
    }
    public GameControllerInterface getMyController () {
        return myController;
    }
    public void setMyController (GameControllerInterface myController) {
        this.myController = myController;
    }
}
