
package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class EndLevel extends Outcome {
	private static final String NEXT_LEVEL="nextLevel";
	private String myDestination;

	public EndLevel(List<Conditions> conditions,String affectedPlayers, String nextLevel, String destination) {
		super(conditions, affectedPlayers);
		myDestination = destination;
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params,ChangedParameters changedParams, int i) {
		params.getEngine().setEndLevel(true);
		changedParams.setLevel(myDestination);
		//params.getEngine().changeLevel(myDestination);
		System.out.println("setgame="+params.getEngine().hasLevelEnded());
		return changedParams;
	}

}
