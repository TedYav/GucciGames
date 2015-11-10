package voogasalad_GucciGames.usecases;

import javafx.scene.layout.BorderPane;
import voogasalad_GucciGames.gameplayer.window.menubar.FileMenu;
import voogasalad_GucciGames.gameplayer.window.menubar.GameMenuBar;

public class UseMainGamePlayer{

	private BorderPane menu;
	public void initialize(){
		menu = new BorderPane();
		menu.getChildren().add(new GameMenuBar(null).returnToolbar());
		
	}
	
}
