package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.Map;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class GameResultsScreen extends ResultsScreen{

	private ResourceBundle myConfig = PlayerConfig.load("components.GameResultsScreen");
	
	public GameResultsScreen(GameScene scene, GameControllerInterface controller, String title,
			Map<String, String> information) {
		super(scene, controller, title, information);
		setStyle();
		// TODO Auto-generated constructor stub
	}

	private void setStyle() {
		myTitleText.getStyleClass().addAll( myConfig.getString("TitleClass"), "gametext");
	}
	
	

}
