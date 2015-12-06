package voogasalad_GucciGames.gameplayer.scenes;

import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.WindowComponent;

public abstract class GameScene implements GameSceneInterface{

	private String myName;
	private String myNext;
	protected GameSceneManager myManager;
	protected GameWindowInterface myWindow;
	protected ResourceBundle myConfig;
	protected ResourceBundle myCSS = PlayerConfig.load("scenes.CssClasses");
	protected ResourceBundle myGuiNames = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GuiComponents");
	protected Scene myScene;
	protected StackPane myParent;
	private Parent myOverlay;
	
	public GameScene(GameSceneManager manager, GameWindowInterface window, String config){
		myManager = manager;
		myWindow = window;
		myConfig = PlayerConfig.load(config);
		myParent = new StackPane();
		myScene = new Scene(myParent);
		readConfig();
	}
	
	protected void readConfig(){
		myName = myConfig.getString("Name");
		myNext = myConfig.getString("NextScene");
	}
	
	protected void loadParent(Parent parent){
		myParent.getChildren().clear();
		myParent.getChildren().add(parent);
		myWindow.loadScene(myScene);
		loadStyleSheets();
	}

	private void loadStyleSheets(){
        myScene.getStylesheets().add(myCSS.getString("CssFile"));
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
	
	public void addOverlay(WindowComponent overlay, double opacity){
		myOverlay = overlay.getParent();
		myOverlay.setOpacity(opacity);
		myParent.getChildren().add(myOverlay);
	}

	public void removeOverlay() {
		if(myOverlay != null)
			myParent.getChildren().remove(myOverlay);
	}

	
	
}
