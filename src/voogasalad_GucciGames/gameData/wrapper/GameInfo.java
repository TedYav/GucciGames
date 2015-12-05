package voogasalad_GucciGames.gameData.wrapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

/**
 * 
 * This class will be a wrapper for the game engine
 * and the information to configure the game player
 * gui
 *
 */


public class GameInfo implements GameInfoToGamePlayer{
	private GameEngine myEngine;
	private GuiData guiData;
	
	public GameInfo(){
		
	}
	

	@Override
	public String getGameName() {
		return myEngine.getGameName();
	}

	@Override
	public List<String> getGuiComponents(String location) {
		return guiData.getComponents(location);
	}

	@Override
	public void setGuiComponents(String location, List<String> components) {
		guiData.setComponents(location, components);
	}


	
	
	
	
	
}