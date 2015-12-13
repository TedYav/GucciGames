package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ResourceBundle;

import javafx.scene.Node;

public interface GameMenu {
	public ResourceBundle myBundle = ResourceBundle
			.getBundle("voogasalad_GucciGames.gameplayer.config.menubar.menubar");

	public Node returnNodeToDraw();
}
