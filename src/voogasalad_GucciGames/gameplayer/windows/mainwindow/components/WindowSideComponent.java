// This entire file is part of my masterpiece.
// John Dai
package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;


/**
 * The class WindowSideComponent represents the base of a sidebar display that should be displayed
 * in the MainGameScene.
 * This class should be extended in order to make custom sidebars.
 * 
 * @author John Dai
 */
public abstract class WindowSideComponent extends WindowComponent implements Observer {
    private List<DisplayComponent> myComponents;
    private PlayerMapObjectInterface activeMapObject;

    /**
     * Instantiates a new window side component by calling WindowComponent's constructor and
     * registering this instance as a listener to the Controller's active MapObject.
     *
     * @param scene the scene
     * @param controller the controller
     * @param components the List of DisplayComponents
     */
    public WindowSideComponent (GameScene scene,
                                GameControllerInterface controller,
                                List<DisplayComponent> components) {
        super(scene, controller);
        getController().addActiveMOObserver(this);
        setComponents(components);
    }

    /**
     * Request ListChangeListeners from the list of DisplayComponents.
     *
     * @return an immutable List of ListChangeListeners
     */
    public List<ListChangeListener<PlayerMapObjectInterface>> requestListeners () {
        List<ListChangeListener<PlayerMapObjectInterface>> listeners = myComponents.stream()
                .filter(d -> d.getListener() != null)
                .map(d -> d.getListener())
                .collect(Collectors.toList());
        return Collections.unmodifiableList(listeners);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        if (arg != null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
            setActiveMapObject((PlayerMapObjectInterface) arg);
        }
    }

    /**
     * Update each DisplayComponent.
     */
    public void updateComponents () {
        myComponents.forEach(d -> d.updateDisplay());
    }

    /**
     * Place the initial bootstrapping code such as loading CSS classes, adding Nodes to the
     * display container, etc.
     * Can be called anywhere but recommended to be called at the end of the constructor.
     */
    protected abstract void initializeData ();

    /**
     * Gets the Iterator over each DisplayComponent's Parent Node.
     *
     * @return the Iterator over each DisplayComponent's Parent Node
     */
    public Iterator<? extends Node> getComponentParentsIter () {
        return myComponents.stream().map(c -> c.getParent()).collect(Collectors.toList())
                .iterator();
    }

    /**
     * Sets the DisplayComponents.
     *
     * @param components the new List of DisplayComponents
     */
    public void setComponents (List<DisplayComponent> components) {
        myComponents = components;
    }

    /**
     * Gets the active MapObject. Can be null.
     * Not currently used but may be useful for future extensions.
     *
     * @return the active MapObject
     */
    public PlayerMapObjectInterface getActiveMapObject () {
        return activeMapObject;
    }

    /**
     * Sets the active MapObject.
     * Not currently used but may be useful for future extensions.
     *
     * @param activeMapObject the new active mapObject
     */
    public void setActiveMapObject (PlayerMapObjectInterface activeMapObject) {
        this.activeMapObject = activeMapObject;
    }

}
