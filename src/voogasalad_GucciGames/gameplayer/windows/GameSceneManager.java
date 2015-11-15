package voogasalad_GucciGames.gameplayer.windows;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;

public class GameSceneManager implements SceneManager{

	private Map<String, GameScene> myScenes;
	private ResourceBundle myConfig;
	private GameScene myCurrentScene;
	private GameWindowInterface myWindow;
	private GameLoader myLoader;
	
	public GameSceneManager(String config, GameWindowInterface window){
		myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config." + config);
		myWindow = window;
		myScenes = generateScenes();
		myLoader = new GameLoader();
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
	}
	
	public GameLoader getLoader(){
		return myLoader;
	}
	
}
