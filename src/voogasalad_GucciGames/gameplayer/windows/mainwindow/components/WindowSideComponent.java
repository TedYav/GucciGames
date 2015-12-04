package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public abstract class WindowSideComponent extends WindowComponent implements Observer{
    private List<DisplayComponent> myComponents;
    private PlayerMapObjectInterface activeMapObject;
    
    public WindowSideComponent(GameScene scene, GameControllerInterface controller, List<DisplayComponent> components){
        super(scene,controller);
        getController().addActiveMOObserver(this);
        setMyComponents(components);
    }
    
    public List<ListChangeListener<PlayerMapObjectInterface>> requestListeners() {
        List<ListChangeListener<PlayerMapObjectInterface>> listeners = new ArrayList<ListChangeListener<PlayerMapObjectInterface>>();
        for (DisplayComponent d: getMyComponents()) {
            if (d.getListener()!=null) {
                listeners.add(d.getListener());
            }
        }
        return listeners;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg!=null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
            setActiveMapObject((PlayerMapObjectInterface)arg);
        }
    }
    
    public void updateComponents() {
        for (DisplayComponent d: myComponents) {
            d.updateDisplay();
        }
    }
    
    /**
     * Initial bootstrapping such as loading CSS classes, adding Nodes to the display container, etc.
     */
    protected abstract void initializeData();

    public List<DisplayComponent> getMyComponents () {
        return myComponents;
    }

    public void setMyComponents (List<DisplayComponent> myComponents) {
        this.myComponents = myComponents;
    }

    public PlayerMapObjectInterface getActiveMapObject () {
        return activeMapObject;
    }

    public void setActiveMapObject (PlayerMapObjectInterface activeMapObject) {
        this.activeMapObject = activeMapObject;
    }

}
