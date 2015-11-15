package voogasalad_GucciGames.gameplayer.windows;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;

public abstract class GameScene {

	private String myName;
	private String myNext;
	protected GameSceneManager myManager;
	protected GameWindowInterface myWindow;
	protected ResourceBundle myConfig;
	protected Scene myScene;
	
	public GameScene(GameSceneManager manager, GameWindowInterface window, String config){
		myManager = manager;
		myWindow = window;
		myConfig = ResourceBundle.getBundle(config);
		readConfig();
	}
	
	protected void readConfig(){
		myName = myConfig.getString("Name");
		myNext = myConfig.getString("NextScene");
	}
	
	protected void loadScene(Scene scene){
		myScene = scene;
		myWindow.loadScene(myScene);
	}

	/**
	 * Returns name of the scene.
	 * @return
	 */
	public String getName(){
		return myName;
	}
	
	/**
	 * returns the name of the next scene to load
	 * As defined in resource file.
	 * @return
	 */
	public String getNext(){
		return myNext;
	}
	
	/**
	 * Tells this scene to load its necessary information and write it to the GameWindow.
	 */
	public abstract void load();
	
}
