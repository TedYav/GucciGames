package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;

public interface DisplayComponent {
    /**
     * 
     * @return Node to be displayed.
     */
    public Node getNodeToDraw();
    
    /**
     * 
     * @return null if doesn't listen to the map, itself it if does.
     */
    public default ListChangeListener<PlayerMapObjectInterface> getListener () {
        return null;
    }
    /**
     * Called if you want to manually refresh the DisplayComponent at the end of every turn.
     */
    public default void updateDisplay () {
        return;
    }
}
