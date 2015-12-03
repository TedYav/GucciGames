package voogasalad_GucciGames.gameEngine.CommunicationParameters;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;

public class ChangedParameters extends CommunicationParameters{
	
	private List<PlayerMapObjectInterface> changedUnits;
	
	public ChangedParameters(){
		changedUnits = new ArrayList<PlayerMapObjectInterface>();
		
	}
	
	public void addUnit(PlayerMapObjectInterface unit){
		changedUnits.add(unit);
	}
	
	public List<PlayerMapObjectInterface> getChangedUnits(){
		return changedUnits;
	}

}
