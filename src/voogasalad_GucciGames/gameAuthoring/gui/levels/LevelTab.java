package voogasalad_GucciGames.gameAuthoring.gui.levels;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.GuiMap;

public class LevelTab extends Tab {
	private final GuiMap myMap;

	LevelTab(AGuiGaeController controller, int id, String name) {
		myMap = new GuiMap(controller, id);
		this.setText(name);
		this.setContent(myMap);
	}

	public void initGrid(int width, int height) {
		myMap.initGrid(width, height);
		myMap.setBackground(new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg"));
	}

	protected GuiMap getMap() {
		return myMap;
	}

	// not used yet
	public String getName() {
		return getText();
	}

	public void setName(String name) {
		setText(name);
	}
}
