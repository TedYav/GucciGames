package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.UnitCollection;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;

public class NoUnitsGlobalGameRule extends GlobalGameRule{

	public NoUnitsGlobalGameRule(GameMap gameMap) {
		super(gameMap);
		
		// TODO Auto-generated constructor stub
	}

	
	//returns an empty list if game has not ended, returns the states otherwise
	@Override
	public List<EndGameConditions> gameEnded() {
		List<EndGameConditions> gameOverState = new ArrayList<EndGameConditions>();
		
		List<UnitCollection> allUnits= myGameMap.getAllUnits();
		
		HashMap<Integer, Boolean> hasUnits = new HashMap<Integer, Boolean>();
		
		for(int i = 0; i < allUnits.size(); i++){
			
			if(allUnits.get(i).size() == 0){
			hasUnits.put(i,  false);
			}
			else{
				hasUnits.put(i,  true);
			}
		}
		
		
		int howManyHasUnits = 0;
		int winner = -1;
		for(int i = 1; i < allUnits.size(); i++){
			
			if(hasUnits.get(i).booleanValue()){
				howManyHasUnits++;
				winner = i;
			}
		}

		
		if(howManyHasUnits == 1){
			gameOverState.add(EndGameConditions.LOSE);
			for(int i = 1; i < allUnits.size(); i++){
				
				if(hasUnits.get(i).booleanValue()){
					gameOverState.add(EndGameConditions.WIN);
							
				}
				gameOverState.add(EndGameConditions.LOSE);

			}
			
		}
		
		return gameOverState;
	}

	
	
}
