package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class OpenItem extends MenuItem{
	OpenItem(String name, GameControllerInterface controller) {
		super(name);
		setOnAction(e -> {
			//controller.saveToXML();
			//TODO
		});
	}
}

