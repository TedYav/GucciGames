package voogasalad_GucciGames.gameplayer.windows;

import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneInterface;

public abstract class GameScene implements GameSceneInterface{

	private String myName;
	private String myNext;
	protected GameSceneManager myManager;
	protected GameWindowInterface myWindow;
	protected ResourceBundle myConfig;
	protected Scene myScene;
	
	public GameScene(GameSceneManager manager, GameWindowInterface window, String config){
		myManager = manager;
		myWindow = window;
		myConfig = PlayerConfig.load(config);
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
	 * Returns the current GameSceneManager 
	 * @return
	 */
	public GameSceneManager getManager(){
		return myManager;
	}
	
	/**
	 * Returns the current GameWindow
	 * @return
	 */
	public GameWindowInterface getWindow(){
		return myWindow;
	}
	
	/**
	 * returns the name of the next scene to load
	 * As defined in resource file.
	 * @return
	 */
	public String getNext(){
		return myNext;
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	
	/**
	 * Tells this scene to load its necessary information and write it to the GameWindow.
	 */
	public abstract void load();

	@Override
	public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<T> eventHandler) {
		myScene.addEventHandler(eventType, eventHandler);
	}

	@Override
	public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<T> eventFilter) {
		myScene.addEventFilter(eventType, eventFilter);
	}

	
	
}
