package voogasalad_GucciGames.gameAuthoring.gui.levels;

import java.util.ArrayList;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.statusbar.StatusBar;
import javafx.scene.control.TabPane;

public class LevelTabPane extends TabPane{
	ArrayList<LevelTab> myTabs = new ArrayList<LevelTab>();
	AGuiGaeController myController;
	StatusBar myStatusBar;
	
	public LevelTabPane(AGuiGaeController controller, StatusBar statusBar){
		LevelTab levelTab = new LevelTab(controller, statusBar, 1);
		levelTab.setClosable(false);
		this.getTabs().add(levelTab);
		myController = controller;
		myStatusBar = statusBar;
		myTabs.add(levelTab);
	}
	
//	public void addTab(LevelTab levelTab) {
//		myTabs.add(levelTab);
//	}
	
	public LevelTab getCurrTab() {
		int ind = getSelectionModel().getSelectedIndex();
		return myTabs.get(ind);
	}

	public LevelTab createNewTab() {
		int id = myTabs.size() + 1;
		LevelTab newTab = new LevelTab(myController, myStatusBar,id);
		this.getTabs().add(newTab);
		myTabs.add(newTab);
		return newTab;
	}
	
	public void initializeMap(int width, int height, LevelTab levelTab) {
		levelTab.initGrid(width, height);
	}
}
