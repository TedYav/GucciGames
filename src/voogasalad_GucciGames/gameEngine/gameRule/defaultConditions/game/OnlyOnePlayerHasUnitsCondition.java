package voogasalad_GucciGames.gameEngine.gameRule.defaultConditions.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.UnitCollection;
import voogasalad_GucciGames.gameEngine.gameRule.EndGameConditions;

public class OnlyOnePlayerHasUnitsCondition extends GlobalGameCondition {

	public OnlyOnePlayerHasUnitsCondition(GameMap gameMap) {
		super(gameMap);
	}

	@Override
	public List<EndGameConditions> getConditionResolution() {

		List<EndGameConditions> gameOverState = new ArrayList<EndGameConditions>();
		List<UnitCollection> allUnits = myGameMap.getAllUnits();
		HashMap<Integer, Boolean> hasUnits = new HashMap<Integer, Boolean>();

		for (int i = 0; i < allUnits.size(); i++) {
			hasUnits.put(i, !(allUnits.get(i).size() == 0));
		}

		int howManyHasUnits = 0;
		int winner = -1;
		for (int i = 1; i < allUnits.size(); i++) {

			if (hasUnits.get(i).booleanValue()) {
				howManyHasUnits++;
				winner = i;
			}
		}

		if (howManyHasUnits == 1) {

			gameOverState.add(EndGameConditions.LOSE);
			for (int i = 1; i < allUnits.size(); i++) {

				if (hasUnits.get(i).booleanValue()) {
					gameOverState.add(EndGameConditions.WIN);

				}
				gameOverState.add(EndGameConditions.LOSE);

			}

		}

		return gameOverState;

	}

	@Override
	public boolean hasConditionResolved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void evaluateEndResult() {
		// TODO Auto-generated method stub
		
	}

}
