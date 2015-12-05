package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class GuiData extends AGameInfo{
	// these will hold the components which go in each part of the player gui
	// format can be changed
	private List<String> myRightComponents;	// hold class names
	private List<String> myLeftComponents;
	private List<String> myBottomComponents;
	private String myGameName;
	
	public GuiData(String gameName){
		this(gameName, defaultLeft(), defaultRight(), defaultBottom());
	}
	
	public GuiData(List<String> leftComponents, List<String> rightComponents, List<String> bottomComponents){
	    myLeftComponents=leftComponents;
	    myRightComponents=rightComponents;
	    myBottomComponents=bottomComponents;
	    
	    myGameName = "Game " + Math.round((Math.random()*10000));
	}
	
	// TODO: CHANGE THIS -> JOHN DAI
	
	public GuiData(String gameName, List<String> defaultLeft,
			List<String> defaultRight, List<String> defaultBottom) {
		// TODO Auto-generated constructor stub
	}

	private static List<String> defaultBottom() {
	
     List<String> bottomComponents=new ArrayList<String>();
         bottomComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.MainMenuButton");
        
         // REMOVED UNTIL WE MAKE DISPLAYCOMPONENT AND WINDOWCOMPONENT INTERCHANGEABLE
         //bottomComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap");
         return bottomComponents;
	}

	private static List<String> defaultRight() {
        List<String> rightComponents=new ArrayList<String>();
		 rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.ActionDisplay");
	     rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.BuildUnitsDisplay");
	     rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.EndTurnButton");
	     rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.GameStatsDisplay");
	     return rightComponents;
	}

	private static List<String> defaultLeft() {
		 List<String> leftComponents=new ArrayList<String>();
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectImage");
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectDetails");
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayChat");
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap");
	     return leftComponents;
	}
	


}
