package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;

public class Builder {
	
	public ChangedParameters build(String name, LocationParameters params){
		MapObject mo = params.getEngine().getBuild().get(name).clone();
		mo.setCoordinate(params.getNewLocation());
		ChangedParameters result = new ChangedParameters();
		result.addUnit(mo);
		return result;
	}
	
	public GridCoordinateParameters request(BasicParameters params){
		List<MapObject> allMapObjects = params.getEngine().getCurrentLevel().getPlayers().getAllUnits();
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();
		allMapObjects.stream().forEach(mo -> {
			if(mo.hasCharacteristic("TileCharacteristic") || mo.getName().equals("TileCharacteristic")){
				result.addTargetCoordinateSingle(mo.getCoordinate());
			}
		});
		allMapObjects.stream().forEach(mo -> {
			if(mo.hasCharacteristic("HealthCharacteristic")){
				for(int i = result.getListOfCoordinates().size()-1; i >= 0; i--){
					if(result.getListOfCoordinates().get(i).equals(mo.getCoordinate())){
						result.getListOfCoordinates().remove(i);
					}
				}
			}
		});
		return new GridCoordinateParameters(result);
	}

}