package voogasalad_GucciGames.gameplayer.scenes;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Stage;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;
import voogasalad_GucciGames.gameplayer.windows.SceneManager;

public class GameSceneManager implements SceneManager{

	private Map<String, GameScene> myScenes;
	private ResourceBundle myConfig;
	private GameScene myCurrentScene;
	private GameWindowInterface myWindow;
	private XStreamGameEngine myData;
	private GameController myController;
	
	public GameSceneManager(String config, GameWindowInterface window, GameController controller){
		myConfig = PlayerConfig.load(config);
		myController = controller;
		myWindow = window;
		myScenes = generateScenes();
		myData = new XStreamGameEngine();
		myCurrentScene = myScenes.get(myConfig.getString("DefaultScene"));
		myCurrentScene.load();
	}
	
	private Map<String, GameScene> generateScenes(){
		return myConfig.keySet().stream()
			.filter((s) -> s.matches("SceneClass\\d+"))
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
		System.out.println(myCurrentScene.getNext());
		myCurrentScene = myScenes.get(myCurrentScene.getNext());
		myCurrentScene.load();
	}
	public Stage getStage() {
	    return myWindow.getStage();
	}
	public GameLoader getLoader(){
		return myController.getLoader();
	}

	public void refresh() {
		myCurrentScene.refresh();
	}

	public GameControllerInterface getController() {
		return myController;
	}
	
}
