package voogasalad_GucciGames.gameEngine.targetCoordinate;

import java.util.ArrayList;
import java.util.List;

public class TargetCoordinateMultiple extends ATargetCoordinate {
	
	private List<ATargetCoordinate> myCoordinates;
	
	public TargetCoordinateMultiple(){
		this.myCoordinates = new ArrayList<>();
	}
	
	public List<ATargetCoordinate> getCoordinates(){
		return this.myCoordinates;
	}
	
}
