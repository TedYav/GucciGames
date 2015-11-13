package voogasalad_GucciGames.gameplayer.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import voogasalad.util.reflection.Reflection;
import javafx.stage.Stage;

public class GameWindowManager {

	private List<GameWindow> myWindows;
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.WindowManager");
	
	public GameWindowManager(){
		myWindows = new ArrayList<>();
		generateWindows();
	}
	
	private void generateWindows(){
		myConfig.keySet().stream()
			.filter((s) -> s.matches("WindowClass,\\d"))
			.forEach( (s) -> myWindows.add((GameWindow)Reflection.createInstance(myConfig.getString(s), Integer.parseInt(s.split(",")[1]) )));
	}
	
}
