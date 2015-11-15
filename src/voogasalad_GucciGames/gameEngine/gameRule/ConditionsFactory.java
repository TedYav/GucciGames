package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionsFactory {
	private AllPlayers myPlayers;
	private Outcome myOutcomes;
	private final String CONDITIONS_PROPERTIES = "resources/conditions";
	private final String OUTCOMES_PROPERTIES = "resources/outcomes";
	private ResourceBundle conditionBudle;
	private ResourceBundle outcomeBundle;

	public ConditionsFactory(AllPlayers players) {
		myPlayers = players;
		myOutcomes = new Outcome(players);
		conditionBudle = ResourceBundle.getBundle(CONDITIONS_PROPERTIES);
		outcomeBundle = ResourceBundle.getBundle(OUTCOMES_PROPERTIES);
	}
	public void createCOndition(){}

}
