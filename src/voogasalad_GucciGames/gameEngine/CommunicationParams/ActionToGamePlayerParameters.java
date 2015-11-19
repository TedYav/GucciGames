package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class ActionToGamePlayerParameters extends CommunicationParameters{
	
	private List<PlayerMapObjectInterface> changedUnits;
	
	public ActionToGamePlayerParameters(){
		changedUnits = new ArrayList<PlayerMapObjectInterface>();
		
	}
	
	public void addUnit(PlayerMapObjectInterface unit){
		changedUnits.add(unit);
	}
	
	public List<PlayerMapObjectInterface> getChangedUnits(){
		return changedUnits;
	}

}
