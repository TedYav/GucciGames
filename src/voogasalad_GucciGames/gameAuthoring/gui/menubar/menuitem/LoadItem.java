package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import java.io.File;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameplayer.PlayerMain;

class LoadItem extends MenuItem {
	LoadItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		setOnAction(e -> {
			controller.saveToXML(new File("./src/voogasalad_GucciGames/gameData/gaeengine.xml"));
			PlayerMain main = new PlayerMain();
			try {
				main.start(new Stage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
}
