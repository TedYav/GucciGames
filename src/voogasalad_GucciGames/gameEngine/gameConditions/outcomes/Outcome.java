package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Outcome {
	private List<Conditions> myConditions = new ArrayList<Conditions>();
	private String myAffectedPlayers = "self";

	public Outcome(String affectedPlayers, String argumentValue) {
		this(affectedPlayers);
	}

	public Outcome(String affectedPlayers, int argumentValue) {
		this(affectedPlayers);
	}

	public Outcome(String affectedPlayers) {
		myAffectedPlayers = (affectedPlayers);
	}

	public Outcome() {

	}

	abstract ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int playerID);

	public void addCondition(Conditions condition) {
		myConditions.add(condition);
	}

	public final ChangedParameters executeOutcome(BasicParameters params, ChangedParameters changedParams) {
		// for every affected player, check conditions, then apply outcome.
		List<Integer> players = createPlayerAffected(params);
		// myOutcomeParams.getPlayerID();
		for (int i = 0; i < players.size(); i++) {
			GamePlayerPerson cur = params.getLevelEngine().getPlayers().getPlayerById(players.get(i));
			if (checkConditions(params, cur)) {
				changedParams = applyOutcome(params, changedParams, players.get(i));
			}
		}

		return changedParams;
	}

	private List<Integer> createPlayerAffected(BasicParameters params) {
		List<Integer> temp = new ArrayList<Integer>();
		if (myAffectedPlayers.equals("self")) {
			temp.add(params.getLevelEngine().getTurnPlayerID());

		} else if (myAffectedPlayers.equals("other")) {
			List<Integer> list = params.getLevelEngine().getPlayers().getAllIds();
			for (int i = 0; i < list.size(); i++) {
				if (params.getLevelEngine().getTurnPlayerID() != list.get(i)) {
					temp.add(list.get(i));
				}
			}
		}
		return temp;
	}

	private Boolean checkConditions(BasicParameters params, GamePlayerPerson player) {

		if (myConditions.isEmpty()) {
			return true;
		} else {
			Boolean flag = true;
			Iterator<Conditions> itr = myConditions.iterator();
			while (itr.hasNext() && flag == true) {
				flag = itr.next().execute(params, player);
			}
			return flag;
		}

	}
}
