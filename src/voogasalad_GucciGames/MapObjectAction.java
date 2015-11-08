package voogasalad_GucciGames;

import java.util.List;
import java.util.Map;

public class MapObjectAction {


	
	
	private Map<String, List<TileType>> myPossibleTileInteractions; // this
																	// string
	// will be
	// something
	// like
	// "CannotMove"
	// or
	// "RegainHealth"
	
	
	private List<UnitAbilities> myListOfAbilitiesToDo;

	
	
	public void add(UnitAbilities ability) {
		// TODO Auto-generated method stub
		myListOfAbilitiesToDo.add(ability); 
		
		
	}

	
}
