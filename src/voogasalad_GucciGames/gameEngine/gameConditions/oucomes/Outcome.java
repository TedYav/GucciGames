package voogasalad_GucciGames.gameEngine.gameConditions.oucomes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Outcome {
	private List<Conditions> myConditions = new ArrayList<Conditions>();

	public Outcome(List<Conditions> conditions) {

		myConditions = conditions;
	}


	abstract void applyOutcome(BasicParameters params, int i);

	public void addCondition(Conditions condition) {
		myConditions.add(condition);
	}

	public final void executeOutcome(BasicParameters params) {
		List<Integer> players = params.getEngine().getPlayers().getAllIds();//now checks every cond/outcome for all players. a list of players to check is useful
		for (int i = 0; i < players.size(); i++) {
			GamePlayerPerson cur= params.getEngine().getPlayers().getPlayerById(players.get(i));
			if (checkConditions(cur)) {
				applyOutcome(params,  cur.getMyPlayerId());
			}
		}

	}

	private Boolean checkConditions(GamePlayerPerson player) {
		if (myConditions.isEmpty()) {
			return true;
		} else {
			Boolean flag = true;
			Iterator<Conditions> itr = myConditions.iterator();
			while (itr.hasNext() && flag == true) {
				flag = itr.next().execute(player);
			}
			return flag;
		}

	}
}
