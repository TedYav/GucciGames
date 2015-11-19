package voogasalad_GucciGames.gameplayer.windows;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Stage;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;

public class GameSceneManager implements SceneManager{

	private Map<String, GameScene> myScenes;
	private ResourceBundle myConfig;
	private GameScene myCurrentScene;
	private GameWindowInterface myWindow;
	private GameLoader myLoader;
	private XStreamGameEngine myData;
	
	public GameSceneManager(String config, GameWindowInterface window){
		myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config." + config);
		myWindow = window;
		myScenes = generateScenes();
		myData = new XStreamGameEngine();
		myLoader = new GameLoader(myData.loadEngine());
		myCurrentScene = myScenes.get(myConfig.getString("DefaultScene"));
		myCurrentScene.load();
	}
	
	private Map<String, GameScene> generateScenes(){
		return myConfig.keySet().stream()
			.filter((s) -> s.matches("SceneClass\\d"))
			.map( (s) -> (GameScene)Reflection.createInstance(myConfig.getString(s), this, myWindow, myConfig.getString("SceneConfig"+s.substring(10))))
			.collect(Collectors.toMap((s)->s.getName(), (s)->s));
	}
	
	public void sceneFinished(){
		myCurrentScene = myScenes.get(myCurrentScene.getNext());
		myCurrentScene.load();
		myLoader.reinitializeController(myCurrentScene);
	}
	public Stage getStage() {
	    return myWindow.getStage();
	}
	public GameLoader getLoader(){
		return myLoader;
	}
	
}
