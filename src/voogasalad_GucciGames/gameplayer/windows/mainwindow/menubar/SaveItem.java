package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class SaveItem extends MenuItem {
	SaveItem(String name, GameControllerInterface controller) {
		super(name);
		setOnAction(e -> {
			// controller.saveToXML();
			// TODO
		});
	}
}
