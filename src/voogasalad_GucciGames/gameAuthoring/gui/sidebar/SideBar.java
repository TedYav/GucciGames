package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class SideBar{
	private MapObjectType myCurrSelection;
	private AGuiGaeController myController;
	private TabPane myTabPane;
	private ATab mySelectSource;
	
	public SideBar(AGuiGaeController controller){
		myController = controller;
		Tab tileTab = new TileTab(this);
    	Tab unitTab = new UnitTab(this);
    	Tab strucTab = new StructureTab(this);
    	myTabPane = new TabPane(tileTab,unitTab,strucTab);
	}
	
	public TabPane getPane(){
		return myTabPane;
	}
	
	protected AGuiGaeController getController(){
		return myController;
	}
	
	protected MapObjectType getCurrSelection(){
		return myCurrSelection;
	}
	
	protected void setCurrSelection(ATab source, MapObjectType type){
		if(mySelectSource!=null){
			mySelectSource.deselect();
		}
		myCurrSelection = type;
		mySelectSource = source;
	}
}
