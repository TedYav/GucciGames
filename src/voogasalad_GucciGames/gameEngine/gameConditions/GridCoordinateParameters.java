package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GridCoordinateParameters extends CommunicationParameters{

	private ATargetCoordinate listOfCoordinates;
	
	public GridCoordinateParameters(ATargetCoordinate coord){
		super();
		listOfCoordinates = coord;
	}
	

	public List<TargetCoordinateSingle> getListOfCoordinates() {
		return listOfCoordinates.getListOfCoordinates();
	}

}
