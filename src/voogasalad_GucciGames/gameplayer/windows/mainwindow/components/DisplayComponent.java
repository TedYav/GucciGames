package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javax.sound.midi.ControllerEventListener;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;

public abstract class DisplayComponent extends WindowComponent{
    public DisplayComponent(GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
    }
    
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
        
    }
}
