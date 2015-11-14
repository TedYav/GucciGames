package voogasalad_GucciGames.gameplayer.windows;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;

public abstract class GameWindow implements GameWindowInterface {	
	
	int myID;
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.WindowManager");
	private Stage myStage;
	
	public GameWindow(int id){
		myID = id;
		myStage = new Stage();
		displayWindow();
	}

	private void displayWindow(){
		Map<String,String> myWindowConfig = parseConfig();
		myStage.setWidth(Integer.parseInt(myWindowConfig.get("Width")));
		myStage.setHeight(Integer.parseInt(myWindowConfig.get("Height")));
		myStage.setResizable(Integer.parseInt(myWindowConfig.get("Resizable"))==1);
		myStage.setTitle(myWindowConfig.get("Title"));
		myStage.show();
	}

	private Map<String, String> parseConfig() {
		return myConfig.keySet().stream()
			.filter((s) -> s.matches("^[\\s\\S]*," + myID + "$"))
			.collect(Collectors.toMap((s)->s.toString().split(",")[0].substring(6),
					(s)->myConfig.getString(s)));
	}

	@Override
	public void loadScene(Scene scene) {
		myStage.setScene(scene);
	}
	
	public abstract void initialize();
	
}
