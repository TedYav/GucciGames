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
		System.out.println(myScenes);
		myData = new XStreamGameEngine();
		myLoader = new GameLoader(myData.loadGameInfo(myConfig.getString("DefaultGame")));
		myCurrentScene = myScenes.get(myConfig.getString("DefaultScene"));
		myCurrentScene.load();
	}
	
	private Map<String, GameScene> generateScenes(){
		return myConfig.keySet().stream()
			.filter((s) -> s.matches("SceneClass\\d"))
			.map( (s) -> (GameScene)Reflection.createInstance(sceneClassPath(s), this, myWindow, configClassPath(s)))
			.collect(Collectors.toMap((s)->s.getName(), (s)->s));
	}
	
	private String sceneClassPath(String scene){
		System.out.println(myConfig.getString("ClassPath") + "." + myConfig.getString(scene));
		return myConfig.getString("ClassPath") + "." + myConfig.getString(scene);
	}
	
	private String configClassPath(String scene){
		System.out.println(myConfig.getString("ConfigPath") + "." + myConfig.getString(scene));
		return myConfig.getString("ConfigPath") + "." + myConfig.getString(scene);
	}
	
	public void loadScene(String sceneName){
		if(myScenes.get(sceneName)!=null){
			myCurrentScene = myScenes.get(sceneName);
			myCurrentScene.load();
			myLoader.reinitializeController(myCurrentScene);
		}
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
