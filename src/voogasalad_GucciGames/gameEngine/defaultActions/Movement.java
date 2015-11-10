package voogasalad_GucciGames.gameEngine.defaultActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.TheMap;

/**
 *
 * @author Sally Al
 * @use case #2
 *
 */
public class Movement {
	private static final String CARDINAL = "cardinal";
	private static final String DIAGONAL = "diagonal";
	private static final String ALL = "all";
	private static final String CUSTOM = "custom";
	private int numOfTiles;
	private String userChoice;
	private List<List<String>> customMovements;

	public void setNumOfTiles(int distance){
		//this method is used when the user chooses one (cardinal, diagonal, or all) algorithms
		//this method will update numOfTiles with the distance in any direction it can move.
		numOfTiles=distance;
	}
	public void setUserChoice(String option){
		userChoice=option;
				//this method is used to denote which algorithm the user chose (cardinal, diagonal,custom, or all)
	}
	public void setCustomMovements(List<List<String>> userInput){
		//this method is called from mapObjectActions.java
		// this method is used when the user chooses to customized the way a unit can move.
		// the user input all different possibilities a unit can move and this method assigns it to
		//customMovements list for later use.
	}

	public List<List<Double>> findPossibleMovements(TheMap gridInformation, MapObject self){
		//this method is fired when a player clicks on a unit.
		//this method will call one of the four algorithms' methods:
		//checkDiag(gridInformation, self), checkCardinal(gridInformation, self), checkAll(gridInformation, self), or checkCustom(gridInformation, self)
		//each of these methods will return a list of possible coordinates
		return null;

	}
	private List<List<Double>> checkCardinal(TheMap gridInformation, MapObject self){
		//this method has the algorithm that will will check tiles at possible
		// cardinal direction from myLocation of the unit (self).
		// the number of tiles to be check at each direction depends on numOfTiles
		//if a tile is accessible, then it is added to the list.
		//a unit cannot move to a tile if:
		//1. the tile is out of the map boundaries
		//2. the tile is occupied by any other unit
		//3. the tile type is not accessible by this particular unit.
		// the last point is determined by checking the tile type against the list of promotable
		// tiles from the MapObject abilities.
		// this method returns a list of coordinates.
		return null;

	}

	private List<List<Double>> checkDiagonal(TheMap gridInformation, MapObject self){
		//this  method is similar to chekCardinal.
		// the only difference is that it holds the logic for checking tiles at a diagonal
		//direction of the unit.
		return null;

	}

	private List<List<Double>> checkAll(TheMap gridInformation, MapObject self){
		//this  method is similar to chekCardinal.
		// this method makes two calls: first to checkDiagonal, then to checkCardinal.
		return null;

	}

	private List<List<Double>> checkCustom(TheMap gridInformation, MapObject self){
		//this method loops through the list of possibileMovement, and determines if
		// what of the possible options is accessible from the unit.
		// it runs the same three checks as checkCardinal()
		// it returns a list of coordinates.
		return null;

	}






}
