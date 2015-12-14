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

public abstract class WindowSideComponent extends WindowComponent implements Observer {
	private List<DisplayComponent> myComponents;
	private PlayerMapObjectInterface activeMapObject;

	public WindowSideComponent(GameScene scene, GameControllerInterface controller, List<DisplayComponent> components) {
		super(scene, controller);
		getController().addActiveMOObserver(this);
		setComponents(components);
	}

	public List<ListChangeListener<PlayerMapObjectInterface>> requestListeners() {
	        List<ListChangeListener<PlayerMapObjectInterface>> listeners = myComponents.stream()
	                .filter(d->d.getListener()!=null)
	                .map(d->d.getListener())
	                .collect(Collectors.toList());
		return Collections.unmodifiableList(listeners);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
			setActiveMapObject((PlayerMapObjectInterface) arg);
		}
	}

	public void updateComponents() {
	    myComponents.forEach(d->d.updateDisplay());
	}

	/**
	 * Initial bootstrapping such as loading CSS classes, adding Nodes to the
	 * display container, etc.
	 */
	protected abstract void initializeData();

	public Iterator<? extends Node> getComponentParentsIter() {
		return myComponents.stream().map(c->c.getParent()).collect(Collectors.toList()).iterator();
	}

	public void setComponents(List<DisplayComponent> myComponents) {
		this.myComponents = myComponents;
	}

	public PlayerMapObjectInterface getActiveMapObject() {
		return activeMapObject;
	}

	public void setActiveMapObject(PlayerMapObjectInterface activeMapObject) {
		this.activeMapObject = activeMapObject;
	}

}
