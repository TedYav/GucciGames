package voogasalad_GucciGames.gameAuthoring.gui.levels;

import java.util.ArrayList;

import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.GuiMap;

public class LevelTabPane extends TabPane {
	private final ArrayList<LevelTab> myTabs = new ArrayList<LevelTab>();
	private final AGuiGaeController myController;
	private int myTabCount = 0;

	@SuppressWarnings("static-access")
	public LevelTabPane(AGuiGaeController controller) {
		myController = controller;
		addEventHandler(KeyEvent.ANY, e -> {
			if (!(e.getTarget() instanceof GuiMap))
				e.fireEvent(getCurrTab().getMap(), e.copyFor(this, getCurrTab().getMap()));
			e.consume();
		});
	}
	
	public int levelCount(){
		return myTabCount;
	}

	public LevelTab getCurrTab() {
		int ind = getSelectionModel().getSelectedIndex();
		return myTabs.get(ind);
	}

	public LevelTab createNewTab(String name, int width, int height) {
		int id = myController.addLevel(name, width, height);
		LevelTab newTab = new LevelTab(myController, id, name);
		this.getTabs().add(newTab);
		newTab.setClosable(false);
		newTab.initGrid(width, height);
		myTabs.add(newTab);
		myTabCount++;
		return newTab;
	}
}
