// This entire file is part of my masterpiece.
// John Dai
package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;


/**
 * The class DisplayComponent provides a starting point for creating a sidebar GUI component
 * This class should be extended in order to make custom sidebar GUI components.
 * 
 * @author John Dai
 */
public abstract class DisplayComponent extends WindowComponent {

    /**
     * Instantiates a new display component by calling WindowComponent's constructor.
     *
     * @param scene the scene
     * @param controller the controller
     */
    public DisplayComponent (GameScene scene, GameControllerInterface controller) {
        super(scene, controller);
    }

    /**
     * Gets the listener corresponding to itself.
     * This is needed if the display need to update properly according to the currently active
     * MapObject.
     *
     * @return null if doesn't listen to the MainMap, itself it if does.
     */
    public ListChangeListener<PlayerMapObjectInterface> getListener () {
        return null;
    }

    /**
     * Called if you want to manually refresh the DisplayComponent at the end of
     * every turn.
     * By default it is left empty.
     */
    public void updateDisplay () {
    }
}
