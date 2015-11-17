package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GridCoordinateParameters extends CommunicationParams{

	private ATargetCoordinate listOfCoordinates;
	
	public GridCoordinateParameters(ATargetCoordinate coord){
		super(null, null, null, null);
		listOfCoordinates = coord;
	}
	

	public List<TargetCoordinateSingle> getListOfCoordinates() {
		return listOfCoordinates.getListOfCoordinates();
	}

	public void setListOfCoordinates(ATargetCoordinate listOfCoordinates) {
		this.listOfCoordinates = listOfCoordinates;
	}

}
