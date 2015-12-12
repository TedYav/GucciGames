package voogasalad_GucciGames.gameplayer.windows.mainwindow;

import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;

public class MainGameWindow extends GameWindow {

	private GameSceneManager mySceneManager;

	public MainGameWindow(int id, GameWindowManager manager, GameController controller) {
		super(id, manager, controller);
	}

	/**
	 * I'm separating this function out to make debugging easier. Otherwise any
	 * null pointer exceptions get thrown as reflection exceptions.
	 */
	@Override
	public void initialize() {
		mySceneManager = new GameSceneManager("PrimarySceneManager", this, myController);
		myController.setSceneManager(mySceneManager);
	}

	@Override
	public void refresh() {
		mySceneManager.refresh();
	}

}
