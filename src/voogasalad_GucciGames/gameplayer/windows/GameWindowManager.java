package voogasalad_GucciGames.gameplayer.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;

public class GameWindowManager {

	private List<GameWindow> myWindows;
	private ResourceBundle myConfig = PlayerConfig.load("WindowManager");
		
	private GameController myController;
	
	public GameWindowManager(){
		myWindows = new ArrayList<>();
		myController = new GameController(this);
		generateWindows();
		initializeWindows();
		
	}
	
	public GameWindowManager(String gameName){
		this();
		myController.getLoader().loadGame(gameName);
	}
	
	private void generateWindows(){
		myConfig.keySet().stream()
			.filter((s) -> s.matches("WindowClass,\\d"))
			.forEach( (s) -> myWindows.add((GameWindow)Reflection.createInstance(myConfig.getString(s), Integer.parseInt(s.split(",")[1]), this, myController )));
	}
	
	private void initializeWindows(){
		myWindows.stream().forEach((w)->w.initialize());
	}

	public void refresh() {
		myWindows.stream().forEach((w)->w.refresh());
	}
}
