package voogasalad_GucciGames.gameAuthoring.gui.levels;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.GuiMap;
import voogasalad_GucciGames.gameAuthoring.gui.statusbar.StatusBar;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;

public class LevelTab extends Tab{
	private final GuiMap myMap;
	private String myName;
	
	LevelTab(AGuiGaeController controller, StatusBar statusBar, int id){
		myMap = new GuiMap(controller);
		myMap.setOnMouseMoved(e -> statusBar.update(e));
		myMap.setBackground(new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg"));
		myName = "Level " + id;
		this.setText(myName);
		this.setContent(myMap);
	}
	
	public void initGrid(int width, int height){
		myMap.initGrid(width, height);
		myMap.setBackground(new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg"));
	}
	
	protected GuiMap getMap(){
		return myMap;
	}
	
	//not used yet
	public String getName(){
		return myName;
	}
	
	public void setName(String name){
		myName = name;
	}
}
