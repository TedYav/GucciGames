package voogasalad_GucciGames.gameplayer.windows.mainwindow;

import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;

public class MainGameWindow extends GameWindow {

	private GameSceneManager mySceneManager;
	
	public MainGameWindow(int id) {
		super(id);
	}
	
	/**
	 * I'm separating this function out to make debugging easier.
	 * Otherwise any null pointer exceptions get thrown as reflection exceptions.
	 */
	public void initialize(){
		mySceneManager = new GameSceneManager("PrimarySceneManager", this);
	}

}
