package voogasalad_GucciGames.gameEngine.mapObject;
//@Sally, edited this class for use case #2
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinate;
import voogasalad_GucciGames.gameEngine.tile.TileType;

public class MapObjectAction{




	private Map<String, List<TileType>> myPossibleTileInteractions; // this
																	// string
	// will be
	// something
	// like
	// "CannotMove"
	// or
	// "RegainHealth"
	private TargetCoordinate myPossibleTargetSquares; //TargetCoordinate can be single or multiple
	public void onClick(){
		//this method calls findPossibleMovements() in Movement.java
		//it passes with it an interface of the map, and the object itself
		//findPossibleMovements returns a list of possible coordinates that will
		// be stored in this class.
	}


}
