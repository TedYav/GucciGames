package voogasalad_GucciGames.gameplayer.windows;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Stage;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameplayer.config.Config;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;

public class GameSceneManager implements SceneManager{

	private Map<String, GameScene> myScenes;
	private ResourceBundle myConfig;
	private GameScene myCurrentScene;
	private GameWindowInterface myWindow;
	private GameLoader myLoader;
	private GameController myController;
	private XStreamGameEngine myData;
	
	public GameSceneManager(String config, GameWindowInterface window){
		myConfig = Config.load(config);
		myWindow = window;
		myScenes = generateScenes();
		myData = new XStreamGameEngine();
		myCurrentScene = myScenes.get(myConfig.getString("DefaultScene"));
		myCurrentScene.load();
		myController = new GameController(this);
		myLoader = new GameLoader(myController);
	}
	
	private Map<String, GameScene> generateScenes(){
		return myConfig.keySet().stream()
			.filter((s) -> s.matches("SceneClass\\d"))
			.map( (s) -> (GameScene)Reflection.createInstance(sceneClassPath(s), this, myWindow, configClassPath(s)))
			.collect(Collectors.toMap((s)->s.getName(), (s)->s));
	}
	
	private String sceneClassPath(String scene){
		return myConfig.getString("ClassPath") + "." + myConfig.getString(scene);
	}
	
	private String configClassPath(String scene){
		return myConfig.getString("ConfigPath") + "." + myConfig.getString(scene);
	}
	
	public void loadScene(String sceneName){
		if(myScenes.get(sceneName)!=null){
			myCurrentScene = myScenes.get(sceneName);
			myCurrentScene.load();
		}
	}
		
	public void sceneFinished(){
		myCurrentScene = myScenes.get(myCurrentScene.getNext());
		myCurrentScene.load();
	}
	public Stage getStage() {
	    return myWindow.getStage();
	}
	public GameLoader getLoader(){
		return myLoader;
	}

	public void refresh() {
		myCurrentScene.refresh();
	}

	public GameControllerInterface getController() {
		return myController;
	}
	
}
